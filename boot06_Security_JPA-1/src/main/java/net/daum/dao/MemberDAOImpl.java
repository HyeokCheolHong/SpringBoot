package net.daum.dao;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.MemberVO;

// 2024-12-12 Spring Security 실습

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired // JPA 실행을 위한 자동의존성 주입(DI)
	private MemberRepository memberRepo;

	// 아이디 중복검색
	@Override
	public MemberVO idCheck(String id) {
		
		System.out.println("\n==================>(아이디 중복 검색 JPA)");
		Optional<MemberVO> result = this.memberRepo.findById(id);
		// 아이디에 해당하는 회원정보 검색
		MemberVO member;
		
		if(result.isPresent()) {
			// 아이디에 해당하는 회원정보가 있는 경우 =>참
			member = result.get(); // MemberVO 엔티티빈 타입 객체를 구함
		} else { // 회원정보가 없는 경우 => 거짓
			member = null;
		}
		return member;
	} // 아이디 중복검색

	// 회원저장
	@Override
	public void insertMember(MemberVO m) {
		
		System.out.println(" \n=================>(회원 저장 JPA)");
		this.memberRepo.save(m);
	} // 회원 저장

	// 아이디와 회원이름을 기준으로 회원정보를 검색 
	@Override
	public MemberVO pwdMember(MemberVO m) {
		System.out.println(" \n =================>(비번 검색 JPA");
		MemberVO pm = this.memberRepo.pwdFind(m.getMem_id(), m.getMem_name());
		return pm;
	} // 아이디와 회원이름을 기준으로 회원정보를 검색 => 비번찾기

	
	@Transactional
	// javax.presistence.TransactionRequiredException: Executing an update/delete query 에러가 발생하기 때문에
	// @Transactional 해줘서 트랜잭션을 활성화 해야 한다.
	@Override
	public void updatePwd(MemberVO m) {
		System.out.println(" \n =================> (아이디를 기준으로 암호화 된 임시비번으로 수정 JPA) ");
		this.memberRepo.updatePwd(m.getMem_pwd(), m.getMem_id());
		
	} // 암호화 된 임시비번으로 수정
	
	
}
