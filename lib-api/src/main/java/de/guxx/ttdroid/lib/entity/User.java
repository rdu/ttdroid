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
    public enum Gender
    {
        m,
        f
    }
    public String getSession();
    public String getNick();
    public String getEMail();
    public String getForeName();
    public Gender getGender();
    public Integer getMaximumHeartRate();
    public Float getHeight();
    public String getIntroduction();
    public Location getLocation();
    public Display getDisplay();
    public Zones getZones();
    public Date getLastLogin();
}
