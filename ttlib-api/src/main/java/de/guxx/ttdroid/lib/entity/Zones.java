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
public class Zones implements Serializable
{
    private Range power;
    private Range heartRate;

    /**
     * 
     * @return
     */
    public Range getHeartRate()
    {
        return heartRate;
    }

    /**
     * 
     * @param heartRate
     */
    public void setHeartRate(Range heartRate)
    {
        this.heartRate = heartRate;
    }

    /**
     * 
     * @return
     */
    public Range getPower()
    {
        return power;
    }

    /**
     * 
     * @param power
     */
    public void setPower(Range power)
    {
        this.power = power;
    }
}
