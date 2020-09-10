package chatV04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import chatV04.SocketServer.SocketThread;

public class SocketClient {

	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	Scanner sc;
	
	public SocketClient() {
		try {
			socket = new Socket("localhost", 20001);
			SocketThread st = new SocketThread();
			st.start();
			
			pw = new PrintWriter(socket.getOutputStream(), true);
			sc = new Scanner(System.in);
			while (true) {
				String line = sc.nextLine();
				pw.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class SocketThread extends Thread {
		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				String line = null;
				while ((line = br.readLine()) != null) {
				System.out.println("from server : " + line);
				}
			} catch (Exception e) {
			
			}
		}
	}
	
	public static void main(String[] args) {
		new SocketClient();
	}

}
