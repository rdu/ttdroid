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

    /**
     * root element to work on
     */
    protected Element rootElement;
    /**
     * document that contains the current element
     */
    protected Document document;

    private GenericEntity()
    {
    }

    /**
     * the only public contructor
     * 
     * @param rootElement
     * @param document
     */
    public GenericEntity(Element rootElement, Document document)
    {
	this.rootElement = rootElement;
	this.document = document;
    }

    /**
     * helper method to fetch the first element by name
     * 
     * @param name
     * @return
     * @throws ElementNotFoundException
     */
    protected final Element getFirstElement(String name) throws ElementNotFoundException
    {
	NodeList nl = rootElement.getElementsByTagName(name);
	if (nl != null)
	{
	    if (nl.getLength() > 0)
	    {
		return (Element) nl.item(0);
	    }
	}
	throw new ElementNotFoundException("element " + name + " not found");
    }

    /**
     * helper method to get the string content of an element by name
     * 
     * @param name
     * @return
     */
    public String getString(String name)
    {
	try
	{
	    Element e = getFirstElement(name);
	    return e.getTextContent();
	}
	catch (ElementNotFoundException ex)
	{
	    return "";
	}
    }

    /**
     * helper method to get (and parse) the double content of an element by name
     * @param name
     * @return
     */
    public Double getDouble(String name)
    {
	try
	{
	    try
	    {
		Element e = getFirstElement(name);
		return Double.parseDouble(e.getTextContent());
	    }
	    catch (Exception e)
	    {
	    }
	}
	catch (ElementNotFoundException ex)
	{
	}
	return 0d;
    }
}
