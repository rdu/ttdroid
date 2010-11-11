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

import de.guxx.ttdroid.lib.util.Settings;
import de.guxx.ttdroid.lib.entity.Location;
import de.guxx.ttdroid.lib.entity.User;
import de.guxx.ttdroid.lib.exception.ElementNotFoundException;
import de.guxx.ttdroid.lib.util.TTXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author rdu
 */
public class UserDaoImpl extends GenericTTXMLDaoImpl<User> implements UserDao
{
    @Override
    protected User read()
    {
        String session = Settings.getSession();
        TTXml result = TTXml.getInstance(session);
        Document dom = result.getDomDocument("settings");

        User user = readUser(dom.getDocumentElement());

        return user;
    }

    private User readUser(Element element)
    {
        User user = new User();
        
        user.setSession(getString(element, "session"));
        user.setEmail(getString(element, "email"));
        user.setForeName(getString(element, "forename"));
        user.setSurName(getString(element, "surname"));
        user.setNick(getString(element, "nick"));
        user.setIntroduction(getString(element, "introduction"));
        user.setHeight(getDouble(element, "height").floatValue());
                
        String g = getString(element, "gender");
        if (g.equals("m")) user.setGender(User.Gender.m);
        if (g.equals("f")) user.setGender(User.Gender.f);
       
        try
        {
            LocationDao locationDao = new LocationDaoImpl(getFirstElement(element, "location"));
            Location location = locationDao.get();
            user.setLocation(location);
        }
        catch (ElementNotFoundException e)
        {            
        }
        
        return user;
    }
}
