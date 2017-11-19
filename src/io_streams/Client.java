package io_streams;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		try {
			Socket socket = new Socket("localhost", 2001);
			//both use 'Bytes'==> result as it's, is not readable (String
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();

			Scanner clavier = new Scanner(System.in);
			System.out.println("Client to user : how old are you!?  :  "); //int
			int age = clavier.nextInt();

			outputStream.write(age);
			int result = inputStream.read();

			System.out.println("Resultt : " + result);
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
