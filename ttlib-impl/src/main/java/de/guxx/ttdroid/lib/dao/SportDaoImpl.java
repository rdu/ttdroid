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
        String session = Settings.getSession();
        TTXml result = TTXml.getInstance(session);
        Document dom = result.getDomDocument("sports/list");
	
	NodeList nl = dom.getElementsByTagName("value");
		
	List<Sport> ll = new ArrayList<Sport>();
	for (int n = 0; n < nl.getLength(); n++)
	{
	    Sport s = new Sport();
	    Element e = (Element) nl.item(n);
	    s.setName(getString(e, "name"));
	    ll.add(s);	    
	}
	
	return ll;
    }

}
