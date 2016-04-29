package lab5;

import java.io.IOException;
import java.net.*;

/**
 * A client that sends a message to a server at <destination> <port>.
 */
public class UDPClient {
    public static void main(String[] args) {
        if (args.length!=3) {
            System.out.println("Syntax: java UDPClient <destination> <port> <message>");
            System.exit(1);
        }

        // Create a DatagramSocket on any free port
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch(SocketException e) {
            System.out.println("Could not create socket!");
            System.exit(1);
        }

        // Create a DatagramPacket to send
        byte[] data = args[2].getBytes();

        InetAddress dest = null;
        int port = Integer.parseInt(args[1]);
        try {
            dest = InetAddress.getByName(args[0]);
        } catch (UnknownHostException e) {
            System.out.println("Unknown host: "+args[0]);
            System.exit(1);
        }

        DatagramPacket dp = new DatagramPacket(data,data.length,dest,port);

        // Send the datagram
        try {
            socket.send(dp);
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
}
