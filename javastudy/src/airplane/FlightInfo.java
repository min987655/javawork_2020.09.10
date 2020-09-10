package airplane;

import java.util.ArrayList;

import lombok.Data;

@Data
class FlightInfo {
	private Response response;
}

@Data
class Response {
	private Header header;
	private Body body;
}

@Data
class Header {
	private String resultCode;
	private String resultMsg;
}

@Data
class Body {
	private Items items;
	private int numOfRows;
	private int pageNo;
	private int totalCount;
}

@Data
class Items {
	private ArrayList<Item> item;
}

@Data
class Item {
	private String airlineNm;
	private String arrAirportNm;
	private Long arrPlandTime;
	private String depAirportNm;
	private Long depPlandTime;
	private String vihicleId;	
	private int economyCharge;
}
