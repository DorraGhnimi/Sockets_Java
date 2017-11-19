package io_streams_readers_buffers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

		
	
	public static void main(String[] args) {

		try {
			
			/*
			 * first tried  port 1001 => "java.net.BindException: Permission denied (Bind failed)"
			 * ===> "You can't open a port below 1024, if you don't have root privileges"
			 */

		
			
			ServerSocket serverSocket = new ServerSocket(2002);
			
			System.out.println("server : waiting for a connection...");
			Socket socket = serverSocket.accept();
			
			
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inprutStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inprutStreamReader);
			
			
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream, true);
			
			
			System.out.println("server : waiting for a String...");
			String thoughts = bufferedReader.readLine();
			
			String result = thoughts + "   !!! haka ";
			
			
			System.out.println("server : sending result back ...");
			printWriter.println(result);
			
					
			System.out.println("closing connection...");
			serverSocket.close();
			socket.close();

			/*
			 * socket min 3and serverSocket w inputStream w outputStream min 3and e socket
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
		
		
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
