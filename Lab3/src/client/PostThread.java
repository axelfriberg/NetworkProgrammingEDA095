package client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class PostThread extends Thread{
	private BufferedWriter writer;
	private Scanner scan;
	
	public PostThread(BufferedWriter writer, Scanner scan){
		this.writer = writer;
		this.scan = scan;
	}
	
	@Override
	public void run(){
		while(scan.hasNext()){
			String line = scan.nextLine();
			if(line.startsWith("Q:")){
				scan.close();
				break;
			}
			try {
				writer.write(line+"\n");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
			System.out.println("Sending msg to the server: " + line);
			
		}
	}
}
