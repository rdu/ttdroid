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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
