package dateex;

public class MathEx {
	public static void main(String[] args) {
		System.out.println(Math.PI);
		System.out.println(Math.ceil(1.5));
		System.out.println(Math.floor(1.5));
		System.out.println(Math.sqrt(9));
		System.out.println(Math.exp(2));
		System.out.println(Math.round(3.14));

		// [1, 45] 사이의 정수형 난수 5개 발생
		System.out.println("이번주 행운의 번호는 ");
		for (int i = 0; i < 5; i++) {
			System.out.println((int) (Math.random() * 45 + 1) + " "); // 난수 발생
		}
	}

}
