package coffeeshop;

// 책임 : 커피생성
public class Barista {
	
	public Coffee 커피생성(MenuItem menuItem) {
		return new Coffee(menuItem);
	}
}
