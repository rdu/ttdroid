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
package de.guxx.ttdroid.lib.dao;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import de.guxx.ttdroid.lib.Database;
import de.guxx.ttdroid.lib.entity.Column;
import de.guxx.ttdroid.lib.entity.Table;
import de.guxx.ttdroid.lib.exception.DaoException;
import de.guxx.ttdroid.lib.exception.DatabaseException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rdu
 */
public abstract class GenericDao<C, K> implements Dao<C, K>
{
    protected static final Logger logger = Logger.getLogger(GenericDao.class.getName());

    private SQLiteDatabase database;
    private Map<String, Field>columnMap = new HashMap<String, Field>();

    public GenericDao() throws DaoException
    {
	createFields();
	
	try
	{
	    database = Database.getInstance().getDatabase();
	    try
	    {
		database.execSQL("select * from " + getTableName());
	    }
	    catch(SQLException se)
	    {
		createTable();
	    }
	}
	catch (DatabaseException ex)
	{
	    logger.severe(ex.getMessage());
	    throw new DaoException(ex);
	}
    }

    @Override
    public void insert(C object) throws DaoException
    {
	logger.info("inserting into table: " + getTableName());	
	
	StringBuilder sb = new StringBuilder("insert into ");
	sb.append(getTableName());
	sb.append(" (");
	for (Map.Entry<String, Field> entry : columnMap.entrySet())
	{
	    Column c = entry.getValue().getAnnotation(Column.class);
	    if (!c.primary()) sb.append(c.name());
	    else sb.append("_id");
	    sb.append(", ");
	}
	sb.delete(sb.length()-2, sb.length());
	sb.append(") values(");
	for (Map.Entry<String, Field> entry : columnMap.entrySet())
	{
	    Field f = entry.getValue();
	    f.setAccessible(true);
	    try
	    {
		sb.append("'").append(f.get(object)).append("'").append(", ");
	    }
	    catch (IllegalArgumentException ex)
	    {
		logger.info("illegal argument while accessing field:" + f.getName());
	    }
	    catch (IllegalAccessException ex)
	    {
		logger.info("illegal access while accessing field:" + f.getName());
	    }
	    f.setAccessible(false);
	}
	sb.delete(sb.length()-2, sb.length());
	sb.append(");");
	
	logger.info("insert query: " + sb.toString());
	
	try
	{
	    database.execSQL(sb.toString());
	}
	catch(Exception e)
	{
	    throw new DaoException("could not insert: " + e.getMessage());
	}
    }

    @Override
    public C getByFK(K key) throws DaoException
    {
	throw new UnsupportedOperationException("Not supported yet.");
    }
  
    protected abstract Class getParameterClass();
    
    protected String getTableName() throws DaoException
    {
	Class c = getParameterClass();
	if (c == null) throw new DaoException("no table name defined");
	Table t = (Table) c.getAnnotation(Table.class);
	return t.name();
    }

    private void createFields()
    {
	logger.info("field count: " + getParameterClass().getDeclaredFields().length);	
	for (Field field : getParameterClass().getDeclaredFields())
	{
	    logger.info("field: " + field.getName());	    
	    Column c = field.getAnnotation(Column.class);
	    if (c != null)
	    {
		logger.info("--> annotation column: " + c.name());
		columnMap.put(c.name(), field);
	    }
	}	
    }
    
    private void createTable() throws DaoException
    {
	logger.info("create table: " + getTableName());	
	
	StringBuilder sb = new StringBuilder("create table ");
	sb.append(getTableName());
	sb.append(" (");
	for (Map.Entry<String, Field> entry : columnMap.entrySet())
	{
	    Column c = entry.getValue().getAnnotation(Column.class);
	    if (!c.primary()) sb.append(c.name());
	    else sb.append("_id");
	    sb.append(" ");
	    sb.append(c.sqlType());
	    if (c.primary()) sb.append(" primary key");
	    if (c.autoincrement()) sb.append(" autoincrement");
	    if (c.notnull()) sb.append(" not null");
	    else sb.append(" null");
	    sb.append(", ");
	}
	sb.delete(sb.length()-2, sb.length());
	sb.append(");");
	
	logger.info("create query: " + sb.toString());
	
	try
	{
	    database.execSQL(sb.toString());
	}
	catch(Exception e)
	{
	    throw new DaoException("could not create table: " + e.getMessage());
	}
    }
}
