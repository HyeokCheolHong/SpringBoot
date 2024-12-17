package net.daum.dao;

import java.util.List;
import java.util.Optional;

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

	@Override
	public void updateHit(int bbs_no) {
//		this.sqlSession.update("bbs_hit", bbs_no); (JPA로 진행하기위해서 주석문 처리)
		
		System.out.println(" \n ==============================> JPA로 자료실 레코드 검색 후 조회수 증가");
		Optional<BbsVO> bbs_hit = this.bbsRepo.findById(bbs_no); // JPA로 번호를 기준으로 레코드 검색
		
		bbs_hit.ifPresent(bbs_hit2 -> { // 검색된 자료가 있는 경우만 실행
			bbs_hit2.setBbs_hit(bbs_hit2.getBbs_hit()+1); // 조회수 1증가
			this.bbsRepo.save(bbs_hit2); // JPA로 번호를 기준으로 조회수 증가
		});
	} // 조회수 증가가

	
	@Override
	public BbsVO getBbs_cont(int bbs_no) {
		
		// return this.sqlSession.selectOne("bbs_co", bbs_no"); (JPA 사용으로 인한 주석문 처리)
		
		System.out.println(" \n ====================> JPA로 내용보기");
		BbsVO bc = this.bbsRepo.getReferenceById(bbs_no);
		/*
		 * getReferenceById() 내장메서드는 번호에 해당하는 레코드가 없는 경우 예외 오류가 나기 때문에 해당값이 있는 경우만 사용한다.
		 */
		return bc;
	} // 내용 보기

	@Override
	public void updateLevel(BbsVO rb) {
//		this.sqlSession.update("levelUp", rb); (JPA 사용으로 인한 주석문 처리)
		
		System.out.println(" \n =====================> JPA로 답변 레벨 증가");
		this.bbsRepo.updateLevel(rb.getBbs_ref(), rb.getBbs_level());
		
	} // 답변 레벨 증가

	@Override
	public void replyBbs(BbsVO rb) {
//		this.sqlSession.insert("reply_in2", rb); (JPA 사용으로 인한 주석문 처리)
		
		System.out.println(" \n ==================> JPA로 자료실 답변 저장");
		int bbsSeq_no = this.bbsRepo.getNextSequenceValue(); // 다음 시퀀스 번호값을 가져옴
		rb.setBbs_no(bbsSeq_no); // 시퀀스 다음 번호값을 자료실 번호로 저장
		rb.setBbs_step(rb.getBbs_step()+1);
		rb.setBbs_level(rb.getBbs_level()+1);
		
		this.bbsRepo.save(rb); // 답변 저장
		
	} // 답변 저장

	@Override
	public void editBbs(BbsVO bbs) {
		// this.sqlSession.update("bbs_edit", bbs);
		
		System.out.println(" \n ================> JPA로 자료실 수정 ");
		this.bbsRepo.updateBbs(bbs.getBbs_name(), bbs.getBbs_title(), bbs.getBbs_cont(), bbs.getBbs_file(), bbs.getBbs_no());
	}

	@Override
	public void delBbs(int bbs_no) {
		// this.sqlSession.delete("bbs_del", bbs_no);
		
		System.out.println(" \n =======================> JPA로 자료실 삭제");
		this.bbsRepo.deleteById(bbs_no);
	}
}
