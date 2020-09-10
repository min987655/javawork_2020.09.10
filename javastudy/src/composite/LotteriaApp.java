package composite;

public class LotteriaApp {
	public static void main(String[] args) {
		// BigBurgerSet 추가요 !
		BigBurgerSet set1 = new BigBurgerSet();
	
		// Burger 추가요 !
		Burger burger1 = new Burger();
		
		// Coke 추가요 !
		Coke coke1 = new Coke();
		
		// BigBurgerSet 추가요 ! 버거 1000원 할인 쿠폰
		BigBurgerSet set2 = new BigBurgerSet(
							new BigBurger(3000, "빅버거 할인"),
							new Coke(),
							new FrenceFried()
							);
		
		System.out.println(set1.getBigBurger().getPrice());
		System.out.println(set2.getBigBurger().getPrice());

		// BigBurgerSet 추가요 ! 버거 2000원 할인 쿠폰 (1)
		BigBurgerSet set3 = new BigBurgerSet(
							new BigBurger(2000, "빅버거 할인")
							);
		
		System.out.println(set3.getBigBurger().getPrice());
		
	
		// 새우버거세트 추가요 ! (2)
		ShrimpBurgerSet set4 = new ShrimpBurgerSet();
		System.out.println();	
	}
}
