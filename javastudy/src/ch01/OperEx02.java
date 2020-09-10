package ch01;

public class OperEx02 {

	public static void main(String[] args) {
		int sum = 0;// 0
		int distance = 100;
		int move = 20;
		
		sum = sum + move;
		System.out.println(sum);
		
		sum = sum + move;
		System.out.println(sum);
		
		sum = sum + move;
		System.out.println(sum);
		
		sum = sum + move;
		System.out.println(sum);

		sum = sum + move;
		System.out.println("움직인 거리 : "+sum);//결합 : 문자와 숫자를 더하면 문자로(묵시적 형변환) 
		System.out.println("도착지 : " + distance);
	}

}
