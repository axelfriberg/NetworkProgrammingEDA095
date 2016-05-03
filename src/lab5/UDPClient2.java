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
                System.out.println("Sending message: "+s);
                byte[] buf = s.getBytes();
                DatagramPacket dp = new DatagramPacket(buf,buf.length,ia,4099);
                ms.send(dp);

                //receive response
                byte[] receivedBuffer = new byte[65536];
                DatagramPacket receivedPacket = new DatagramPacket(receivedBuffer,receivedBuffer.length);
                ms.receive(receivedPacket);
                String serverResponse = new String(receivedPacket.getData(),0,receivedPacket.getLength());
                System.out.println("Received: "+serverResponse);

                //send command to the chosen server
                DatagramSocket socket = null;
                try {
                    socket = new DatagramSocket();
                } catch(SocketException e) {
                    System.out.println("Could not create socket!");
                    System.exit(1);
                }

                // Create a DatagramPacket to send
                byte[] data = s.getBytes();


                int port = Integer.parseInt(serverResponse);
                InetAddress  dest = receivedPacket.getAddress();


                DatagramPacket dp2 = new DatagramPacket(data,data.length,dest,port);

                // Send the datagram
                try {
                    socket.send(dp2);
                } catch(IOException e) {
                    System.out.println("An IOException occured: "+e);
                    System.exit(1);
                }

                // Create a DatagramPacket hold the incoming message
                byte[] responseData = new byte[65507];
                DatagramPacket responseDP = new DatagramPacket(responseData, responseData.length);

                // Wait for a message to arrive
                try {
                    socket.receive(responseDP);
                } catch (IOException e) {
                    System.out.println("An IOException occured: " + e);
                    socket.close();
                    System.exit(1);
                }
                System.out.println(new String(responseDP.getData(), 0, responseDP.getLength()));


                // Close the DatagramSocket
                socket.close();

            }
        } catch(IOException e) {
            System.out.println("Exception:"+e);
        }
    }
}
