package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lab3.Clients;
import lab3.Mailbox;

public class Server {
	public static void main(String[] args) {
		Mailbox mailbox = new Mailbox();
		
		ServerSocket ss;
		try {
			int port = 30000;
			ss = new ServerSocket(port);
			System.out.println("The server started with " + port);
			Clients clients = new Clients();
			new ReadThread(mailbox, clients).start();
			while(true){
				try {
					Socket clientSocket = ss.accept();
					clients.addClient(clientSocket);
					new PostThread(clientSocket, mailbox).start();
				} catch (Exception e) {
					System.out.println(e);
					System.exit(1);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}

}
