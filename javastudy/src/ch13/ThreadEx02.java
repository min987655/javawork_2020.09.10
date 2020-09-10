package ch13;

class DownloadThread implements Runnable {
	// 비동기 프로그램 
	// 일이 순서대로 진행되지 않음
	// 메인이 모두 실행하지 않고 os에게 쓰레드를 하나 더 만들어 실행하라고 함.
	// 금액 30000원이 안나옴(타이밍의 문제)
	int data = 10000;
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		data = data + 20000;
		System.out.println("금액 다운로드 종료");
	}
}

public class ThreadEx02 {
	public static void main(String[] args) {
		System.out.println("프로그램 시작");
		System.out.println("-------------");
		
		DownloadThread dt = new DownloadThread();
		Thread t1 = new Thread(dt);
		t1.start();
		
		System.out.println("금액은 : "+dt.data);
	}
}
