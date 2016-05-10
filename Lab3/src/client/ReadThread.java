package client;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadThread extends Thread{
	private BufferedReader reader;
	
	public ReadThread(BufferedReader reader){
		this.reader = reader;
	}
	
	@Override 
	public void run(){
		try {
			while(true){
				System.out.println(reader.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
