package chat_multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Conversation extends Thread {

	private Socket socket;
	private int numClient;

	protected Socket getSocket() {
		return socket;
	}

	protected void setSocket(Socket socket) {
		this.socket = socket;
	}

	protected int getNumClient() {
		return numClient;
	}

	protected void setNumClient(int numClient) {
		this.numClient = numClient;
	}

	public Conversation(Socket socket, int numClient) {
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

			//String ip = socket.getRemoteSocketAddress().toString();

			System.out.println("*******Client num  " + numClient + "  just connected*******");
			pw.println("*******Welcome ! Client   num  " + numClient +"*******");
			while (true) {
				String request;
				
				
				Pattern pattern = Pattern.compile(".xx.");
				Matcher matcher = pattern.matcher("MxxY");
				
				
				
				while ((request = br.readLine()) != null) {
					
					String[] msgClients = request.split(":");
					
					String msg = msgClients[0];
					
					String[] stringClients = msgClients[1].split(",");
					int[] NumClients = new int[stringClients.length];
					for (int i = 0; i< stringClients.length;i++) {
						NumClients[i]= Integer.parseInt(stringClients[i]);
					}
					ServerChat.brodCast(msg,NumClients,numClient);

				}
			}

		} catch (

		IOException e) {
			e.printStackTrace();
		}

	}

}
