package chat;

interface StarUnit {
	abstract int gethp();
}

abstract class Protoss implements StarUnit {
	abstract int getSh(); // 쉴드는 프로토스만 가지고 있음
}

abstract class Zerg implements StarUnit {
}

class Zealot extends Protoss {
	int sh = 100;
	int hp = 100;

	@Override
	public int gethp() {
		return hp;
	}
	
	@Override
	public int getSh() {
		return sh;
	}
}

class Ultra extends Zerg {
	int hp = 100;
	
	@Override
	public int gethp() {
		return hp;
	}
}

public class FinallyEx01 {
	
	// 상태 체크(hp, sh)
	static void check(StarUnit unit) {
		try {
			Zealot z = (Zealot)unit;
			System.out.println("남은 쉴드는 : " + z.getSh());	
			System.out.println("남은 체력은 : " + z.gethp());	
			
		} catch (Exception e) {
			// 저그
			Ultra u = (Ultra)unit;
			System.out.println("저그는 쉴드가 없습니다.");
			System.out.println("남은 체력은 : "+u.gethp());			
		} finally {
			System.out.println("나는 무조건 실행 돼");
		}
		
	}

	public static void main(String[] args) {
		check(new Zealot());
		check(new Ultra());
	}

}