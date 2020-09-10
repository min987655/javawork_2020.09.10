package ch03;

// 메서드(함수)는 메서드(함수)안에 작성할 수 없다.
// 메서드(함수)는 클래스안에 작성할 수 있다.
public class MethodEx01 {
	
	
	public static void main(String[] args) {
		int num = Cal.add(3, 5);
		System.out.println("더하기:"+num);
		
		int num1 = Cal.minus(3, 5);
		System.out.println("빼기:"+num1);
		
		int num2 = Cal.multi(3, 5);
		System.out.println("곱하기:"+num2);
		
		int num3 = Cal.divibe(5, 3);
		System.out.println("나누기:"+num3);
	}
	
}