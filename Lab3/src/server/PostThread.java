package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import lab3.Mailbox;

public class PostThread extends Thread {
	private Socket socket;
	private Mailbox mailbox;

    public PostThread(Socket socket, Mailbox mailbox){
        this.socket = socket;
        this.mailbox = mailbox;
    }

    @Override
    public void run() {
		try {
			System.out.println("A client connected...");
			InetAddress clientAdress = socket.getInetAddress();
			System.out.println(clientAdress);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
	
			boolean connected = true;
			while (connected) {
				String message = reader.readLine();
				String command;
				if(message.length() >= 2)
					 command = message.substring(0, 2);
				else 
					command = "";	
				
				System.out.println(command);
				switch(command){
					case "M:": 
						mailbox.postMessage(message+"\n");
						break;
					case "E:":
						System.out.println(message);
						writer.write(message);
						writer.flush();
						break;
					case "Q:":
						connected = false;
						break;
					default:
						System.out.println("That command does not exist.");						
				}
				
			}
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		try {
			socket.close();
			System.out.println("A client disconnected...");
		} catch(IOException e) {
			System.out.println(e);
		}
    }

}
