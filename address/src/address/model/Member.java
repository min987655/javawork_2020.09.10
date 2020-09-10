package address.model;

import lombok.Builder;
import lombok.Data;

@Data
public class Member {
	private int id; //PK
	private String name; //이름
	private String phone; // 전화번호 (연산할 일이 없기 때문에 String)
	private String address; // 주소
	// 그룹 : 친구, 회사, 학교, 가족 (도메인 설정해줘야 함)
//	private String group; // 클라이언트는 도메인 모름, 도메인 없으면 다 들어와버림
	private GroupType groupType; // 타입 지정-도메인 설정함(enum)
	
	@Builder // 생성자를 new 하는 방식이 달라 짐
	public Member(int id, String name, String phone, String address, GroupType groupType) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.groupType = groupType;
	}
	
	@Override
	public String toString() {
		return id+". "+name;
	}

}
