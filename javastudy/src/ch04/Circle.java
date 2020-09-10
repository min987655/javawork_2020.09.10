package ch04;

import java.util.Scanner;

class Circle01 {
	private int radius;
	private String name;
	
	public void setRadius(int radius) {
		this.radius = radius;
	}

	public void setName(String name) {
		this.name = name;
	}

	//public Circle01() {}
	
	public double getArea() {
		return 3.14*radius*radius;
	}
}
public class Circle {
	public static void main(String[] args) {
		Circle01 c1 = new Circle01(); 
		Scanner scanner = new Scanner(System.in);
		c1.setRadius(scanner.nextInt()); 
		c1.setRadius(scanner.nextInt()); 
		System.out.println(c1.getArea());
	}
}
