package net.daum.dao;

import java.util.Optional;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.AdminVO;

// 2024-12-18 Spring Admin Project 실습

@Repository
public class AdminDAOImpl implements AdminDAO {
	
	@Autowired
	private SqlSession sqlSession; // mybatis 쿼리문 실행
	
	@Autowired
	private AdminRepository adminRepo; // JPA를 실행하기 위한 자동의존성 주입(DI:Dependency Injection)
	// 의존성 주입하는 이유 : Spring으로 수작업을 하는것이아닌 기능을 사용하기 위해

	@Override
	public void insertAdmin(AdminVO ab) {
//		this.sqlSession.insert("admin_in", ab); // JPA 사용으로 인한 주석처리
		
		System.out.println(" \n ================> JPA로 Admin 정보 저장");
		this.adminRepo.save(ab);
	}

	@Override
	public AdminVO adminLogin(String admin_id) {
//		return this.sqlSession.selectOne("admin_login", admin_id); // JPA 사용으로 인한 주석처리
		// mybatis에서 selectOne()메서드는 단 한개의 레코드 검색
		
		System.out.println(" \n ================> JPA로 관리자 로그인 인증");
		Optional<AdminVO> result = this.adminRepo.findById(admin_id);
		AdminVO admin;
		if(result.isPresent()) { // 관리자 아이디에 해당하는 관리자가 있다면 참
			admin = result.get();
		} else {
			admin = null;
		}
		
		return admin;
	}
	
	
	
	
}
