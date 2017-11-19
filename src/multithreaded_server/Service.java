package multithreaded_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Service extends Thread{
	
	private Socket socket;
	private int numClient;
	
	
	public Service(Socket socket, int numClient) {
		super();
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
			PrintWriter pw = new PrintWriter(os,true);
			
			pw.print("Welcome Client "+numClient+" ! ^^" );
			System.out.println("Client "+numClient+" just connected !");
			System.out.println("    |  IP        			= "+socket.getRemoteSocketAddress());
			System.out.println("    |  Port      			= "+socket.getPort());
			System.out.println("    |  LocalPort 			= "+socket.getLocalPort());
			System.out.println("    |  LocalAddress         = "+socket.getLocalAddress());
			
			while (true) {
				String request = br.readLine();
				pw.println("\n\n"+request+"  :  "+request.length());
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
