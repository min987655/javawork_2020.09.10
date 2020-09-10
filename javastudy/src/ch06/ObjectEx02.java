package ch06;

class Person {
	String name ="홍길동";
	int age = 15;
	String job ="학생";
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", job=" + job + "]";
	}

}

public class ObjectEx02 {

	public static void main(String[] args) {
		Integer num = 10;
		String s = num.toString(); // 모든 오브젝트는 toString 할 수 있음
		System.out.println(s);

		Person p = new Person();
		System.out.println(p.toString()); // (toString의 원형은 : class @ 해쉬코드) 재정의해서 스트링타입이 리턴되게 함.
		System.out.println(p); // 객체를 호출하면 자동으로 toString 호출 됨.
		
		StringBuilder sb = new StringBuilder();
		sb.append("안녕");
		sb.append("반가워");
		
		System.out.println(sb.toString());
		                                                                                                                                      
	}

}
