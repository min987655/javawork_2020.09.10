package composite;

import lombok.Data;

// 자바는 다중 상속이 안됨(부모가 두명일 수 없음)
@Data
public class BigBurgerSet {
	private BigBurger bigBurger;
	private Coke coke;
	private FrenceFried frenceFried;
	
	public BigBurgerSet(BigBurger bigBurger) {
		this(
			 bigBurger,
			 new Coke(1500,"코카콜라"),
			 new FrenceFried(2000,"감자칩")
			);
	}
	
	public BigBurgerSet() {
		this(
			 new BigBurger(),
			 new Coke(),
			 new FrenceFried()
			);
	}

	public BigBurgerSet(BigBurger bigBurger, Coke coke, FrenceFried frenceFried) {
		this.bigBurger = bigBurger;
		this.coke = coke;
		this.frenceFried = frenceFried;
	}
}