package catchcatch.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import catchcatch.gui.GameRoomFrame;
import catchcatch.gui.LoginFrame;
import catchcatch.gui.SigninFrame;

public class MainClient {

	private final static String TAG = "MainClient : ";

	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;
	
	LoginFrame loginFrame;
	SigninFrame signinFrame;
	GameRoomFrame gameRoomFrame;

	public MainClient() {

		try {
			socket = new Socket("localhost", 8888);
			SocketThread st = new SocketThread();
			st.start();

			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			pw = new PrintWriter(socket.getOutputStream(), true);

			while (true) {
				String msgLine = br.readLine();
				pw.println(msgLine);
				pw.flush();
			}
		} catch (Exception e) {
			System.out.println(TAG + e.getMessage());
			e.printStackTrace();
		}
	}

	class SocketThread extends Thread {

		@Override
		public void run() {
						
			while (true) {
				try {
					br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

					String msg = null;
					while ((msg = br.readLine()) != null) {
						gameRoomFrame.taChat.setText(gameRoomFrame.taChat.getText() + msg + "\n");
					}


				} catch (Exception e) {
					System.out.println(TAG + "br : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		new LoginFrame();
	}

}
