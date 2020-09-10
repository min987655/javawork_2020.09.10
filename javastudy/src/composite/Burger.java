package composite;

import lombok.Data;

@Data // Getter, Setter 생성
public class Burger {
	private int price;
	private String desc;
	
	// 기본 가격 셋팅
	public Burger() {
		this(1500,"기본 버거");
	}
	// 최종 목적지
	// 특별 세일 할 때만 하단 호출하는 것이 효율적
	public Burger(int price, String desc) {
		this.price = price;
		this.desc = desc;
		System.out.println(desc + "가 만들어졌습니다.");
	}
}
