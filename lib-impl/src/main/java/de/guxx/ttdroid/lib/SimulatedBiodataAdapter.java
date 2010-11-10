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

import de.guxx.ttdroid.lib.exception.BiodataAdapterException;
import java.util.Date;

/**
 *
 * @author rdu
 */
public class SimulatedBiodataAdapter implements BiodataAdapter
{

    private SimulatedDataThread thread;

    private class SimulatedDataThread extends Thread
    {

	private BioData bioData;

	public SimulatedDataThread()
	{
	    BioData bd = new BioData();
	    bd.setBatteryPercent(100);
	    bd.setBeatNumber(0);
	    bd.setTimestamp(new Date());
	    bd.setCadence(0);
	    bd.setDistance(0);
	    bd.setHeartRate(0);
	    bd.setSpeed(0);
	    bd.setStrides(0);
	    setBioData(bd);
	}

	@Override
	public void run()
	{
	    try
	    {
		int beat = 0;
		int distance = 0;
		int steps = 0;
		while (true)
		{
		    BioData oldData = getBioData();
		    BioData lbioData = new BioData();
		    lbioData.setTimestamp(new Date());
		    lbioData.setBatteryPercent(100);
		    lbioData.setBeatNumber(beat);
		    int cadence = (new Double(Math.random() * 100)).intValue();
		    lbioData.setCadence(cadence);
		    lbioData.setStrides(steps++);
		    
		    int mdiff = (int) (lbioData.getTimestamp().getTime() - oldData.getTimestamp().getTime());
		    int sdiff = mdiff / 1000;

		    Double lb = (Math.abs(Math.cos(steps) * Math.sin(steps)) / 2) + 1;
		    beat += lb * sdiff;
		    lbioData.setHeartRate(new Double(lb * 60000 / mdiff).intValue());
		    lbioData.setDistance(distance);
		    distance += Math.random() * 4;
		    lbioData.setSpeed(distance / steps);
		    setBioData(lbioData);
		    Thread.sleep(910 + (int) (Math.random() * 120));
		}
	    }
	    catch (InterruptedException ex)
	    {
	    }
	}

	public void cancel()
	{
	    this.interrupt();
	}

	private synchronized void setBioData(BioData bioData)
	{
	    this.bioData = bioData;
	}

	public synchronized BioData getBioData()
	{
	    return bioData;
	}
    }

    @Override
    public void init() throws BiodataAdapterException
    {
	try
	{
	    thread = new SimulatedDataThread();
	    thread.start();
	}
	catch (Exception e)
	{
	    throw new BiodataAdapterException(e);
	}
    }

    @Override
    public BioData getBioData() throws BiodataAdapterException
    {
	return thread.getBioData();
    }

    @Override
    public void dispose() throws BiodataAdapterException
    {
	try
	{
	    thread.cancel();
	}
	catch (Exception e)
	{
	    throw new BiodataAdapterException(e);
	}
    }
}
