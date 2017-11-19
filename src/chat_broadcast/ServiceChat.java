package chat_broadcast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ServiceChat extends Thread {

	private Socket socket;
	private int numClient;
	private String ip;

	public ServiceChat(Socket socket, int numClient) {
		this.socket = socket;
		this.numClient = numClient;
	}

	@Override
	public void run() {

		try {
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os, true);

			String ip = socket.getRemoteSocketAddress().toString();

			System.out.println("*******Client num  " + numClient + "  just connected*******");
			pw.println("*******Welcome ! Client   num  " + numClient + "  || ip : " + ip + "*******");

			while (true) {// loop : new Requests from a Client (conversation)
				String request;
				while ((request = br.readLine()) != null) {
					String msg = request;
					System.out.println(ip + " : " + msg);
					ServerChat.brodCast(msg, ip);
				}
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			

		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

}
