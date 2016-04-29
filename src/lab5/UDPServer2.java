package lab5;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.DateFormat;
import java.util.Date;

/**
 * Sends the date or time to the client depending on the command.
 * Created by axel on 2016-04-29.
 */
public class UDPServer2 {
    public static void main(String[] args) {
        if (args.length!=1) {
            System.out.println("Syntax: java UDPServer <port>");
            System.exit(1);
        }

        // Create a DatagramSocket on the given port
        int port = Integer.parseInt(args[0]);
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(port);
        } catch(SocketException e) {
            System.out.println("Could not create socket!");
            System.exit(1);
        }

        System.out.println("Server started on port " + port);

        while (true) {

            // Create a DatagramPacket hold the incoming message
            byte[] data = new byte[65507]; // We are conservative...
            DatagramPacket dp = new DatagramPacket(data, data.length);

            // Wait for a message to arrive
            System.out.println("Waiting for message...");
            try {
                socket.receive(dp);
            } catch (IOException e) {
                System.out.println("An IOException occured: " + e);
                socket.close();
                System.exit(1);
            }

            // Extract the message and process the command.
            System.out.println("New command from " + dp.getAddress());
            String command = new String(dp.getData(), 0, dp.getLength());
            System.out.println(command);
            InetAddress clientAddress = dp.getAddress();
            int clientPort = dp.getPort();

            Date date = new Date();
            System.out.println("Sending response...");
            switch (command) {
                case "date":
                    DateFormat df1 = DateFormat.getDateInstance();
                    byte[] dateResponse = df1.format(date).getBytes();
                    DatagramPacket clientDatePacket = new DatagramPacket(dateResponse,dateResponse.length,clientAddress,clientPort);
                    try {
                        socket.send(clientDatePacket);
                    } catch (IOException e) {
                        System.out.println("Could not send response. " + e);
                        socket.close();
                        System.exit(1);
                    }
                    break;
                case "time":
                    DateFormat df2 = DateFormat.getTimeInstance();
                    byte[] timeResponse = df2.format(date).getBytes();
                    DatagramPacket clientTimePacket = new DatagramPacket(timeResponse,timeResponse.length,clientAddress,clientPort);
                    try {
                        socket.send(clientTimePacket);
                    } catch (IOException e) {
                        System.out.println("Could not send response. " + e);
                        socket.close();
                        System.exit(1);
                    }
                    break;
                default:
                    System.out.println("That is not a valid command.");
                    break;
            }
        }
    }
}
