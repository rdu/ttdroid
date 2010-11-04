package de.guxx.ttdroid.lib.entity;

import de.guxx.ttdroid.lib.Settings;
import de.guxx.ttdroid.lib.UserFactory;
import de.guxx.ttdroid.lib.entity.User.Gender;
import java.util.Date;
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
