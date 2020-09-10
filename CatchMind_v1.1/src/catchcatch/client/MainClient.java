package catchcatch.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import catchcatch.gui.GameRoomFrame;
import catchcatch.gui.LoginFrame;

public class MainClient {
	
	private final static String TAG = "MainClient : ";

	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	MainClient mainClient = this;
	GameRoomFrame gameRoomFrame;
	String msg = null;
	
	public MainClient() {
	
		try {
			gameRoomFrame = new GameRoomFrame(mainClient);
			socket = new Socket("localhost", 8888);
			SocketThread st = new SocketThread();
			st.start();
			
			pw = new PrintWriter(socket.getOutputStream(), true);
			
			while (true) {
				String msg = gameRoomFrame.taChat.getText();
				pw.println(msg);
			}
			
		} catch (Exception e) {
			System.out.println(TAG + e.getMessage());
			e.printStackTrace();
		} 
	}
	
	class SocketThread extends Thread {
		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				while ((msg = br.readLine()) != null) {
					System.out.println(TAG + "사용자" + msg);
					gameRoomFrame.taChat.setText(gameRoomFrame.taChat.getText() + msg + "\n");
				}
			
			} catch (Exception e) {
				System.out.println(TAG + "br : " + e.getMessage());
				e.printStackTrace();
			} 
		}
	}
		
	public static void main(String[] args) {
		new MainClient();
	}

}
