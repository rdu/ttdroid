package de.guxx.ttdroid.lib.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rdu
 */
public class EntityCache
{

    private final String cacheDir;

    private EntityCache()
    {
	cacheDir = Settings.getCacheDir();
	File f = new File(cacheDir);
	if (!f.exists())
	{
	    try
	    {
		f.mkdirs();
	    }
	    catch (Exception e)
	    {
	    }
	}
    }

    public static EntityCache getInstance()
    {
	return EntityCacheHolder.INSTANCE;
    }

    private static class EntityCacheHolder
    {

	private static final EntityCache INSTANCE = new EntityCache();
    }

    protected File getFile(String key)
    {
	return new File(cacheDir + File.separator + key + ".dat");
    }

    public void putToCache(String key, Object object) throws IOException
    {
	FileOutputStream fos = null;
	fos = new FileOutputStream(getFile(key));
	ObjectOutputStream o = new ObjectOutputStream(fos);
	Container c = new Container();
	c.setObject(object);
	o.writeObject(c);
	fos.close();
    }


    public Object readFromCache(String key) throws FileNotFoundException, IOException, ClassNotFoundException
    {
	FileInputStream fis = null;
	fis = new FileInputStream(getFile(key));
	ObjectInputStream o = new ObjectInputStream(fis);
	Container c = (Container) o.readObject();	
	fis.close();
	return c.getObject();
    }

    public Boolean isCached(String key)
    {
	if (getFile(key).exists())
	{
	    try
	    {
		FileInputStream fis = null;
		fis = new FileInputStream(getFile(key));
		ObjectInputStream o = new ObjectInputStream(fis);
		Container c = (Container) o.readObject();
		fis.close();
		return c.isValid();
	    }
	    catch (Exception e)
	    {
		getFile(key).delete();
	    }
	}
	return false;
    }
}
