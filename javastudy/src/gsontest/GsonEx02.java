package gsontest;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

class Family2 {

	private Integer number;
	private String father;
	private String mother;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

class People2 {

	private String name;
	private Integer age;
	private String gender;
	private String add;
	private List<String> hobby = null;
	private Family2 family2;
	private String company;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public List<String> getHobby() {
		return hobby;
	}

	public void setHobby(List<String> hobby) {
		this.hobby = hobby;
	}

	public Family2 getFamily2() {
		return family2;
	}

	public void setFamily2(Family2 family2) {
		this.family2 = family2;
	}

	public String getCompany() {
		return company;
	}

	public void setComoany(String company) {
		this.company = company;
	}

	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

public class GsonEx02 {

	public static void main(String[] args) {
		String jsonP = "{\"name\":\"홍길동\",\"age\":25,\"gender\":\"여\",\"add\":\"서울특별시 양천구 목동\",\"hobby\":[\"농구\",\"도술\"],\"family\":{\"number\":2,\"father\":\"홍판서\",\"mother\":\"춘섬\"},\"comoany\":\"경기 수원시 팔달구 우만동\"}\r\n"
				+ "";

		Gson gson = new Gson();
		People2 people2 = gson.fromJson(jsonP, People2.class);
		System.out.println(people2.getName());
		System.out.println(people2.getAdd());
	}
}