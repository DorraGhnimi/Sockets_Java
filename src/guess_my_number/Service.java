package guess_my_number;

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
			
			pw.println("Welcome Client num"+numClient+" ! ^^" );
			
			System.out.println("Client num"+numClient+" just connected !  (IP = "+socket.getRemoteSocketAddress()+" )");
			//System.out.println("    |  RemoteSocketAddress  = "+socket.getRemoteSocketAddress());
			//System.out.println("    |  Port      			= "+socket.getPort());
			//System.out.println("    |  LocalPort 			= "+socket.getLocalPort());
			//System.out.println("    |  LocalAddress         = "+socket.getLocalAddress());
			
			while (true) {
				String request = br.readLine();
				System.out.println("num"+numClient +"  :   "+request  );
				int nb = Integer.parseInt(request);
				if(MTServer.end == false) {
					if(nb < MTServer.nbrSecret) {
						pw.println("higher than "+nb);
					}else if(nb > MTServer.nbrSecret) {
						pw.println("lower than "+nb);
					}else  {
						pw.println("***********");
						pw.println("well done !");
						pw.println("***********");
						MTServer.winner = socket.getRemoteSocketAddress().toString();
						MTServer.end = true;
						System.out.println(MTServer.winner + " win ");
					}
				}else  {
					pw.println("too late!!  !" + MTServer.winner + " win !");
				
				}				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
