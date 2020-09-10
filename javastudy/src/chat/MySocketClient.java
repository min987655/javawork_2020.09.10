package chat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MySocketClient {
	// 클라이언트는 소켓 하나만 있으면 됨 -> 전역에 빼 놓음. 
	Socket socket;

	public MySocketClient() throws Exception {
		socket = new Socket("192.168.0.8", 3000);
				
		ReadThread rt = new ReadThread();
		Thread newWorker = new Thread();
		newWorker.start();
		//new Thread(new ReadThread()).start();

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		BufferedReader keyboardIn = new BufferedReader(new InputStreamReader(System.in));

		String outputMsg = "";
		
		// 메인 쓰레드는 여기서 무한 루프
		while ((outputMsg = keyboardIn.readLine()) != null) {
			bw.write(outputMsg + "\n");
			bw.flush();
		}
	}
	
	class ReadThread implements Runnable {
		@Override
		public void run() {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String inputMsg = "";
				while((inputMsg = br.readLine()) != null) {
					System.out.println("상대방 : "+inputMsg);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		try {
			new MySocketClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
