
package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class MySocketServer {

	ServerSocket serverSocket;
	Vector<NewSocketThread> vt; 
	
	public MySocketServer() throws Exception {
		vt = new Vector<>();
		serverSocket = new ServerSocket(3000);
		
		while(true) {
			Socket socket =serverSocket.accept(); // while이 돌 때마다 socket을 덮어 쓰기 때문에 socket을 컬랙션에 보관하여야 함.
			System.out.println("요청이 들어옴");
			NewSocketThread nt = new NewSocketThread(socket);
			Thread newWorker = new Thread(nt);
			newWorker.start();
			vt.add(nt); // 컬랙션에 socket 보관.
		}
	
	}
	// 새로운 쓰레드에게 버퍼를 연결할 수 있게 socket을 전달 !
	// 내부클래스
	class NewSocketThread implements Runnable {
		
		Socket socket;
		BufferedReader br;
		BufferedWriter bw; 
		
		public NewSocketThread(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			try {
				br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
				String msg = "";
				while((msg = br.readLine()) != null) {
					for (NewSocketThread newSocketThread : vt) {
						System.out.println("클라이언트 : "+msg);
						if(newSocketThread != this) { // this는 자기자신이니까 자기 자신과 같지 않을 때만 뿌려줌
							newSocketThread.bw.write(msg+"\n");
							newSocketThread.bw.flush();
						}

					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new MySocketServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
