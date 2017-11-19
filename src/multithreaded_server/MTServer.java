package multithreaded_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MTServer extends Thread{

	private int nbrClients = 0 ;
	
	@Override
	public void run() {
		//service d'ecoute
		
		//return the length of the String sent by the client
		
		try {
			ServerSocket serverSocket = new ServerSocket(2003);
			while(true) {
				Socket socket = serverSocket.accept();
				++nbrClients;
				new Service(socket, nbrClients).start();				
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	public static void main(String[] args) {
		new MTServer().start();
	}	
}
