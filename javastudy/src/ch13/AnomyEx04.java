package ch13;

abstract class Ani {
	abstract void sound(); 
}

class Cat extends Ani {
	@Override
	void sound() {
		System.out.println("냐옹");
	}
}

class Bird extends Ani {
	@Override
	void sound() {
		System.out.println("짹짹");
	}
}

class Fish extends Ani {
	@Override
	void sound() {
		System.out.println("뻐끔뻐끔");
	}
}


public class AnomyEx04 {
	
	static void start(Ani a) {
		a.sound();
	}
	//한번만 구현되고 말 때 사용. new Ani
	public static void main(String[] args) {
		start(new Cat());
		start(new Fish());
		start(new Ani() {
			
			@Override
			void sound() {
				System.out.println("음메");
			}
		});
	}

}
