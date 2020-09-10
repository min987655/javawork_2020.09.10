package address.service;

import java.util.List;

import address.dao.MemberDao;
import address.model.GroupType;
import address.model.Member;

public class MemberService {

	// 트랜잭션 관리 (commit, rollback 관리)
	// 한번만 부르면 됨 싱글톤 : 생성자를 private로 만듬	
	private MemberService() {}
	private static MemberService instance = new MemberService();
	public static MemberService getinstance() {
		return instance;
	}
	private MemberDao memberDao = MemberDao.getInstance();
	
	public int 주소록추가(Member member) {
		// 3. DAO 접근해서 추가함수호출(Member)
		return memberDao.추가(member);
	}
	public List<Member> 전체목록() {
		return memberDao.전체목록();
	}
	public Member 상세보기(int memberId) {
		return memberDao.상세보기(memberId);
	}
	public int 주소록삭제(int memberId) {
		return memberDao.삭제(memberId);
	}
	public int 주소록수정(Member member) {
		return memberDao.수정(member);
	}
	public List<Member> 그룹목록(GroupType groupType) {
		return memberDao.그룹목록(groupType);
	}
}

