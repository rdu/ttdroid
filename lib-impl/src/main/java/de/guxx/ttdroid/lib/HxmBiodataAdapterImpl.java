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

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import de.guxx.ttdroid.lib.exception.BiodataAdapterException;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rdu
 */
public class HxmBiodataAdapterImpl implements BiodataAdapter
{

    protected static final Logger logger = Logger.getLogger(HxmBiodataAdapterImpl.class.getName());
    private BioDataThread bioDataThread = null;

    private class BioDataThread extends Thread
    {
	private final UUID deviceUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
	private BluetoothAdapter bluetoothAdapter;
	private final BluetoothSocket bluetoothSocket;
	private final BluetoothDevice bluetoothDevice;

	public BioDataThread() throws Exception
	{
	    bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	    if (bluetoothAdapter == null)
	    {
		logger.info("device does not support bluetooth");
		throw new BiodataAdapterException("device does not support bluetooth");
	    }
	    logger.info("got bluetooth adapter");

	    if (!bluetoothAdapter.isEnabled())
	    {
		logger.info("bluetooth adapter not enabled");
		throw new BiodataAdapterException("need activated bluetooth for working");
	    }
	    logger.info("bluetooth is enabled");

	    bluetoothDevice = getHXMDevice();

	    logger.info("using device: " + bluetoothDevice.getName());
	    
	    BluetoothSocket tempSocket = null;
	    
	    try
	    {
		logger.info("try creating socket, device uuid: " + deviceUUID.toString());
		tempSocket = bluetoothDevice.createRfcommSocketToServiceRecord(deviceUUID);		
	    }
	    catch (Exception e)
	    {
		throw new BiodataAdapterException(e.getMessage());
	    }
	    
	    bluetoothSocket = tempSocket;
	    
	    logger.info("socket created successfully");
	}

	@Override
	public void run()
	{
	    try
	    {
		while (!isInterrupted())
		{
		    Thread.sleep(1000);
		    logger.info("sleept 1 sec in thread");
		}
	    }
	    catch (InterruptedException ie)
	    {
		logger.info("got interrupted exception msg: " + ie.getMessage());
	    }
	}

	private BluetoothDevice getHXMDevice() throws BiodataAdapterException
	{
	    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
	    logger.info("looking for paired devices");
	    if (pairedDevices.isEmpty()) throw new BiodataAdapterException("no paired devices");
	    for (BluetoothDevice device : pairedDevices)
	    {
		logger.info("probing device: " + device.getName() + ", " + device.getAddress());
		if (device.getName().startsWith("HXM"))
		{
		    return device;
		}
	    }
	    throw new BiodataAdapterException("found no matching paired device");
	}
    }

    @Override
    public void init() throws BiodataAdapterException
    {
	logger.info("calling init");
	try
	{
	    if (bioDataThread != null)
	    {
		if (!bioDataThread.isInterrupted() && bioDataThread.isAlive())
		{
		    logger.info("found another thread alive");
		    bioDataThread.interrupt();
		    logger.info("interrupted");
		}
	    }
	}
	catch (Exception e)
	{
	    logger.log(Level.WARNING, "exception while interrupting old thread msg: " + e.getMessage());
	}
	logger.info("inistantiating new thread");
	try
	{
	    bioDataThread = new BioDataThread();
	    bioDataThread.start();
	    logger.info("thread started");
	}
	catch (Exception e)
	{
	    logger.log(Level.SEVERE, "failed starting new thread, msg: " + e.getMessage());
	    throw new BiodataAdapterException(e.getMessage());
	}
    }

    @Override
    public BioData getBioData() throws BiodataAdapterException
    {
	logger.info("calling getBioData");
	return null;
    }

    @Override
    public void dispose() throws BiodataAdapterException
    {
	logger.info("calling dispose");
	try
	{
	    if (bioDataThread != null)
	    {
		if (!bioDataThread.isInterrupted() && bioDataThread.isAlive())
		{
		    bioDataThread.interrupt();
		}
		else
		{
		    logger.info("no alive interruptable thread found");
		}
	    }
	    else
	    {
		logger.info("no thread found");
	    }
	}
	catch (Exception e)
	{
	    logger.log(Level.SEVERE, "interrupting thread dies with msg: " + e.getMessage());
	}
    }
}
