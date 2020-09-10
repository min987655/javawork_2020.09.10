package ch16;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SelectEx02 {

	public static void main(String[] args) {
//		OracleDriver o = new OracleDriver();
//		다른 스택에서 필요하면 또 new 해야한다. 

		try {
			final String SQL = "select id, name, email, password from users";
			// OJDBC 문서에 해당 드라이버를 로드하라는 메뉴얼이 있음(인터페이스)
			Class.forName("oracle.jdbc.driver.OracleDriver"); // heap에 오라클 드라이버 new해서 띄움(stream 연결하기 위해서) but 주소 모름
			// 스트림 연결(인타페이스가 적용된 스트림)
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "ssar", "bitc5600");
			// 버퍼달기(?를 사용하게 해준다), 인젝션 공격을 막아줌.
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			// 버퍼에 쓰기(ResultSet(커서)을 리턴받음-첫번째 커서만 리턴받음)
			ResultSet rs = pstmt.executeQuery();
			List<Users> users = new ArrayList<>(); // 다형성을위해 부모로 리턴
			while (rs.next()) { // rs를 java 오브젝트로 바꾸어 줌.
				Users user = new Users(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password")
						);
				users.add(user);
			}
			for (Users user : users) {
				System.out.println(user.getId()); // 컬럼명 적음(가독성)
				System.out.println(user.getName());
				System.out.println(user.getEmail());
				System.out.println(user.getPassword());
				System.out.println();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
