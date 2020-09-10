package ch06;

class Animal {
	@Override
	public int hashCode() {
		return super.hashCode();
	}
}

public class ObjectEx01 {
	public static void main(String[] args) {
		String d1 = "물";
		String d2 = "물";

		System.out.println(d1.equals(d2)); // 레퍼런스 검색 후 값도 검색
		System.out.println(d1 == d2); // 같은 공간에 있기 때문에 true

		String d3 = new String("물"); 
		String d4 = new String("물");

		System.out.println(d3.equals(d4)); // 레퍼런스 검색 후 값도 검색
		System.out.println(d3 == d4); // 다른 공간에 있기 때문에 false
		
		System.out.println(d3.getClass()); // class의 정보(경로와 이름) 리턴
		System.out.println(new ObjectEx01().getClass());
		
		// 해쉬코드 -> 해쉬 알고리즘 = 동일한 길이의 숫자로 리턴
		System.out.println(d3.hashCode());
		System.out.println(d4.hashCode()); // String만 값이 같으면 같은 해쉬가 나오도록 오버라이딩 돼있음.
		
		Animal a1 = new Animal();
		Animal a2 = new Animal();
		
		System.out.println(a1.hashCode()); // 같은 객체인지 비교 할 때 사용.
		System.out.println(a2.hashCode()); // String 아니기 때문에 다른 공간에 있는 값 각각 해쉬 나옴.
		
		System.out.println(a1 instanceof Animal); // 타입 확인
		System.out.println(a2 instanceof Animal);
		}

}
