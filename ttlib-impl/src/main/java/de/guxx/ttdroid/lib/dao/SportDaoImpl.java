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

import de.guxx.ttdroid.lib.entity.Sport;
import de.guxx.ttdroid.lib.util.Settings;
import de.guxx.ttdroid.lib.util.TTXml;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author rdu
 */
public class SportDaoImpl extends GenericTTXMLDaoImpl<Sport> implements SportDao
{

    @Override
    protected Sport read()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Sport> list()
    {
	return readFromCache();
	/*
	String session = Settings.getSession();
	TTXml result = TTXml.getInstance(session);
	Document dom = result.getDomDocument("sports/list");

	NodeList nl = dom.getElementsByTagName("value");

	List<Sport> ll = new ArrayList<Sport>();
	for (int n = 0; n < nl.getLength(); n++)
	{
	    Sport s = new Sport();
	    Element e = (Element) nl.item(n);
	    s.setId(getInteger(e, "id"));
	    s.setName(getString(e, "name"));
	    s.setIconId(getInteger(e, "icon-id"));
	    s.setIconImage16x16(getString(e, "icon-image-16x16"));
	    s.setIconImage32x32(getString(e, "icon-image-32x32"));
	    s.setIconImage64x64(getString(e, "icon-image-64x64"));
	    s.setDescription(getString(e, "comment"));
	    s.setLastChange(getDate(e, "lastchange"));
	    ll.add(s);
	}
	putToCacheObj(ll);
	return ll;*/
    }

    protected void putToCacheObj(Object obj)
    {
	FileOutputStream fos = null;
	try
	{
	    fos = new FileOutputStream("/tmp/sportslist.dat");
	    ObjectOutputStream o = new ObjectOutputStream(fos);
	    o.writeObject(obj);
	    fos.close();
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }

    protected List<Sport>readFromCache()
    {
	FileInputStream fis = null;
	try
	{
	    fis = new FileInputStream("/tmp/sportslist.dat");
	    ObjectInputStream o = new ObjectInputStream(fis);
	    List<Sport> l = (List<Sport>)o.readObject();
	    fis.close();
	    return l;
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	return null;
    }
}
