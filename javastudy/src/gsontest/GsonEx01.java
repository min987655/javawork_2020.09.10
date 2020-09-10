package gsontest;

import com.google.gson.Gson;

class Person {
	String name;
	int age;
	String gender;
	String add;
	String[] hobby;
	Family family;
	String company;
}

class Family {
	int number;
	String father;
	String mother;
}

public class GsonEx01 {

	public static void main(String[] args) {
		String jsonPerson = "{\"name\":\"홍길동\",\"age\":25,\"gender\":\"여\",\"add\":\"서울특별시 양천구 목동\",\"hobby\":[\"농구\",\"도술\"],\"family\":{\"number\":2,\"father\":\"홍판서\",\"mother\":\"춘섬\"},\"company\":\"경기 수원시 팔달구 우만동\"}\r\n"
				+ "";

		Gson gson = new Gson();
		Person p = gson.fromJson(jsonPerson, Person.class);

		System.out.println(p.add);
		System.out.println(p.family.father);
	}
}
