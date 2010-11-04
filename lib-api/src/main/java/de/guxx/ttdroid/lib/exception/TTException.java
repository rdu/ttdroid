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
public class TTException extends Throwable
{

    /**
     * 
     * @param cause
     */
    public TTException(Throwable cause)
    {
	super(cause);
    }

    /**
     * 
     * @param message
     * @param cause
     */
    public TTException(String message, Throwable cause)
    {
	super(message, cause);
    }

    /**
     * 
     * @param message
     */
    public TTException(String message)
    {
	super(message);
    }

    /**
     * 
     */
    public TTException()
    {
    }
}
