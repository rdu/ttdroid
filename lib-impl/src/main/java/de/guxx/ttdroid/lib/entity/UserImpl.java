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

import java.util.Date;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author rdu
 */
public class UserImpl extends GenericEntity implements User
{

    private UserImpl()
    {
	super(null, null);
    }
 
    public UserImpl(Element rootElement, Document document)
    {
	super(rootElement, document);
    }
    
    @Override
    public String getSession()
    {
	return getString("session");
    }

    @Override
    public String getNick()
    {
	return getString("nick");
    }

    @Override
    public String getEMail()
    {
	return getString("email");
    }

    @Override
    public String getForeName()
    {
	return getString("forename");
    }

    @Override
    public String getSurName()
    {
	return getString("surname");
    }

    @Override
    public Gender getGender()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer getMaximumHeartRate()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Float getHeight()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getIntroduction()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Location getLocation()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Display getDisplay()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Zones getZones()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date getLastLogin()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }
}
