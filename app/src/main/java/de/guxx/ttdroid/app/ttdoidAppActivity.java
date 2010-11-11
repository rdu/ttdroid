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
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import de.guxx.ttdroid.lib.BiodataAdapter;
import de.guxx.ttdroid.lib.Database;
import de.guxx.ttdroid.lib.HxmBiodataAdapterImpl;
import de.guxx.ttdroid.lib.dao.SnapshotDao;
import de.guxx.ttdroid.lib.dao.SnapshotDaoImpl;
import de.guxx.ttdroid.lib.entity.Snapshot;
import de.guxx.ttdroid.lib.exception.BiodataAdapterException;
import de.guxx.ttdroid.lib.exception.DaoException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.Socket;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ronny Dudeck
 */
public class ttdoidAppActivity extends Activity
{

    protected BluetoothAdapter bluetoothAdapter;
    protected BiodataAdapter biodataAdapter;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);	
	biodataAdapter = new HxmBiodataAdapterImpl();
    }

    @Override
    protected void onResume()
    {
	try
	{
	    biodataAdapter.init();
	    SnapshotDao snapshotDao = new SnapshotDaoImpl();
	    Snapshot s = new Snapshot();
	    s.setBattery(100);
	    s.setCadence(10);
	    s.setHeartrate(79);
	    s.setLatitude(12.21f);
	    s.setLongitude(12.21f);
	    snapshotDao.insert(s);
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
	    biodataAdapter.dispose();
	    Database.getInstance().dispose();
	}
	catch (Exception ex)
	{
	    Logger.getLogger(ttdoidAppActivity.class.getName()).log(Level.SEVERE, null, ex);
	}
	super.onPause();
    }

    private void testBluetooth()
    {
	bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	if (bluetoothAdapter == null)
	{
	    // Device does not support Bluetooth
	}

	if (!bluetoothAdapter.isEnabled())
	{
	    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
	    startActivityForResult(enableBtIntent, 1);
	}

	Set<BluetoothDevice> pairedDevices = bluetoothAdapter.getBondedDevices();
	// If there are paired devices
	if (pairedDevices.size() > 0)
	{
	    // Loop through paired devices
	    for (BluetoothDevice device : pairedDevices)
	    {
		// Add the name and address to an array adapter to show in a ListView
		System.out.println(device.getName() + "\n" + device.getAddress());
		Thread t = new ConnectThread(device);
		t.start();
//                Thread s = new AcceptThread();
//                s.start();
	    }
	}
    }

    private class ConnectThread extends Thread
    {

	private final BluetoothSocket mmSocket;
	private final BluetoothDevice mmDevice;

	public ConnectThread(BluetoothDevice device)
	{
	    // Use a temporary object that is later assigned to mmSocket,
	    // because mmSocket is final
	    BluetoothSocket tmp = null;
	    mmDevice = device;

	    // Get a BluetoothSocket to connect with the given BluetoothDevice
	    try
	    {
		// MY_UUID is the app's UUID string, also used by the server code
		System.out.println("preSocket");
		tmp = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
		System.out.println("postSocket");
	    }
	    catch (IOException e)
	    {
	    }
	    mmSocket = tmp;
	}

	@Override
	public void run()
	{
	    // Cancel discovery because it will slow down the connection
//            bluetoothAdapter.cancelDiscovery();
	    System.out.println("socket:" + mmSocket);

	    try
	    {
		// Connect the device through the socket. This will block
		// until it succeeds or throws an exception
		mmSocket.connect();
		System.out.println("connect");
	    }
	    catch (IOException connectException)
	    {
		// Unable to connect; close the socket and get out
		try
		{
		    System.out.println("unable to connect");
		    mmSocket.close();
		}
		catch (IOException closeException)
		{
		    System.out.println("close exception");
		}
		return;
	    }

	    // Do work to manage the connection (in a separate thread)
	    manageConnectedSocket(mmSocket);
	}

	/** Will cancel an in-progress connection, and close the socket */
	public void cancel()
	{
	    try
	    {
		System.out.println("close");
		mmSocket.close();

	    }
	    catch (IOException e)
	    {
	    }
	}

	private void manageConnectedSocket(BluetoothSocket socket)
	{
	    System.out.println(mmSocket);
	    System.out.println("manage");
	    try
	    {
		InputStream in = socket.getInputStream();
		OutputStream out = socket.getOutputStream();

		HxmReader hr = new HxmReader(in);

		while (true)
		{
		    HxmPaket paket = hr.read();
		    paket.validate();
		    System.out.println(paket);
		}
	    }
	    catch (Exception ex)
	    {
		System.out.println("io exception");
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
}
