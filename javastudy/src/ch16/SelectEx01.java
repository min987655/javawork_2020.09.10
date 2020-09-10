package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SelectEx01 {

	public static void main(String[] args) {
//		OracleDriver o = new OracleDriver();
//		다른 스택에서 필요하면 또 new 해야한다. 

		try {
			final String SQL = "select id, name, email, password from users where id = ?";
			// OJDBC 문서에 해당 드라이버를 로드하라는 메뉴얼이 있음(인터페이스)
			Class.forName("oracle.jdbc.driver.OracleDriver"); // heap에 오라클 드라이버 new해서 띄움(stream 연결하기 위해서) but 주소 모름
			// 스트림 연결(인타페이스가 적용된 스트림)
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ssar", "bitc5600");
			// 버퍼달기(?를 사용하게 해준다), 인젝션 공격을 막아줌.
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, 3);
			// 버퍼에 쓰기(ResultSet(커서)을 리턴받음-첫번째 커서만 리턴받음)
			ResultSet rs = pstmt.executeQuery();
			Users users = null;
			if (rs.next()) { 
				users = new Users(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password")
						);
			}
				System.out.println(users.getId()); // 컬럼명 적음(가독성)
				System.out.println(users.getName());
				System.out.println(users.getEmail());
				System.out.println(users.getPassword());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
