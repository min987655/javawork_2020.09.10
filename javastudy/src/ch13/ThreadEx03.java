package ch13;

interface Callback {
	void printMoney(int money);
}

class MoneyChanger {
	int money = 10000;

	public void accept(Callback callback) {
		// 은행에 인출 요청을 해서 20000원을 받을 예정 2초 !(한번만 쓸거라 타겟을 익명클래스로 만듬)
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					money = money + 20000;
					callback.printMoney(money);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}

public class ThreadEx03 {

	public static void main(String[] args) {
		MoneyChanger mc = new MoneyChanger();
		mc.accept(new Callback() {

			@Override
			public void printMoney(int money) {
				System.out.println("통장의 잔액은 : " + money);
			}
		});
		for (int i = 1; i < 6; i++) {
			System.out.println("메인쓰레드 : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
