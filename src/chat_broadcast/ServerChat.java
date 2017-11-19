package chat_broadcast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerChat extends Thread {

	private int nbClients = 0;
	private static List<Socket> connectedClients = new ArrayList<>();

	@Override
	public void run() {

		try {
			ServerSocket ss = new ServerSocket(2004);

			while (true) {// loop : new Clients
				Socket s = ss.accept();
				connectedClients.add(s);
				nbClients++;
				new ServiceChat(s, nbClients).start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void brodCast(String msg, String transmitter) {
		for (Socket socket : connectedClients) {
			String ip = socket.getRemoteSocketAddress().toString();
			if (!(transmitter.equals(ip))) {
				try {
					PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
					pw.println(ip + " : " + msg);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new ServerChat().start();

	}

}
