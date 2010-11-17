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
package de.guxx.ttdroid.app;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import de.guxx.ttdroid.lib.BioData;
import de.guxx.ttdroid.lib.BiodataAdapter;
import de.guxx.ttdroid.lib.Database;
import de.guxx.ttdroid.lib.HxmBiodataAdapterImpl;
import de.guxx.ttdroid.lib.dao.SnapshotDao;
import de.guxx.ttdroid.lib.dao.SnapshotDaoImpl;
import de.guxx.ttdroid.lib.entity.Snapshot;
import de.guxx.ttdroid.lib.exception.BiodataAdapterException;
import de.guxx.ttdroid.lib.exception.DaoException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ronny Dudeck
 */
public class ttdoidAppActivity extends Activity implements View.OnClickListener
{

    protected static final Logger logger = Logger.getLogger(ttdoidAppActivity.class.getName());
    protected BluetoothAdapter bluetoothAdapter;
    protected BiodataAdapter biodataAdapter;
    private DataWriterThread writerThread;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	
	Button button = (Button)findViewById(R.id.sportListButton);
	button.setOnClickListener(this);
	
//	biodataAdapter = new HxmBiodataAdapterImpl();
	try
	{	    
//	    writerThread = new DataWriterThread(biodataAdapter);
	}
	catch (Exception e)
	{
	}
    }

    @Override
    protected void onResume()
    {
	try
	{
//	    biodataAdapter.init();
//	    writerThread.start();
	}
	catch (Exception ex)
	{
	    Logger.getLogger(ttdoidAppActivity.class.getName()).log(Level.SEVERE, null, ex);
	}
	super.onResume();
    }

    @Override
    protected void onPause()
    {
	try
	{
//	    biodataAdapter.dispose();
//	    writerThread.interrupt();
	    Database.getInstance().dispose();
	}
	catch (Exception ex)
	{
	    Logger.getLogger(ttdoidAppActivity.class.getName()).log(Level.SEVERE, null, ex);
	}
	super.onPause();
    }

    @Override
    public void onClick(View v)
    {
	Intent sportActivity = new Intent(this, SportListActivity.class); 
	startActivity(sportActivity);
    }

    private class DataWriterThread extends Thread
    {

	BiodataAdapter biodataAdapter;
	SnapshotDao snapshotDao;

	public DataWriterThread(BiodataAdapter biodataAdapter) throws DaoException
	{
	    this.biodataAdapter = biodataAdapter;
	    snapshotDao = new SnapshotDaoImpl();
	}

	@Override
	public void run()
	{
	    while (!isInterrupted())
	    {
		try
		{
		    Thread.sleep(1000);

		    Snapshot s = new Snapshot();
		    BioData bd = biodataAdapter.getBioData();
		    if (bd != null)
		    {
			s.setBattery(bd.getBatteryPercent());
			s.setCadence(bd.getCadence().intValue());
			s.setHeartrate(bd.getHeartRate());
			s.setLatitude(0.00f);
			s.setLongitude(0.00f);
			snapshotDao.insert(s);
		    }
		}
		catch (BiodataAdapterException ex)
		{
		    logger.info("error getting data from biodata adapter");
		}
		catch (DaoException ex)
		{
		    logger.info("error writing dataset");
		}
		catch (InterruptedException e)
		{
		    logger.info("writer thread interrupted");
		}
	    }
	}
    }
}
