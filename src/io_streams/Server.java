package io_streams;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

		
	
	public static void main(String[] args) {

		try {
			ServerSocket serverSocket = new ServerSocket(2001);
			System.out.println("server : waiting for a connection...");
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			System.out.println("server : waiting for a number...");
			int age = inputStream.read();
			int result = age * 2;
			System.out.println("server : sending result back ...");
			outputStream.write(result);
			System.out.println("closing connection...");
			socket.close();

			/*
			 * socket min 3and serverSocket w inputStream w outputStream min 3and e socket
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
		
		
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
