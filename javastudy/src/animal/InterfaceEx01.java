package animal;

// 1. 변수 : public static final 생략
// 2. 함수 : public abstrack 생략
// 3. 통로의 역할 - 동적바인딩
// 4. 무조건 추상메서드만 존재가능 = 강제성 부여(오류 뜸)
// 5. new 할 수 없다.(자식을 new해서 같이 띄울 수 밖에 없음)
// 6. 도메인을 준다.(공통 코드)
interface Cal {
	public static final int num = 10;
}

public class InterfaceEx01 {

	public static void main(String[] args) {
		System.out.println(Cal.num);
	}

}
