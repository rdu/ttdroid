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
 * @author rdu
 */
public class Sport
{

    private String name;
    private Integer iconId;
    private String iconImage16x16;
    private String iconImage32x32;
    private String iconImage64x64;
    private String description;
    private Date lastChange;

    public String getDescription()
    {
	return description;
    }

    public void setDescription(String description)
    {
	this.description = description;
    }

    public Integer getIconId()
    {
	return iconId;
    }

    public void setIconId(Integer iconId)
    {
	this.iconId = iconId;
    }

    public String getIconImage16x16()
    {
	return iconImage16x16;
    }

    public void setIconImage16x16(String iconImage16x16)
    {
	this.iconImage16x16 = iconImage16x16;
    }

    public String getIconImage32x32()
    {
	return iconImage32x32;
    }

    public void setIconImage32x32(String iconImage32x32)
    {
	this.iconImage32x32 = iconImage32x32;
    }

    public String getIconImage64x64()
    {
	return iconImage64x64;
    }

    public void setIconImage64x64(String iconImage64x64)
    {
	this.iconImage64x64 = iconImage64x64;
    }

    public Date getLastChange()
    {
	return lastChange;
    }

    public void setLastChange(Date lastChange)
    {
	this.lastChange = lastChange;
    }

    public String getName()
    {
	return name;
    }

    public void setName(String name)
    {
	this.name = name;
    }
}
