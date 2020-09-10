package array03;

import java.util.Scanner;

public class ArrayEx08 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("영어 대문자 한자리를 입력하시오.");
		
		String str = sc.next();
		char ch = str.charAt(0);
		
		for (int i = ch +1; i < 'Z'; i++) {
			System.out.print((char)i+"");
		}

		}
		
	}
