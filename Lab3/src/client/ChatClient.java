package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		if(args.length < 2){
			System.out.println("Input args: machine port");
		} else {
			try {
				Socket socket = new Socket(args[0],Integer.parseInt(args[1]));
				OutputStream os = socket.getOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
				InputStream is = socket.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				Scanner scan = new Scanner(System.in);
				new ReadThread(reader).start();
				PostThread input = new PostThread(writer,scan);
				input.start();
				input.join();
				socket.close();	
				
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			
		}

	}

}
