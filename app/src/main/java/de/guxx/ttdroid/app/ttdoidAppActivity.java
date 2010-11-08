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
import java.io.IOException;
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

    /**
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        testBluetooth();
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
//                Thread t = new ConnectThread(device);
//                t.start();
                Thread s = new AcceptThread();
                s.start();
            }
        }
    }

    private class AcceptThread extends Thread
    {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread()
        {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try
            {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = bluetoothAdapter.listenUsingRfcommWithServiceRecord("test", UUID.randomUUID());
            } catch (IOException e)
            {
            }
            mmServerSocket = tmp;
        }

        @Override
        public void run()
        {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            System.out.println("start");
            while (true)
            {
                try
                {
                    socket = mmServerSocket.accept();
                    System.out.println("socket");
                } catch (IOException e)
                {
                    System.out.println("exception");
                    break;
                }
                // If a connection was accepted
                if (socket != null)
                {
                    // Do work to manage the connection (in a separate thread)
                    manageConnectedSocket(socket);
                    try
                    {
                        mmServerSocket.close();
                    } catch (IOException ex)
                    {
                        System.out.println("problem close");
                    }
                    break;
                }
            }
        }

        /** Will cancel the listening socket, and cause the thread to finish */
        public void cancel()
        {
            try
            {
                System.out.println("close");
                mmServerSocket.close();
            } catch (IOException e)
            {
            }
        }

        private void manageConnectedSocket(BluetoothSocket socket)
        {
            System.out.println("manage");
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
                tmp = device.createRfcommSocketToServiceRecord(UUID.randomUUID());
            } catch (IOException e)
            {
            }
            mmSocket = tmp;
        }

        @Override
        public void run()
        {
            // Cancel discovery because it will slow down the connection
//            bluetoothAdapter.cancelDiscovery();
            System.out.println("discovery");

            try
            {
                // Connect the device through the socket. This will block
                // until it succeeds or throws an exception
                mmSocket.connect();
                System.out.println("connect");
            } catch (IOException connectException)
            {
                // Unable to connect; close the socket and get out
                try
                {
                    System.out.println("unable to connect");
                    mmSocket.close();
                } catch (IOException closeException)
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

            } catch (IOException e)
            {
            }
        }

        private void manageConnectedSocket(BluetoothSocket mmSocket)
        {
            System.out.println(mmSocket);
            System.out.println("manage");
        }
    }
}
