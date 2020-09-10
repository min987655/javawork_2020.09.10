package coffeeshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 클래스 자료형 (Beans) : 책임없이 데이터만 있는 오브젝트 = DB Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
	private String name;
	private int price;
}
