package quiz;

//String 타입은 .charAt(index)로 해당위치의 글자를 불러올 수있다.
//예를들어 "가나다".charAt(0) 은  '가' 이다
//아래 주어진 numStr을 charAt과 for문을 이용하여 한글자씩 출력해보자

public class Quiz002 {

	public static void main(String[] args) {

		String numStr = "0123456789";

		for (int i = 0; i < 10; i++) {
			System.out.print(numStr.charAt(i));
		}
		
	}

}
