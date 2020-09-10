package catchcatch.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Vector;

import catchcatch.util.Protocol;

//소켓정보 + 타겟(run)
	class SocketThread extends Thread {

		private final static String TAG = "SocketThread: ";
		
		private Socket socket;
		private BufferedReader br;
		private PrintWriter pw;
		private Vector<SocketThread> vcList; // 돌아가고있는 스레드

		public SocketThread(Socket socket, Vector<SocketThread> vcList) throws Exception {
			this.vcList = vcList;
			this.socket = socket;
		}

		@Override
		public void run() {

			try {				
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				pw = new PrintWriter(socket.getOutputStream(), true);

				String msg = null;
				while ((msg = br.readLine()) != null) {
					System.out.println(TAG + msg);
					router(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void router(String msg) {

			String[] msgline = msg.split(":");
			String protocol = msgline[0];
			if (protocol.equals(Protocol.CHAT)) {
				String chatMsg = msgline[1];
				Chat(chatMsg);
			}
		}

		public void Chat(String chatMsg) {
			System.out.println(TAG + "Chat : " + chatMsg);
			for (SocketThread socketThread : vcList) {
				socketThread.pw.println(chatMsg);
				socketThread.pw.flush();
			}
		}
	}