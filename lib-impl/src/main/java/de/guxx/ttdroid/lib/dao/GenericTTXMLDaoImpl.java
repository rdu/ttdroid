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
package de.guxx.ttdroid.lib.dao;

import de.guxx.ttdroid.lib.exception.ElementNotFoundException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author rdu
 */
public abstract class GenericTTXMLDaoImpl<T> implements Dao<T>
{
    @Override
    public T get()
    {
        if (isCached())
        {
            return getCached();
        }
        T object = read();
        putToCache(object);
        return object;
    }

    protected boolean isCached()
    {
        return false;
    }

    protected T getCached()
    {
        return null;
    }

    protected void putToCache(T object)
    {
    }

    /**
     * helper method to fetch the first element by name
     * 
     * @param name
     * @return
     * @throws ElementNotFoundException
     */
    protected final Element getFirstElement(Element element, String name) throws ElementNotFoundException
    {
        NodeList nl = element.getElementsByTagName(name);
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
    public String getString(Element element, String name)
    {
        try
        {
            Element e = getFirstElement(element, name);
            return e.getTextContent();
        } catch (ElementNotFoundException ex)
        {
            return "";
        }
    }

    /**
     * helper method to get (and parse) the double content of an element by name
     * 
     * @param name
     * @return
     */
    public Double getDouble(Element document, String name)
    {
        try
        {
            try
            {
                Element e = getFirstElement(document, name);
                return Double.parseDouble(e.getTextContent());
            } catch (Exception e)
            {
            }
        } catch (ElementNotFoundException ex)
        {
        }
        return 0d;
    }

    protected abstract T read();
}
