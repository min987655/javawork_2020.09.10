package ch01;

import java.util.Scanner;

public class ConEx03 {

	public static void main(String[] args) {
		System.out.println("성적을 입력하세요:");
		Scanner sc = new Scanner(System.in);
		int point = sc.nextInt(); // 정수를 입력받기

		if (point <= 100 && point >= 90) {
			System.out.println("A학점입니다.");
		} else if (point<90 && point >= 80) {
			System.out.println("B학점입니다.");
		} else if (point<80 && point >= 70) {
			System.out.println("C학점입니다.");
		} else if (point<70 && point >= 60) {
			System.out.println("D학점입니다.");
		} else if (point>=0 && point<60) {
			System.out.println("F학점입니다.");
		} else {
			System.out.println("잘못된 입력이 있습니다.");
		}
	}

}
