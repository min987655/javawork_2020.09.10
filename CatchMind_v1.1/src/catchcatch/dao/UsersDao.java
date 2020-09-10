package catchcatch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import catchcatch.db.DBConnection;
import catchcatch.db.DBUtils;
import catchcatch.gui.SigninFrame;
import catchcatch.models.Users;


// 자바와 디비의 거점
public class UsersDao {
	
	private final static String TAG = "UsersDao : ";
	
	public UsersDao() {}
	private static UsersDao instance = new UsersDao();
	public static UsersDao getInstance() {
		return instance;
	}
	
	public int 가입(Users users) {
		
		final String SQL = "INSERT INTO users(id, userName, password) VALUES(users_seq.nextval, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 1. 스트림 연결
			conn = DBConnection.getConnection();
			// 2. 버퍼달기
			pstmt = conn.prepareStatement(SQL);
			// 3. 물음표 완성
			pstmt.setString(1, users.getUserName());
			pstmt.setString(2, users.getPassword());
			// 4. 쿼리 전송(flush + commit)
			int result = pstmt.executeUpdate();
			return result;
		} catch (Exception e) {
			System.out.println(TAG + "추가오류 : " + e.getMessage());
		} finally {
			DBUtils.close(conn, pstmt);
		}
		return -1;
	}
	public List<Users> 확인(List<Users> userNameList) {
		
		final String SQL = "SELECT userName FROM users";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Users> userNameList1 = new Vector<>();
		try {
			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Users user = new Users(rs.getString("userName"));
				userNameList1.add(user);
			}
			return userNameList1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
