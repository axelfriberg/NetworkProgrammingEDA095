package lab5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MCServerOffer extends Thread{
    private int port;
    public MCServerOffer(int port ){
        this.port = port;
    }
    public void run(){
		try {
			MulticastSocket ms = new MulticastSocket(4099);
			InetAddress ia = InetAddress.getByName("experiment.mcast.net");
			ms.joinGroup(ia);
            System.out.println("Server started, waiting for message...");
            while(true) {
                byte[] buf = new byte[65536];
                DatagramPacket dp = new DatagramPacket(buf,buf.length);
                ms.receive(dp);
                String s = new String(dp.getData(),0,dp.getLength());
                System.out.println("Received: "+s);
                //String localAddress = InetAddress.getLocalHost().getHostAddress();
                System.out.println("Sending: " + port);
                InetAddress clientAddress = dp.getAddress();
                int clientPort = dp.getPort();
                byte[] responseBuffer = Integer.toString(port).getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseBuffer,
                        responseBuffer.length,
                        clientAddress,
                        clientPort);
                //DatagramSocket socket = new DatagramSocket(clientPort,clientAddress);
                ms.send(responsePacket);
            }
		} catch(IOException e) {
			System.out.println("Exception:"+e);
		}
    }
}
