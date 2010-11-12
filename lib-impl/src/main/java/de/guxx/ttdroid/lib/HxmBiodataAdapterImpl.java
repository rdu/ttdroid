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
import java.io.IOException;
import java.io.InputStream;
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

	    logger.info("try'in connect to device");

	    try
	    {
		bluetoothSocket.connect();
		logger.info("successful connected to device");
	    }
	    catch (IOException e)
	    {
		logger.severe("could not connect to device");
	    }
	}

	@Override
	public void run()
	{
	    logger.info("running thread");
	    try
	    {
		try
		{
		    InputStream in = bluetoothSocket.getInputStream();

		    HxmReader hr = new HxmReader(in);
		    logger.info("init hxmreader reader");

		    while (!isInterrupted())
		    {
			HxmPaket paket = hr.read();
			paket.validate();
			logger.info(paket.toString());
		    }
		}
		catch (IOException ex)
		{
		    logger.severe("could not get input stream");
		}
	    }
	    catch (Exception ie)
	    {
		logger.info("got interrupted exception msg: " + ie.getMessage());
	    }
	}

	private BluetoothDevice getHXMDevice() throws BiodataAdapterException
	{
	    Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
	    logger.info("looking for paired devices");
	    if (pairedDevices.isEmpty())
		throw new BiodataAdapterException("no paired devices");
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

	public void dispose()
	{
	    if (bluetoothSocket != null)
	    {
		try
		{
		    bluetoothSocket.close();
		    logger.info("socket closed");
		}
		catch (IOException ex)
		{
		    logger.severe("error closing socket");
		}
	    }
	}

	public class HxmPaket
	{

	    private final static int checksumPolynomial = 0x8c;
	    private final static byte STX = 0x02;
	    private final static byte ETX = 0x03;
	    private final static byte HXM_ID = 0x26;
	    private final static byte HXM_DLC = 0x37;
	    private byte[] data;
	    private int dataSize;

	    public HxmPaket(byte[] data, int dataSize)
	    {
		this.data = data;
		this.dataSize = dataSize;
	    }

	    public int getBeat()
	    {
		return getUnsigned(data[13]);
	    }

	    public int getHeartBeat()
	    {
		return getUnsigned(data[12]);
	    }

	    public int getBattery()
	    {
		return getUnsigned(data[11]);
	    }

	    private int getUnsigned(byte b)
	    {
		return (int) b & 0xff;
	    }

	    public int getStrides()
	    {
		return getUnsigned(data[54]);
	    }

	    public Double getDistance()
	    {
		return ((double) getMergedUnsigned(data[50], data[51])) / 16d;
	    }

	    public Double getSpeed()
	    {
		return ((double) getMergedUnsigned(data[52], data[53])) / 256d;
	    }

	    public Double getCadence()
	    {
		return ((double) getMergedUnsigned(data[54], data[55])) / 16d;
	    }

	    private int getMergedUnsigned(byte b1, byte b2)
	    {
		int lint = b1 & 0xff;
		int hint = b2 & 0xff;
		return (int) (hint << 8 | lint);
	    }

	    @Override
	    public String toString()
	    {
		StringBuilder sb = new StringBuilder();

		sb.append("hxmPaket: len: ");
		sb.append(dataSize);
		sb.append(", ");

		sb.append("heartbeat: ");
		sb.append(getHeartBeat());
		sb.append(", ");

		sb.append("beatnumber: ");
		sb.append(getBeat());
		sb.append(", ");

		sb.append("battery: ");
		sb.append(getBattery());
		sb.append("\n");


		sb.append("strides: ");
		sb.append(getStrides());
		sb.append(", ");

		sb.append("speed: ");
		sb.append(getSpeed());
		sb.append(", ");

		sb.append("distance: ");
		sb.append(getDistance());
		sb.append(", ");

		sb.append("cadence: ");
		sb.append(getCadence());
		sb.append("\n");

		return sb.toString();
	    }

	    public boolean validate()
	    {
		if (dataSize != 60)
		{
		    System.out.println("error: datasize");
		    return false;
		}

		if (data[0] != STX)
		{
		    System.out.println("error STX");
		    return false;
		}

		if (data[1] != HXM_ID)
		{
		    System.out.println("error HXM_ID");
		    return false;
		}

		if (data[2] != HXM_DLC)
		{
		    System.out.println("error HXM_DLC");
		    return false;
		}

		if (data[59] != ETX)
		{
		    System.out.println("error ETX");
		    return false;
		}

		if (!isCRC())
		{
		    System.out.println("error CRC");
		    return false;
		}

		return true;
	    }

	    private boolean isCRC()
	    {
		int crc = 0;

		for (int i = 3; i < 58; i++)
		{
		    crc = crcPushByte(crc, ((int) data[i] & 0xff));
		}
		StringBuilder sb = new StringBuilder("crc: " + crc + " == ");
		sb.append((new Integer((int) data[58] & 0xff)));
		System.out.println(sb.toString());
		return (crc == ((int) data[58] & 0xff));
	    }

	    private int crcPushByte(int crc, int b)
	    {
		crc = (crc ^ b);

		for (int t = 0; t < 8; t++)
		{
		    if ((crc & 1) == 1)
		    {
			crc = ((crc >> 1) ^ checksumPolynomial);
		    }
		    else
		    {
			crc = (crc >> 1);
		    }
		}
		return crc;
	    }
	}

	private class HxmReader
	{

	    public InputStream stream;

	    public HxmReader(InputStream stream)
	    {
		this.stream = stream;
	    }

	    public HxmPaket read() throws IOException
	    {
		byte[] array = new byte[60];
		int size = stream.read(array);
		while (size < 60)
		{
		    size += stream.read(array, size, 60 - size);
		}
		return new HxmPaket(array, size);
	    }
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
		    bioDataThread.dispose();
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
