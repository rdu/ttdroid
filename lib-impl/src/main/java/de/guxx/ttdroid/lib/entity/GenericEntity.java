/**
 * Copyright (C) 2010-2010 rdu <ich@ronny-dudeck.de>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program at http://www.gnu.org/licenses/licence/gpl_v3.txt.
 *
 * If not, see <http://www.gnu.org/licenses/>.
 */
package de.guxx.ttdroid.lib.entity;

import de.guxx.ttdroid.lib.exception.ElementNotFoundException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author rdu
 */
public abstract class GenericEntity
{

    protected Element rootElement;
    protected Document document;

    private GenericEntity()
    {
    }

    public GenericEntity(Element rootElement, Document document)
    {
	this.rootElement = rootElement;
	this.document = document;
    }

    protected final Element getFirstElement(String name) throws ElementNotFoundException
    {
	NodeList nl = rootElement.getElementsByTagName("session");
	if (nl != null)
	{
	    if (nl.getLength() > 0)
	    {
		return (Element) nl.item(0);
	    }
	}
	throw new ElementNotFoundException("element " + name + "not found");
    }

    public String getString(String name)
    {
	try
	{
	    Element session = getFirstElement(name);
	    return session.getTextContent();
	}
	catch (ElementNotFoundException ex)
	{
	    return "";
	}

    }
}
