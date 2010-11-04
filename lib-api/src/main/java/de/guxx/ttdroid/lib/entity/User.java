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

/**
 *
 * @author Ronny Dudeck
 */
public interface User
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

    /**
     * 
     * @return
     */
    public String getSession();

    /**
     * 
     * @return
     */
    public String getNick();

    /**
     * 
     * @return
     */
    public String getEMail();

    /**
     * 
     * @return
     */
    public String getForeName();

    /**
     * 
     * @return
     */
    public String getSurName();

    /**
     * 
     * @return
     */
    public Gender getGender();

    /**
     * 
     * @return
     */
    public Integer getMaximumHeartRate();

    /**
     * 
     * @return
     */
    public Float getHeight();

    /**
     * 
     * @return
     */
    public String getIntroduction();

    /**
     * 
     * @return
     */
    public Location getLocation();

    /**
     * 
     * @return
     */
    public Display getDisplay();

    /**
     * 
     * @return
     */
    public Zones getZones();

    /**
     * 
     * @return
     */
    public Date getLastLogin();
}
