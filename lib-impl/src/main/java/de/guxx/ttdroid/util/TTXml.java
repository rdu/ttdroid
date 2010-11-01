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
package de.guxx.ttdroid.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

/**
 *
 * @author Ronny Dudeck
 */
public class TTXml
{
    /*
     * constants
     */

    private final String baseUrl = "http://trainingstagebuch.org/";
    /*
     * private member variables
     */
    private String sessionId;
    private URLConnection connection;

    private TTXml()
    {
    }

    /**
     *
     * getInstance
     * singleton design pattern
     *
     * @param sessionId
     * @return
     */
    public static TTXml getInstance(String sessionId)
    {
	TTXmlHolder.INSTANCE.setSessionId(sessionId);
	return TTXmlHolder.INSTANCE;
    }

    private static class TTXmlHolder
    {

	private static final TTXml INSTANCE = new TTXml();
    }

    /**
     *
     * set current tt session id
     *
     * @param sessionId
     */
    public void setSessionId(String sessionId)
    {
	this.sessionId = sessionId;
    }

    /**
     * this method connects the tt-server via http,
     * submits specified parameter values
     * and returns the parsed document.
     *
     * @param parameter
     * @return
     */
    public Document getDomDocument(Map<String, Object> parameter)
    {
	URL url = buildUrl(parameter);
	return null;
    }

    private URL buildUrl(Map<String, Object> parameter)
    {
	try
	{
	    if (parameter == null) return new URL(baseUrl);
	    if (parameter.isEmpty()) return new URL(baseUrl);
	    StringBuilder url = new StringBuilder(baseUrl);
	    url.append("?");
	    for (Map.Entry entry : parameter.entrySet())
	    {
		url.append(entry.getKey());
		url.append('=');
		url.append(entry.getValue().toString());
	    }
	    return new URL(url.toString());
	}
	catch (MalformedURLException ex)
	{
	    Logger.getLogger(TTXml.class.getName()).log(Level.INFO, null, ex);
	}
	return null;
    }
}
