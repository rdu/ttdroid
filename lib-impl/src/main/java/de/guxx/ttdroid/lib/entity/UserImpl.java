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

    private Location location;

    private UserImpl()
    {
	super(null, null);
    }

    /**
     * 
     * @param rootElement
     * @param document
     */
    public UserImpl(Element rootElement, Document document)
    {
	super(rootElement, document);
    }

    /**
     * 
     * @return
     */
    @Override
    public String getSession()
    {
	return getString("session");
    }

    /**
     * 
     * @return
     */
    @Override
    public String getNick()
    {
	return getString("nick");
    }

    /**
     * 
     * @return
     */
    @Override
    public String getEMail()
    {
	return getString("email");
    }

    /**
     * 
     * @return
     */
    @Override
    public String getForeName()
    {
	return getString("forename");
    }

    /**
     * 
     * @return
     */
    @Override
    public String getSurName()
    {
	return getString("surname");
    }

    /**
     * 
     * @return
     */
    @Override
    public Gender getGender()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @return
     */
    @Override
    public Integer getMaximumHeartRate()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @return
     */
    @Override
    public Float getHeight()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @return
     */
    @Override
    public String getIntroduction()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @return
     */
    @Override
    public Location getLocation()
    {
	return location;
    }

    /**
     * 
     * @return
     */
    @Override
    public Display getDisplay()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @return
     */
    @Override
    public Zones getZones()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @return
     */
    @Override
    public Date getLastLogin()
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param location
     */
    public void setLocation(Location location)
    {
	this.location = location;
    }
}
