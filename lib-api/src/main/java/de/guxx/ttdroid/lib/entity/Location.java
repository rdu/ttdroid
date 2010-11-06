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

/**
 *
 * @author Ronny Dudeck
 */
public class Location implements Serializable
{
    private Double latitude;
    private Double longitude;
    private String name;

    /**
     * 
     * @return
     */
    public Double getLatitude()
    {
        return latitude;
    }

    /**
     * 
     * @param latitude
     */
    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    /**
     * 
     * @return
     */
    public Double getLongitude()
    {
        return longitude;
    }

    /**
     * 
     * @param longitude
     */
    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    /**
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * 
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
}
