package address.test.temp;

import org.junit.Test;

public class MyStringParserTest {

	@Test // 전체에 테스트 걸어놓고 다 실행해 봄 - 다 초록색 뜨면 OK 
	public void getId() {
		int memeberId = Integer.parseInt("200.홍길동".split("[.]")[0]); 
		System.out.println(memeberId);
	}
	
}
