package chat_multicast;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerChat extends Thread {

	private int nbClients = 0;
	private static List<Conversation> connectedClients = new ArrayList<>();

	@Override
	public void run() {

		try {
			ServerSocket ss = new ServerSocket(2004);

			while (true) {// loop : new Clients
				Socket s = ss.accept();
				nbClients++;
				Conversation conversation = new Conversation(s, nbClients);
				connectedClients.add(conversation);
				conversation.start();

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void brodCast(String msg, int[] NumClients, int numTransmitter) {
		for (Conversation conversation : connectedClients) {

			if ((numTransmitter != conversation.getNumClient())) {
				boolean yes = false;
				for (int num : NumClients) {
					if (num == conversation.getNumClient()) {
						yes = true;
						break;
					}
				}
				if (yes) {
					try {
						PrintWriter pw = new PrintWriter(conversation.getSocket().getOutputStream(), true);
						pw.println("Client " + numTransmitter + " : " + msg);
						System.out.println("Client " + numTransmitter + " : " + msg);
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}

		}
	}

	public static void main(String[] args) {
		new ServerChat().start();

	}

}
