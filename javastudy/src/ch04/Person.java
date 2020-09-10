package ch04;


// 상태와 행동
public class Person {
	String name;
	String job;
	int age;
	char gender;
	String bloodType;
	
	
	//오버로딩 : 같은 이름의 함수를 오버하여 적재 시킴. - 매개변수의 갯수나 타입이 다르면 다르게 인식.
	//하단 같은 이름의 함수가 있는데 오류가 안남.
	//System.out.println() 
	//디폴생성자(매개변수가 없는 아이) = 초기화 : 클래스 이름과 같음.
	public Person() {

	}
	//매개변수가 있는 생성자
	public Person(String name, String job, int age, char gender, String bloodType) {
		this.name = name;
		this.job = job;
		this.age = age;
		this.gender = gender;
		this.bloodType = bloodType;
	}
	
	
}
