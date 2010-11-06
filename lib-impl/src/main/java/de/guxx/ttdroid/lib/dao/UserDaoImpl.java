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

import de.guxx.ttdroid.lib.entity.User;
import de.guxx.ttdroid.util.TTXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author rdu
 */
public class UserDaoImpl implements UserDao
{
/*    public static User getCurrentUser()
    {
	String session = Settings.getSession();
	TTXml result = TTXml.getInstance(session);
	Document dom = result.getDomDocument("settings");
	UserImpl user = new UserImpl(dom.getDocumentElement(), dom);

	// find node for location an injecting to current user
	NodeList displayList = dom.getElementsByTagName("location");
	if (displayList != null)
	{
	    if (displayList.getLength() > 0)
	    {
		Element location = (Element) displayList.item(0);
		user.setLocation(LocationFactory.createLocationFromElement(location, dom));
	    }
	}

	return user;
    }
 */
}
