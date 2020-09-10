package ch08;

import com.google.gson.Gson;

class Family {
	int number = 2;
	String father = "홍판서";
	String mother = "춘섬";
}

class Person {
	String name = "홍길동";
	int age = 25;
	String gender = "여";
	String add = "서울특별시 양천구 목동";
	String[] hobby = { "농구", "도술" };
	Family family = new Family();
	String company = "경기 수원시 팔달구 우만동";
}

public class JsonEx01 {
	public static void main(String[] args) {
		Gson gson = new Gson();
		// fromJson() 함수 : json(String) -> java
		// toJson() 함수 : java -> json(String)
		String personJson = gson.toJson(new Person());
		System.out.println(personJson);
	}
}
