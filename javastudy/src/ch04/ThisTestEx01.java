package ch04;

class AirPlane {
	String name;
	String color;
		
	
	public AirPlane(String name, String color) {
		this.name = name;
		this.color = color;
	}	
}

public class ThisTestEx01 {

	public static void main(String[] args) {
		AirPlane a1 = new AirPlane("제트기", "하얀색");

		AirPlane a2 = new AirPlane("항공기", "노란색");
		
	}

}
