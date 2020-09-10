package ch15;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MySocketClient {

	Socket socket;
	BufferedWriter bw;
	BufferedReader br;

	public MySocketClient() throws Exception {
		// 루프백 : "localhost" "127.0.0.1" - 내 아이피로 다시 돌아옴.
		socket = new Socket("localhost", 15000); // 서버소켓의 accept() 함수를 호출
		bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		br = new BufferedReader(new InputStreamReader(System.in));
		
		String msg = "";
		while((msg = br.readLine()) != null) {
			bw.write(msg+"\n");
			bw.flush();
		}
		
//		PrintWriter pw = new PrintWriter(socket.getOutputStream(),true); // BufferedWriter+OutputStreamWriter. auto flush 선택 가능
//		pw.write("안녕");
//		pw.close();
		bw.close();
		br.close();
		socket.close();
	}

	public static void main(String[] args) {
		try {
			new MySocketClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
