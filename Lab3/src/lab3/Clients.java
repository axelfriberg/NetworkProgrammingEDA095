package lab3;

import java.net.Socket;
import java.util.ArrayList;

public class Clients {
	private ArrayList<Socket> clients;
	
	public Clients(){
		this.clients = new ArrayList<>();
	}
	
	public void addClient(Socket socket){
		clients.add(socket);
	}
	
	public synchronized ArrayList<Socket> getClients(){
		return clients;
	}
}
