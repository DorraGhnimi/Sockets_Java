package guess_my_number;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MTServer extends Thread{

	private int nbrClients = 0 ;
	public static int nbrSecret;
	public static boolean end ;
	public static String winner;
	
	@Override
	public void run() {
		//service d'ecoute
		//guess secret number 
		
		try {
		
			nbrSecret = (int) (Math.random()*1000);
			System.out.println("\nnbrSecret : "+nbrSecret);
			ServerSocket serverSocket = new ServerSocket(2003);
			while(true) {
				Socket socket = serverSocket.accept(); 
				// server waiting for a client to make a request to connect
				//whenever a client connect, he ll get a socket (****)
				
				++nbrClients;
				//Service : discussion with new client  
				new Service(socket, nbrClients).start();//here 	(****)			
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static void main(String[] args) {
		new MTServer().start();
	}	
}
