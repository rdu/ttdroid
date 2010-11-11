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

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ronny Dudeck
 */
public class User implements Serializable
{
    /**
     * 
     */
    public enum Gender
    {
        /**
         * male
         */
        m,
        /**
         * female
         */
        f
    }
    private String session;
    private String nick;
    private String email;
    private String foreName;
    private String surName;
    private Gender gender;
    private Integer maximumHeartRate;
    private Float height;
    private String introduction;
    private Location location;
    private Display display;
    private Zones zones;
    private Date lastLogin;

    /**
     * 
     * @return
     */
    public Display getDisplay()
    {
        return display;
    }

    /**
     * 
     * @param display
     */
    public void setDisplay(Display display)
    {
        this.display = display;
    }

    /**
     * 
     * @return
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * 
     * @return
     */
    public String getForeName()
    {
        return foreName;
    }

    /**
     * 
     * @param foreName
     */
    public void setForeName(String foreName)
    {
        this.foreName = foreName;
    }

    /**
     * 
     * @return
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * 
     * @param gender
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     * 
     * @return
     */
    public Float getHeight()
    {
        return height;
    }

    /**
     * 
     * @param height
     */
    public void setHeight(Float height)
    {
        this.height = height;
    }

    /**
     * 
     * @return
     */
    public String getIntroduction()
    {
        return introduction;
    }

    /**
     * 
     * @param introduction
     */
    public void setIntroduction(String introduction)
    {
        this.introduction = introduction;
    }

    /**
     * 
     * @return
     */
    public Date getLastLogin()
    {
        return lastLogin;
    }

    /**
     * 
     * @param lastLogin
     */
    public void setLastLogin(Date lastLogin)
    {
        this.lastLogin = lastLogin;
    }

    /**
     * 
     * @return
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * 
     * @param location
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }

    /**
     * 
     * @return
     */
    public Integer getMaximumHeartRate()
    {
        return maximumHeartRate;
    }

    /**
     * 
     * @param maximumHeartRate
     */
    public void setMaximumHeartRate(Integer maximumHeartRate)
    {
        this.maximumHeartRate = maximumHeartRate;
    }

    /**
     * 
     * @return
     */
    public String getNick()
    {
        return nick;
    }

    /**
     * 
     * @param nick
     */
    public void setNick(String nick)
    {
        this.nick = nick;
    }

    /**
     * 
     * @return
     */
    public String getSession()
    {
        return session;
    }

    /**
     * 
     * @param session
     */
    public void setSession(String session)
    {
        this.session = session;
    }

    /**
     * 
     * @return
     */
    public String getSurName()
    {
        return surName;
    }

    /**
     * 
     * @param surName
     */
    public void setSurName(String surName)
    {
        this.surName = surName;
    }

    /**
     * 
     * @return
     */
    public Zones getZones()
    {
        return zones;
    }

    /**
     * 
     * @param zones
     */
    public void setZones(Zones zones)
    {
        this.zones = zones;
    }
}
