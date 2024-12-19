package net.daum.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.daum.vo.BbsVO;
import net.daum.vo.PageVO;

//2024-12-18 Spring Admin Project 실습

@Repository
public class AdminBbsDAOImpl implements AdminBbsDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private AdminBbsRepository adminBbsRepo;

	@Override
	public int getRowCount(PageVO p) {
		return this.sqlSession.selectOne("abbs_count", p);
	} // 관리자 자료실 검색전후 레코드 개수

	@Override
	public List<BbsVO> getBbsList(PageVO p) {
		return this.sqlSession.selectList("abbs_list", p);
	} // 관리자 자료실 검색전후 목록

	@Override
	public void insertBbs(BbsVO bbs) {
		System.out.println("\n ==========================> bbs_no_seq 시퀀스 번호값 가져오기와 관리자 자료실 저장 JPA");
		long bbsSeq_no = this.adminBbsRepo.getNextSequenceValue();
		// 오토 언박싱해서 시퀀스 다음번호값을 구함
		
		bbs.setBbs_no((int)bbsSeq_no); // 명시적인 다운캐스팅해서 자료실 번호값 저장
		
		bbs.setBbs_ref((int)bbsSeq_no); // 명시적인 다운캐스팅해서 글그룹번호 저장
		
		this.adminBbsRepo.save(bbs); // JPA로 자료실 저장
	} // 관리자 자료실 저장

	@Override
	public BbsVO getAdminBbsCont(int no) {
//		return this.sqlSession.selectOne("abbs_cont", no); JPA 사용으로 인한 주석처리
		
		System.out.println(" \n =====================> JPA로 관리자 자료실 조회수 증가 안되는 내용보기");
		BbsVO bc = this.adminBbsRepo.getReferenceById(no);
		return bc;
	} // 관리자 자료실 상세정보 보기+수정

	@Override
	public void adminUpdateBbs(BbsVO bbs) {
//		this.sqlSession.update("abbs_edit", bbs);
		
		System.out.println("\n ==========================> JPA로 관리자 자료실 수정");
		this.adminBbsRepo.adminEditBbs(bbs.getBbs_name(), bbs.getBbs_title(), bbs.getBbs_cont(), bbs.getBbs_file(), bbs.getBbs_no());
		
		/*
		 * 문제) 개발자 테스트
		 */
	} // 관리자 자료실 수정완료

	@Override
	public void adminDeleteBbs(int no) {
		System.out.println(" \n ====================> JPA로 관리자 자료실 삭제");
		this.adminBbsRepo.adminDeleteBbs(no);
	}

	
	
}
