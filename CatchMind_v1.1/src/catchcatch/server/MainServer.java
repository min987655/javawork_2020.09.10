package catchcatch.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import catchcatch.server.MainServer.SocketThread;
import catchcatch.util.Protocol;

public class MainServer {

	private final static String TAG = "MainServer : ";

	ServerSocket ss;
	Vector<SocketThread> vc;

	public MainServer() {

		try {
			// 서버 소켓 생성
			ss = new ServerSocket(8888);
			System.out.println("서버준비완료");
			vc = new Vector<>();

			// 메인쓰레드 : 소켓 accept(), vector에 담음.
			while (true) {
				Socket socket = ss.accept();
				SocketThread st = new SocketThread(socket);
				st.start();
				vc.add(st);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 소켓정보 + 타겟(run)
	class SocketThread extends Thread {

		Socket socket;
		BufferedReader br;
		PrintWriter pw;

		public SocketThread(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {

			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
				pw = new PrintWriter(socket.getOutputStream(), true);

				String msg = "";
				while((msg = br.readLine()) != null) {
					System.out.println(TAG + "클라이언트 : " + msg);
					for (SocketThread socketThread : vc) {
							socketThread.pw.println(msg + "\n");
							socketThread.pw.flush();
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

//		public void router(String msg) {
//
//			String[] parsing = msg.split(":");
//			String protocol = parsing[0];
//			if (protocol.equals(Protocol.CHAT)) {
//				String chatMsg = parsing[1];
//				Chat(chatMsg);
//			}
//		}
//
//		public void Chat(String chatMsg) {
//			try {
//				for (SocketThread socketThread : vc) {
//					if (socketThread != this) {
//						socketThread.bw.write(chatMsg);
//						socketThread.bw.flush();
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}

	public static void main(String[] args) {
		new MainServer();
	}
}
