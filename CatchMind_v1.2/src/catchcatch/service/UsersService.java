package catchcatch.service;

import java.util.List;

import catchcatch.dao.UsersDao;
import catchcatch.models.Users;

public class UsersService {
	
	// 트랜잭션 관리 (commit, rollback 관리)
	private UsersService() {}
	private static UsersService instance = new UsersService();
	public static UsersService getinstance() {
		return instance;
	}
	private UsersDao usersDao = UsersDao.getInstance();
	
	public int 회원가입(Users users) {
		// 3. DAO 접근해서 가입함수호출(Users)
		return usersDao.가입(users);
	}
	public List<Users> 아이디확인(List<Users> userNameList1) {
		return usersDao.확인(userNameList1);
	}
}
