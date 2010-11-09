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

package de.guxx.ttdroid.lib.exception;

/**
 *
 * @author rdu
 */
public class BiodataAdapterException extends Exception
{
    /**
     * Creates a new instance of <code>BiodataAdapterException</code> without detail message.
     */
    public BiodataAdapterException()
    {
    }

    /**
     * Constructs an instance of <code>BiodataAdapterException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public BiodataAdapterException(String msg)
    {
        super(msg);
    }
}
