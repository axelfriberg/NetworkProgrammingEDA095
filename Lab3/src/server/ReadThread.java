package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import lab3.Clients;
import lab3.Mailbox;

public class ReadThread extends Thread{
private Mailbox mailbox;
private Clients clients;
	
	public ReadThread(Mailbox mailbox, Clients clients){
		this.mailbox = mailbox;
		this.clients = clients;
	}
	
	@Override
	public void run(){
		while(true){
			String message = mailbox.getMessage();	
			System.out.println(message);
			ArrayList<Socket> clientList = clients.getClients();
			OutputStream os;
			for(Socket s : clientList){				
					try {
						os = s.getOutputStream();
						BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
						writer.write(message);
						writer.flush();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
			}
			
			try {
				sleep((long) Math.random());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
