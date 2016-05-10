package lab3;

import java.io.IOException;
import java.net.ServerSocket;

public class EchoTCP2 {
	public static void main(String[] args) {
		ServerSocket ss;
		try {
			ss = new ServerSocket(30000);
			while(true){
				try {
					new ServerThread(ss.accept()).start();
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
