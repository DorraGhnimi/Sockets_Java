package io_streams_readers_buffers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		try {
			Socket socket = new Socket("localhost", 2002);
			
			InputStream inputStream = socket.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter printWriter = new PrintWriter(outputStream, true); //true = print each String, otherwise ill need 'flush' ( in once)
			
			Scanner clavier = new Scanner(System.in);
			System.out.println("Client to user : what's on your mind !?  :  "); //String
			String thoughts = clavier.nextLine();

			
			printWriter.println(thoughts);
			
			
			String result = bufferedReader.readLine();

			System.out.println("Result : " + result);
			System.out.println("closing connection...");
			clavier.close();
			socket.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
