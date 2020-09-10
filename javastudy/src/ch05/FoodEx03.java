package ch05;

abstract class 칼 {
	abstract void kill();

	abstract void cook();

	abstract void repair();
}
// 추상클래스는 어댑터의 용도로 사용 가능하다.
abstract class 요리사어댑터 extends 칼 {
	@Override
	void kill() {}
	@Override
	void repair() {}
}

class 백종원 extends 요리사어댑터 {

	@Override
	void cook() {
		
	}

}

public class FoodEx03 {

	public static void main(String[] args) {

	}

}
