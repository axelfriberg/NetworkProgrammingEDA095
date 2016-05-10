package lab3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.InetAddress;

public class ServerThread extends Thread{
	private Socket socket;

    public ServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
		try {
			System.out.println("A client connected...");
			InetAddress clientAdress = socket.getInetAddress();
			System.out.println(clientAdress);
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
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
			socket.close();
			System.out.println("A client disconnected...");
		} catch(IOException e) {
			System.out.println(e);
		}
    }

}
