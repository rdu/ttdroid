/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.guxx.ttdroid.lib.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author rdu
 */
public class Container implements Serializable
{

    private Object object;
    private Date TTL;

    public Container()
    {
	Calendar cal = Calendar.getInstance();
	cal.add(Calendar.SECOND, Settings.getCacheTTL());
	TTL = cal.getTime();
    }

    public void setObject(Object object)
    {
	this.object = object;
    }

    public Object getObject()
    {
	return object;
    }

    public Boolean isValid()
    {
	return TTL.after(new Date());
    }
}
