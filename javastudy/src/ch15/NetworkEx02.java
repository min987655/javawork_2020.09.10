package ch15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class NetworkEx02 {

	public static void main(String[] args) {

		try {
			// 1. 주소 객체 만들기 - 자동으로 파싱해 줌
			URL url = new URL("https://www.naver.com");

			// 2. 스트림 연결
			HttpURLConnection con = 
					(HttpURLConnection) url.openConnection();
			
			// 3. 버퍼 연결(문자열)
			BufferedReader br = 
					new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
			
			//tip : 파일에 스트림 연결
			FileWriter fr = new FileWriter("c:\\utils\\test.html");
			
			// 4. 문자 더하기
			StringBuilder sb = new StringBuilder();
			
			String input = "";
			while((input = br.readLine()) != null) {
				sb.append(input);
			}
			fr.write(sb.toString());
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
