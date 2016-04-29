package lab5;

import java.io.IOException;
import java.net.*;

/**
 * A client that sends a message to a server at <destination> <port>.
 */
public class UDPClient2 {
    public static void main(String[] args) {
        try {
            MulticastSocket ms = new MulticastSocket();
            ms.setTimeToLive(1);
            InetAddress ia = InetAddress.getByName("experiment.mcast.net");
            while(true) {
                int ch;
                String s = new String();
                do {
                    ch = System.in.read();
                    if (ch!='\n') {
                        s = s+(char)ch;
                    }
                } while(ch!='\n');
                //Broadcast Request
                byte[] receivedBuffer = new byte[65536];
                DatagramPacket receivedPacket = new DatagramPacket(receivedBuffer,receivedBuffer.length);
                ms.receive(receivedPacket);
                String serverResponse = new String(receivedPacket.getData(),0,receivedPacket.getLength());
                System.out.println("Server "+serverResponse + " answered first.");

                //Send the command to the server that answered first
                System.out.println("Sending message: "+s);
                byte[] buf = s.getBytes();
                DatagramPacket dp = new DatagramPacket(buf,buf.length,receivedPacket.getAddress(),receivedPacket.getPort());
                DatagramSocket ds = new DatagramSocket();
                ds.send(dp);

                //Output time or date to client
            }
        } catch(IOException e) {
            System.out.println("Exception:"+e);
        }
    }
}
