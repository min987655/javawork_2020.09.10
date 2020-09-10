package catchcatch.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MainServer {

	private final static String TAG = "MainServer : ";

	private ServerSocket serverSocket;
	private Vector<SocketThread> vcList;

	public MainServer() {

		try {
			// 서버 소켓 생성
			serverSocket = new ServerSocket(8888);
			System.out.println("서버준비완료");

			vcList = new Vector<SocketThread>();

			// 메인쓰레드 : 소켓 accept(), vector에 담음.
			while (true) {
				Socket socket = serverSocket.accept();
				SocketThread socketThread = new SocketThread(socket, vcList);
				socketThread.start();
				vcList.add(socketThread);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MainServer();
	}
}
