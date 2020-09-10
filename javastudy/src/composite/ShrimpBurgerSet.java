package composite;

import lombok.Data;

@Data
public class ShrimpBurgerSet {
	private ShrimpBurger shrimpBurger;
	private Coke coke;
	private FrenceFried frenceFried;

	public ShrimpBurgerSet() {
		this(
			 new ShrimpBurger(),
			 new Coke(),
			 new FrenceFried()
			);
	}
	
	public ShrimpBurgerSet(ShrimpBurger shrimpBurger, Coke coke, FrenceFried frenceFried) {
		this.shrimpBurger = shrimpBurger;
		this.coke = coke;
		this.frenceFried = frenceFried;
	}
}
