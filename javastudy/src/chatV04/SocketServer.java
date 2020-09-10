package chatV04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class SocketServer {

	ServerSocket serverSocket;
	Vector<SocketThread> vc;
	
	public SocketServer() {
		try {
			serverSocket = new ServerSocket(20001);
			vc = new Vector<>();
			
			while (true) {
				System.out.println("요청 대기");
				Socket socket = serverSocket.accept();
				System.out.println("요청 받음");
				SocketThread st = new SocketThread(socket);
				st.start();
				vc.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class SocketThread extends Thread {
		Socket socket;
		String id;
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
			
				pw.println("please Sign in");
				id = br.readLine();
				System.out.println("요거 실행됨");
				System.out.println(id);
				
				String line = null;
				while ((line = br.readLine()) != null) {
					router(line);
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void router(String line) {
			String[] gubun = line.split(":");
			String protocol = gubun[0];
			if (protocol.equals(ChatProtocol.ALL)) {
				String msg = gubun[1];
				allChat(msg);
			} else if (protocol.equals(ChatProtocol.MSG)) {
				String otherId = gubun[1];
				String msg = gubun[2];
				privateChat(otherId, msg);
			} 
		}
		
		public void allChat(String msg) {
			System.out.println(id + " : " + msg + " ip : " + socket.getInetAddress());
			for (SocketThread socketThread : vc) {
				socketThread.pw.println(id + " : " + msg);
			}
		}
		
		public void privateChat(String otherId, String msg) {
			System.out.println(id + " : " + msg + " ip : " + socket.getInetAddress());
			for (SocketThread socketThread : vc) {
				if (socketThread.id.equals(otherId)) {
					socketThread.pw.println(id + ":" + msg);
				} else {
					pw.println("아이디를 찾을 수 없습니다.");
				}
			}
		
		}
	}
	
	public static void main(String[] args) {
		new SocketServer();
	}
}	