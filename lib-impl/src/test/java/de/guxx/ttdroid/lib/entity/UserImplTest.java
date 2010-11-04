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

import de.guxx.ttdroid.lib.Settings;
import de.guxx.ttdroid.lib.UserFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rdu
 */
public class UserImplTest
{

    public UserImplTest()
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

    @Test
    public void testGetter()
    {
	try
	{
	    User user = UserFactory.getCurrentUser();
	    testString(user.getSession(), Settings.getSession());
	    testString(user.getNick());
	    assertNotSame(user.getNick(), Settings.getSession());
	    testString(user.getEMail());
	    testString(user.getForeName());
	}
	catch (Exception e)
	{
	    fail(e.getLocalizedMessage());
	}
    }

    private void testString(String string, String assertation)
    {
	assertTrue(string != null);
	assertTrue(!string.isEmpty());
	assertEquals(assertation, string);
    }

    private void testString(String string)
    {
	assertTrue(string != null);
	assertTrue(!string.isEmpty());
    }
}
