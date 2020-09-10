package ch02;

class Person {
	String name = "강민정";
	int age = 34;
	char gender = '여';
	String email = "min987655@naver.com";
	String phone = "010-0000-0000";
}

public class VarEx06 {
	public static void main(String[] args) {
		Person a = new Person();
		System.out.println(a.name);
		System.out.println(a.age);
		System.out.println(a.gender);
		System.out.println(a.email);
		System.out.println(a.phone);
	}

}
