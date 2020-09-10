package address.test;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DBConnectionTest {
	
	// 메인을 안만들고 단위 테스트 가능(메인은 모든 패키지의 static을 띄우기 때문에 테스트하기 무거움)
	@Test
	public void getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = 
					DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "cos","bitc5600"); //xe 디비 리스너 이름
		} catch (Exception e) {
			System.out.println("DB 연결 실패 : "+e.getMessage());
		}
	}
}
