package ch05;

class A {
	void run() {
	System.out.println("A 달린다");
	}
}

class B extends A {
	@Override
	void run() {
	 System.out.println("B 달린다");	
	}
}

class C extends B {
	String name = "C";
	@Override
	void run() {
	 System.out.println(name+" 달린다");	
	}
}

public class OverRideEx01 {

	public static void main(String[] args) {
		A c1 = new C();
		c1.run();
		
		B b1 = new C();
		b1.run();
		
		A a1 = new A();
		a1.run();
		
	}

}
