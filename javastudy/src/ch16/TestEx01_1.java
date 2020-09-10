package ch16;

class MyConnection1 {
	
}

class MyJDBC1 extends MyConnection1{
	
	static MyConnection1 conn;
	
	public MyJDBC1() {
		System.out.println("생성자 호출");
	}
	// new 할 때 static 내부가 호출됨.
	static {
		conn = new MyJDBC1();
	}
}

class Hi {
	public void play() {
		MyConnection1 conn = MyJDBC1.conn;
	}
}

public class TestEx01_1 {

	public static void main(String[] args) {
		// MyJDBC1 mj = new MyJDBC1();
		try {
			Class.forName("ch16.MyJDBC1");
			MyConnection1 conn = MyJDBC1.conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}