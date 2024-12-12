package net.daum.service;

import net.daum.vo.MemberVO;

//2024-12-12 Spring Security 실습

public interface MemberService {

	MemberVO idcheck(String id);

	void insertMember(MemberVO m);

	MemberVO pwdMember(MemberVO m);

	void updatePwd(MemberVO m);

}
