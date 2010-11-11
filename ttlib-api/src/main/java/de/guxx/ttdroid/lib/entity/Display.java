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
public class Display implements Serializable
{
    private Boolean bloodPressure;
    private Boolean bloodSugar;
    private Boolean distance;
    private Boolean heartrate;
    private Boolean ascent;
    private Boolean calories;
    private Boolean adipose;
    private Boolean bodyTemp;
    private Boolean power;
    private Boolean material;
    private Boolean muscleMass;
    private Boolean restHr;
    private Boolean sleep;
    private Boolean sport;
    private Boolean route;
    private Boolean waist;
    private Boolean borg;
    private Boolean cadence;
    private Boolean vo2Max;
    private Boolean bodyWater;
    private Boolean weather;

    /**
     * 
     * @return
     */
    public Boolean getAdipose()
    {
        return adipose;
    }

    /**
     * 
     * @param adipose
     */
    public void setAdipose(Boolean adipose)
    {
        this.adipose = adipose;
    }

    /**
     * 
     * @return
     */
    public Boolean getAscent()
    {
        return ascent;
    }

    /**
     * 
     * @param ascent
     */
    public void setAscent(Boolean ascent)
    {
        this.ascent = ascent;
    }

    /**
     * 
     * @return
     */
    public Boolean getBloodPressure()
    {
        return bloodPressure;
    }

    /**
     * 
     * @param bloodPressure
     */
    public void setBloodPressure(Boolean bloodPressure)
    {
        this.bloodPressure = bloodPressure;
    }

    /**
     * 
     * @return
     */
    public Boolean getBloodSugar()
    {
        return bloodSugar;
    }

    /**
     * 
     * @param bloodSugar
     */
    public void setBloodSugar(Boolean bloodSugar)
    {
        this.bloodSugar = bloodSugar;
    }

    /**
     * 
     * @return
     */
    public Boolean getBodyTemp()
    {
        return bodyTemp;
    }

    /**
     * 
     * @param bodyTemp
     */
    public void setBodyTemp(Boolean bodyTemp)
    {
        this.bodyTemp = bodyTemp;
    }

    /**
     * 
     * @return
     */
    public Boolean getBodyWater()
    {
        return bodyWater;
    }

    /**
     * 
     * @param bodyWater
     */
    public void setBodyWater(Boolean bodyWater)
    {
        this.bodyWater = bodyWater;
    }

    /**
     * 
     * @return
     */
    public Boolean getBorg()
    {
        return borg;
    }

    /**
     * 
     * @param borg
     */
    public void setBorg(Boolean borg)
    {
        this.borg = borg;
    }

    /**
     * 
     * @return
     */
    public Boolean getCadence()
    {
        return cadence;
    }

    /**
     * 
     * @param cadence
     */
    public void setCadence(Boolean cadence)
    {
        this.cadence = cadence;
    }

    /**
     * 
     * @return
     */
    public Boolean getCalories()
    {
        return calories;
    }

    /**
     * 
     * @param calories
     */
    public void setCalories(Boolean calories)
    {
        this.calories = calories;
    }

    /**
     * 
     * @return
     */
    public Boolean getDistance()
    {
        return distance;
    }

    /**
     * 
     * @param distance
     */
    public void setDistance(Boolean distance)
    {
        this.distance = distance;
    }

    /**
     * 
     * @return
     */
    public Boolean getHeartrate()
    {
        return heartrate;
    }

    /**
     * 
     * @param heartrate
     */
    public void setHeartrate(Boolean heartrate)
    {
        this.heartrate = heartrate;
    }

    /**
     * 
     * @return
     */
    public Boolean getMaterial()
    {
        return material;
    }

    /**
     * 
     * @param material
     */
    public void setMaterial(Boolean material)
    {
        this.material = material;
    }

    /**
     * 
     * @return
     */
    public Boolean getMuscleMass()
    {
        return muscleMass;
    }

    /**
     * 
     * @param muscleMass
     */
    public void setMuscleMass(Boolean muscleMass)
    {
        this.muscleMass = muscleMass;
    }

    /**
     * 
     * @return
     */
    public Boolean getPower()
    {
        return power;
    }

    /**
     * 
     * @param power
     */
    public void setPower(Boolean power)
    {
        this.power = power;
    }

    /**
     * 
     * @return
     */
    public Boolean getRestHr()
    {
        return restHr;
    }

    /**
     * 
     * @param restHr
     */
    public void setRestHr(Boolean restHr)
    {
        this.restHr = restHr;
    }

    /**
     * 
     * @return
     */
    public Boolean getRoute()
    {
        return route;
    }

    /**
     * 
     * @param route
     */
    public void setRoute(Boolean route)
    {
        this.route = route;
    }

    /**
     * 
     * @return
     */
    public Boolean getSleep()
    {
        return sleep;
    }

    /**
     * 
     * @param sleep
     */
    public void setSleep(Boolean sleep)
    {
        this.sleep = sleep;
    }

    /**
     * 
     * @return
     */
    public Boolean getSport()
    {
        return sport;
    }

    /**
     * 
     * @param sport
     */
    public void setSport(Boolean sport)
    {
        this.sport = sport;
    }

    /**
     * 
     * @return
     */
    public Boolean getVo2Max()
    {
        return vo2Max;
    }

    /**
     * 
     * @param vo2Max
     */
    public void setVo2Max(Boolean vo2Max)
    {
        this.vo2Max = vo2Max;
    }

    /**
     * 
     * @return
     */
    public Boolean getWaist()
    {
        return waist;
    }

    /**
     * 
     * @param waist
     */
    public void setWaist(Boolean waist)
    {
        this.waist = waist;
    }

    /**
     * 
     * @return
     */
    public Boolean getWeather()
    {
        return weather;
    }

    /**
     * 
     * @param weather
     */
    public void setWeather(Boolean weather)
    {
        this.weather = weather;
    }
}
