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
package de.guxx.ttdroid.lib.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

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
     * @param command 
     * @param parameter
     * @return
     */
    public Document getDomDocument(String command, Map<String, Object> parameter)
    {
        try
        {
            URL url = buildUrl(command, parameter);
            URLConnection uc = url.openConnection();

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(uc.getInputStream());
            return doc;
            
        } catch (Exception ex)
        {
            Logger.getLogger(TTXml.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * without parameter
    
     * @param command
     * @return
     */
    public Document getDomDocument(String command)
    {
        return getDomDocument(command, null);
    }

    /**
     *
     * @return
     */
    public String getSessionId()
    {
        return sessionId;
    }

    /**
     * creates the query string for the request
     * 
     * @param command
     * @param parameter
     * @return
     */
    public URL buildUrl(String command, Map<String, Object> parameter)
    {
        try
        {
            if (command.isEmpty())
            {
                return new URL(baseUrl);
            }
            StringBuilder url = new StringBuilder(baseUrl);
            url.append(command);
            url.append("?view=xml");
            url.append("&sso=");
            url.append(getSessionId());
            boolean first = true;
            if (parameter != null)
            {
                for (Map.Entry entry : parameter.entrySet())
                {
                    if (first)
                    {
                        url.append('&');
                        first = false;
                    }
                    url.append(entry.getKey());
                    url.append('=');
                    url.append(entry.getValue().toString());
                }
            }
            return new URL(url.toString());
        } catch (MalformedURLException ex)
        {
            Logger.getLogger(TTXml.class.getName()).log(Level.INFO, null, ex);
        }
        return null;
    }
}
