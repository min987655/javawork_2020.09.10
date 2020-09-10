package airplane;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class FlightApp {

	public static int getTotalCount(String depAirportId, String arrAirportId, long depPlandTime) {

		try {
			URL url = new URL(
					"http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=TYJuOuhQd%2BPQB9hRLr96SDnMNeegd0U3g9mOpBpumr6DwZ6sgto5MjcX%2F8rUKaIKC3AHWmO4gTEHK39cCxwLug%3D%3D&numOfRows=50&pageNo=1&depAirportId="
							+ FlightInfoService.airPortId.get(depAirportId) + "&arrAirportId="
							+ FlightInfoService.airPortId.get(arrAirportId) + "&depPlandTime=" + depPlandTime
							+ "&_type=json");

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			System.out.println(sb.toString());
			System.out.println();

			br.close();
			con.disconnect();

			Gson gson = new Gson();
			FlightInfo flightInfo = gson.fromJson(sb.toString(), FlightInfo.class);

			return flightInfo.getResponse().getBody().getTotalCount();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	public static FlightInfo getFlightInfo(String depAirportId, String arrAirportId, long depPlandTime, int page) {

		try {
			URL url = new URL(
					"http://openapi.tago.go.kr/openapi/service/DmstcFlightNvgInfoService/getFlightOpratInfoList?serviceKey=TYJuOuhQd%2BPQB9hRLr96SDnMNeegd0U3g9mOpBpumr6DwZ6sgto5MjcX%2F8rUKaIKC3AHWmO4gTEHK39cCxwLug%3D%3D&numOfRows=50&pageNo="
							+ page + "&depAirportId=" + FlightInfoService.airPortId.get(depAirportId) + "&arrAirportId="
							+ FlightInfoService.airPortId.get(arrAirportId) + "&depPlandTime=" + depPlandTime
							+ "&_type=json");

			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));

			StringBuilder sb = new StringBuilder();

			String input = "";
			while ((input = br.readLine()) != null) {
				sb.append(input);
			}

			System.out.println(sb.toString());
			System.out.println();

			br.close();
			con.disconnect();

			Gson gson = new Gson();
			FlightInfo flightInfo = gson.fromJson(sb.toString(), FlightInfo.class);

			return flightInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		FlightInfoService.setAirLineId();
		FlightInfoService.setAirPortId();

		for (String key : FlightInfoService.airPortId.keySet()) {
			System.out.print(key + " ");
		}
		System.out.println();
		System.out.println("������� �Է��ϼ���.");
		Scanner sc = new Scanner(System.in);
		String depAirportId = sc.next();

		System.out.println("�������� �Է��ϼ���.");
		String arrAirportId = sc.next();

		System.out.println("������ڸ� �Է��ϼ���.");
		String depPlandTimeTemp = sc.next();

		long depPlandTime = Long.parseLong(depPlandTimeTemp);

		String strTemp = depPlandTime + "";
		
		int totalCount = getTotalCount(depAirportId, arrAirportId, depPlandTime);
		
		int count = 0;
		if(totalCount % 50 == 0) {
			count = totalCount / 50;
		}else {
			count = totalCount / 50 + 1;
		}
		
		ArrayList<FlightInfo> flightInfos = new ArrayList<>();
		int page = 1;
		for (int i = 0; i < count; i++) {
			FlightInfo flightInfo = 
					getFlightInfo(depAirportId, arrAirportId, depPlandTime, page);
			page++;
			flightInfos.add(flightInfo);
		}
		
		for (FlightInfo flightInfo : flightInfos) {
			List<Item> flightItems = flightInfo.getResponse().getBody().getItems().getItem();
			
			for (Item item : flightItems) {
				System.out.println("�װ���: " + item.getAirlineNm());
				System.out.println("�����: " + item.getDepAirportNm());
				System.out.println("������: " + item.getArrAirportNm());
				System.out.println("��߽ð�: " + item.getDepPlandTime());
				System.out.println("�����ð�: " + item.getArrPlandTime());
				System.out.println("���: " + item.getEconomyCharge());
				System.out.println();

			}
		}
		
	}

}
