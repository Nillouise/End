package nillouise.end;

import java.io.*;
import java.net.*;
/**
 * Created by win7x64 on 2016/12/11.
 */

public class udpSend extends Thread {
    private static final int PORT = 5788;
    private DatagramSocket dataSocket;
    private DatagramPacket dataPacket;
    private byte sendDataByte[];
    private String sendStr;


    public void send(String address)
    {

        try {
   //          指定端口号，避免与其他应用程序发生冲突
            dataSocket = new DatagramSocket(PORT);
            sendDataByte = new byte[1024];
            sendStr = "UDP send";
            sendDataByte = sendStr.getBytes();
            dataPacket = new DatagramPacket(sendDataByte, sendDataByte.length,
                    InetAddress.getByName("127.0.0.1"), PORT);
            dataSocket.send(dataPacket);

        } catch (SocketException se) {
            se.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }
    @Override
    public void run(){
        send("FDSF");
    }

}
