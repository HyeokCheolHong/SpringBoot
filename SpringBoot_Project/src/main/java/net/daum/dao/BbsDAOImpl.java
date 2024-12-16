package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

// 2024-12-16 Spring Project 


@Repository
public class BbsDAOImpl implements BbsDAO {

	
	@Autowired
	private SqlSession sqlSession; // mybatis 쿼리문 수행 sqlSession 자동의존성 주입
	
	@Autowired
	private BbsRepository bbsRepo; // JPA를 사용할려고 자동의존성 주입(DI)

	@Override
	public void insertBbs(BbsVO bbs) {
//		this.sqlSession.insert("bbs_in", bbs);
		System.out.println("\n ==========================> bbs_no_seq 시퀀스 번호값 가져오기와 자료실 저장 JPA");
		long bbsSeq_no = bbsRepo.getNextSequenceValue();
		// 오토 언박싱해서 시퀀스 다음번호값을 구함
		
		bbs.setBbs_no((int)bbsSeq_no); // 명시적인 다운캐스팅해서 자료실 번호값 저장
		
		bbs.setBbs_ref((int)bbsSeq_no); // 명시적인 다운캐스팅해서 글그룹번호 저장
		
		this.bbsRepo.save(bbs); // JPA로 자료실 저장
		
	}// 자료실 저장

	@Override
	public int getRowCount(PageVO p) {
		return this.sqlSession.selectOne("bbs_count", p);
		// mybatis에서 selectOne()메서드는 단 한개의 레코드만 반환
	}

	@Override
	public List<BbsVO> getBbslist(PageVO p) {
		return this.sqlSession.selectList("bbs_list", p);
		// mybatis에서 selectList()메서드는 하나이상의 레코드를 검색해서 컬렉션 List로 반환하고
		// bbs_list는 bbs.xml에서 설정할 유일 아이디명이다.
	}
}
