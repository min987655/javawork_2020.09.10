package array03;

public class ArrayEx03 {

	public static void main(String[] args) {
		// for문을 이용하여 1에서 10까지의 합을 구하시오.
		int sum = 0;
		for (int i = 1; i <= 10; i++) {
			sum = sum + i;	
		}
		System.out.println("sum : "+sum);
	}

}
