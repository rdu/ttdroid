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
public class Range implements Serializable
{
    private Integer ga1;
    private Integer ga2;
    private Integer eb;
    private Integer sb;

    /**
     * 
     * @return
     */
    public Integer getEb()
    {
        return eb;
    }

    /**
     * 
     * @param eb
     */
    public void setEb(Integer eb)
    {
        this.eb = eb;
    }

    /**
     * 
     * @return
     */
    public Integer getGa1()
    {
        return ga1;
    }

    /**
     * 
     * @param ga1
     */
    public void setGa1(Integer ga1)
    {
        this.ga1 = ga1;
    }

    /**
     * 
     * @return
     */
    public Integer getGa2()
    {
        return ga2;
    }

    /**
     * 
     * @param ga2
     */
    public void setGa2(Integer ga2)
    {
        this.ga2 = ga2;
    }

    /**
     * 
     * @return
     */
    public Integer getSb()
    {
        return sb;
    }

    /**
     * 
     * @param sb
     */
    public void setSb(Integer sb)
    {
        this.sb = sb;
    }
}
