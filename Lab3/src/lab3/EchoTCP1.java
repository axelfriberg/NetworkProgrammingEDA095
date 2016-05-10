package lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoTCP1 {
	public static void main(String[] args) {
		ServerSocket ss;
		Socket clientSocket = null;
		InetAddress clientAdress;
		InputStream is;
		OutputStream os;
		try {
			ss = new ServerSocket(30000);
			while(true){
				try {
					clientSocket = ss.accept();
				} catch (Exception e) {
					System.out.println(e);
					System.exit(1);
				}
				try {
					clientAdress = clientSocket.getInetAddress();
					System.out.println(clientAdress);
					is = clientSocket.getInputStream();
					os = clientSocket.getOutputStream();
					int ch = is.read();
					while (ch!=-1) {
					    os.write((char) ch);
					    ch = is.read();
					}
				} catch (Exception e) {
					System.out.println(e);
					System.exit(1);
				}
				try {
					clientSocket.close();
				} catch(IOException e) {
					System.out.println(e);
				}
				
			}
		} catch (IOException e) {
			System.out.println(e);
			System.exit(1);
		}
	}
}
