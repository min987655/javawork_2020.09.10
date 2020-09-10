package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteEx01 {

	public static void main(String[] args) {
//		OracleDriver o = new OracleDriver();
//		다른 스택에서 필요하면 또 new 해야한다. 

		try {
			final String SQL = "delete from users where id = ?";
			// OJDBC 문서에 해당 드라이버를 로드하라는 메뉴얼이 있음(인터페이스)
			Class.forName("oracle.jdbc.driver.OracleDriver"); // heap에 오라클 드라이버 new해서 띄움(stream 연결하기 위해서) but 주소 모름
			// 스트림 연결(인타페이스가 적용된 스트림)
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ssar", "bitc5600");
			// 버퍼달기(?를 사용하게 해준다)
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, 1);
			// 버퍼에 쓰기(commit/auto flush 있음)
			pstmt.executeUpdate();
			System.out.println("인서트 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
