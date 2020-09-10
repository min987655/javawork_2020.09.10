package array03;

import java.util.Scanner;

/*
 * 숫자 1000000000 를 입력받고 (10억)
 * 3자리 마다 콤마를 찍어서 출력 !
 * 1,000,000,000
 */

public class ArrayEx05 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		String tempNum = num + "";
		String tempNum2[] = tempNum.split("");

		/*
		 * for (int i = 0 ; i < tempNum2.length; i++) { System.out.print(tempNum2[i]);
		 * if(i%3==0 && i!=tempNum2.length-1) { System.out.print(","); } }
		 */
		int len = tempNum2.length; // len = 4

		for (int i = 0; i < len; i++) { // 4번 도는 for문
			if ((len - i) % 3 == 0 && i != 0) { // 4%3, 3%3, 2%3, 1%3
				System.out.print(",");
			}
			System.out.print(tempNum2[i]);
		}
		
		/* 자릿수 계산을 위한 변수
		int p = (tempNum2.length % 3);
		for (int i = 0; i < tempNum2.length; i++) {
			// 첫째자리 앞에 ,이 붙으면 안되니 첫 조건으로.
			if (i == 0)
				tempNum2[i] = "" + tempNum2[i];
			// i를 3으로 나눈 것이 p값과 같으면 앞에 ,을 붙임
			else if (i % 3 == p)
				tempNum2[i] = "," + tempNum2[i];
			// 자릿수 대로 출력
			System.out.print(tempNum2[i]);
		}*/

	}

}
