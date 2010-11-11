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
package de.guxx.ttdroid.lib;

import android.database.sqlite.SQLiteDatabase;
import de.guxx.ttdroid.lib.exception.DatabaseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rdu
 */
public class Database
{

    private final String DATABASE = "/sdcard/ttdroid.db3";
    private SQLiteDatabase database;
    protected static final Logger logger = Logger.getLogger(Database.class.getName());

    private Database()
    {
	logger.info("database constructor called");
    }

    public static Database getInstance()
    {
	return DatabaseHolder.INSTANCE;
    }

    public SQLiteDatabase getDatabase() throws DatabaseException
    {
	if (database == null)
	{
	    logger.info("not yet initialized database");
	    database = SQLiteDatabase.openDatabase(DATABASE, null, SQLiteDatabase.OPEN_READWRITE + SQLiteDatabase.CREATE_IF_NECESSARY);
	    if (database == null)
		throw new DatabaseException("could not initialize database");
	}
	else
	{
	    logger.info("using pre initialized database");
	}
	return database;
    }

    private static class DatabaseHolder
    {

	private static final Database INSTANCE = new Database();
    }

    public void dispose() throws DatabaseException
    {
	logger.info("calling dispose database");
	if (database == null)
	{
	    logger.log(Level.SEVERE, "cannot close database - database null");
	    throw new DatabaseException("cannot close database - database null");
	}
	database.close();
	database = null;
    }
}
