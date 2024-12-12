package net.daum.dao;

import net.daum.vo.MemberVO;

// 2024-12-12 Spring Security 실습

public interface MemberDAO {

	MemberVO idCheck(String id);

	void insertMember(MemberVO m);

	MemberVO pwdMember(MemberVO m);

	void updatePwd(MemberVO m);

}
