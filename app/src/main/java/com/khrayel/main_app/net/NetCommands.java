package com.khrayel.main_app.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public interface NetCommands
    {
         default void SendDataAsString(String data_as_string)
            {
                new Thread() {
                    @Override
                    public void run() {
                        Socket socket=null;
                        try {
                            // Create Socket instance
                            socket = new Socket(NetStaticData.ip, NetStaticData.port);

                            OutputStream outputStream= socket.getOutputStream();
                            outputStream.write(data_as_string.getBytes());


                        } catch (UnknownHostException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        finally
                            {
                                try
                                    {
                                        socket.close();
                                    }
                                catch (IOException e)
                                    {
                                        e.printStackTrace();
                                    }
                            }
                    }
                }.start();

    };
    }
