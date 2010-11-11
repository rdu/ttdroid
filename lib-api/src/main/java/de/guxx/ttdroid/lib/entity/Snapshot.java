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

/**
 *
 * @author rdu
 */
@Table(name = "snapshot")
public class Snapshot
{

    @Column(name = "heartrate", sqlType = "integer")
    private Integer heartrate;
    @Column(name = "cadence", sqlType = "integer")
    private Integer cadence;
    @Column(name = "battery", sqlType = "integer")
    private Integer battery;
    @Column(name = "latitude", sqlType = "float")
    private Float latitude;
    @Column(name = "longitude", sqlType = "float")
    private Float longitude;

    public Integer getHeartrate()
    {
	return heartrate;
    }

    public void setHeartrate(Integer heartrate)
    {
	this.heartrate = heartrate;
    }

    public Integer getBattery()
    {
	return battery;
    }

    public void setBattery(Integer battery)
    {
	this.battery = battery;
    }

    public Integer getCadence()
    {
	return cadence;
    }

    public void setCadence(Integer cadence)
    {
	this.cadence = cadence;
    }

    public Float getLatitude()
    {
	return latitude;
    }

    public void setLatitude(Float latitude)
    {
	this.latitude = latitude;
    }

    public Float getLongitude()
    {
	return longitude;
    }

    public void setLongitude(Float longitude)
    {
	this.longitude = longitude;
    }
}
