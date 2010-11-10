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

import de.guxx.ttdroid.lib.Settings;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import static org.junit.Assert.*;

/**
 *
 * @author Ronny Dudeck
 */
public class TTXmlTest
{

    public TTXmlTest()
    {
    }

    @BeforeClass
    public static void setUpClass() throws Exception
    {
    }

    @AfterClass
    public static void tearDownClass() throws Exception
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of getInstance method, of class TTXml.
     */
    @Test
    public void testGetInstance()
    {
	String sessionId = "12345";
	TTXml result = TTXml.getInstance(sessionId);
	assertNotNull(result);
	TTXml result2 = TTXml.getInstance(sessionId);
	assertEquals(result, result2);
    }

    /**
     * Test of setSessionId method, of class TTXml.
     */
    @Test
    public void testSetSessionId()
    {
    }
   
    /**
     * Test of getDomDocument method, of class TTXml.
     */
    @Test
    public void testGetDomDocument()
    {
	String session = Settings.getSession();
	TTXml result = TTXml.getInstance(session);
	Document dom = result.getDomDocument("settings");
	assertNotNull(dom);
	assertEquals(1, dom.getElementsByTagName("session").getLength());
	Element e = (Element)dom.getElementsByTagName("session").item(0);
//	assertEquals(session, e.getTextContent());
    }

    @Test
    public void testBuildUrl()
    {
	TTXml result = TTXml.getInstance("1234");
	Map<String, Object>param = new HashMap<String, Object>();
	param.put("test", "test2");
	assertEquals("http://trainingstagebuch.org/settings?view=xml&sso=1234&test=test2", result.buildUrl("settings", param).toString());
    }
}
