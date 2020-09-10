package composite;

import lombok.Data;

@Data
public class FrenceFried {
	private int price;
	private String desc;

	public FrenceFried() {
		this(2000,"감자칩");
	}
	
	public FrenceFried(int price, String desc) {
		this.price = price;
		this.desc = desc;
		System.out.println(desc+"이 만들어졌습니다.");
	}
}
