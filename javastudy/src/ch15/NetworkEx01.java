package ch15;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetworkEx01 {

	public static void main(String[] args) {

		try {
			// 1. 주소 객체 만들기 - 자동으로 파싱해 줌
			URL url = new URL(
					"http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=XiDeOhe%2FX427It5zoMfoELqSWyIrFm6LicOSj7n6vJBEiEMZq7tWzLt7Oc1f1WYh9KiwAmWZqrVvaEuS15TlFA%3D%3D&numOfRows=50&pageNo=1&depAirportId=NAARKJJ&arrAirportId=NAARKPC&depPlandTime=20200407&_type=json");

			// 2. 스트림 연결
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			// 3. 버퍼 연결(문자열)
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			// 4. 문자 더하기
			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
