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

package de.guxx.ttdroid.lib;

/**
 *
 * @author rdu
 */
public class BioData
{
    private Integer heartRate;
    private Integer beatNumber;
    private Integer batteryPercent;
    private Integer strides;
    private Integer distance;
    private Integer speed;
    private Integer cadence;

    /**
     * 
     * @return
     */
    public Integer getBatteryPercent()
    {
        return batteryPercent;
    }

    /**
     * 
     * @param batteryPercent
     */
    public void setBatteryPercent(Integer batteryPercent)
    {
        this.batteryPercent = batteryPercent;
    }

    /**
     * 
     * @return
     */
    public Integer getBeatNumber()
    {
        return beatNumber;
    }

    /**
     * 
     * @param beatNumber
     */
    public void setBeatNumber(Integer beatNumber)
    {
        this.beatNumber = beatNumber;
    }

    /**
     * 
     * @return
     */
    public Integer getCadence()
    {
        return cadence;
    }

    /**
     * 
     * @param cadence
     */
    public void setCadence(Integer cadence)
    {
        this.cadence = cadence;
    }

    /**
     * 
     * @return
     */
    public Integer getDistance()
    {
        return distance;
    }

    /**
     * 
     * @param distance
     */
    public void setDistance(Integer distance)
    {
        this.distance = distance;
    }

    /**
     * 
     * @return
     */
    public Integer getHeartRate()
    {
        return heartRate;
    }

    /**
     * 
     * @param heartRate
     */
    public void setHeartRate(Integer heartRate)
    {
        this.heartRate = heartRate;
    }

    /**
     * 
     * @return
     */
    public Integer getSpeed()
    {
        return speed;
    }

    /**
     * 
     * @param speed
     */
    public void setSpeed(Integer speed)
    {
        this.speed = speed;
    }

    /**
     * 
     * @return
     */
    public Integer getStrides()
    {
        return strides;
    }

    /**
     * 
     * @param strides
     */
    public void setStrides(Integer strides)
    {
        this.strides = strides;
    }
}
