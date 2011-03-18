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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.guxx.ttdroid.lib.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rdu
 */
public class Container implements Serializable
{

    private Object object;
    private Date TTL;

    public Container()
    {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.SECOND, Settings.getCacheTTL());
	TTL = cal.getTime();
    }

    public void setObject(Object object)
    {
	this.object = object;
    }

    public Object getObject()
    {
	return object;
    }

    public Boolean isValid()
    {
	return TTL.after(new Date());
    }
}
