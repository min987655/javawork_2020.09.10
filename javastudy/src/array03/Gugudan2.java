package array03;

import java.util.Scanner;

public class Gugudan2 {

	// 사용자로 부터 입력 받은 구구단 프로그램을 완성 !
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("구구단을 시작합니다.");
		boolean confirm = true;
		while (confirm) {

			System.out.println("숫자를 입력하세요.(2~9)");
			int num = sc.nextInt();

			for (int i = 1; i < 10; i++) {
				System.out.println(num + "*" + i + "=" + (num * i));
			}
			System.out.println("계속 진행할까요? y or n");
			String check = sc.next();
			if (check.equals("n")) {
				// break;// 가장 가까운 반복문을 빠져나갈 때 사용(while)
				confirm = false;
			}
		}
		System.out.println("구구단 프로그램 종료");
	}

}
