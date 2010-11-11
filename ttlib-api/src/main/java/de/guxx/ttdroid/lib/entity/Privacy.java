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
public class Privacy implements Serializable
{
    /**
     * 
     */
    public enum Publicly
    {
        /**
         * private
         */
        PRIVATE(0),
        /**
         * public
         */
        PUBLIC(1);
        private Integer ordinal;

        private Publicly(Integer ordinal)
        {
            this.ordinal = ordinal;
        }
    }
    private Publicly _public;
    private Publicly location;
    private Publicly comment;

    /**
     * 
     * @return
     */
    public Publicly getPublic()
    {
        return _public;
    }

    /**
     * 
     * @param _public
     */
    public void setPublic(Publicly _public)
    {
        this._public = _public;
    }

    /**
     * 
     * @return
     */
    public Publicly getComment()
    {
        return comment;
    }

    /**
     * 
     * @param comment
     */
    public void setComment(Publicly comment)
    {
        this.comment = comment;
    }

    /**
     * 
     * @return
     */
    public Publicly getLocation()
    {
        return location;
    }

    /**
     * 
     * @param location
     */
    public void setLocation(Publicly location)
    {
        this.location = location;
    }
}
