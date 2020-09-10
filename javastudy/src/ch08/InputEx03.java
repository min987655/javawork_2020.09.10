package ch08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class InputEx03 {
	public static void main(String[] args) {
		// ﻿OutputStream은 Write 하면 됨.
		// 버퍼를 쓰기 위해서는 InputStream, InputStreamReader 필요함.
		InputStream in = System.in;
		InputStreamReader reader = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(reader);

		// 위 3가지 합친 코드
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));

		// catch에 초기화 없어서 오류 -> 선언할 때 초기화 함.
		String data = "";
		try {
			while ((data = br2.readLine()) != null) {
				System.out.println(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
