package net.daum.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import net.daum.vo.BoardVO;

//2024-12-09 JPA 실습

public interface BoardRepository extends JpaRepository<BoardVO, Integer> {
	
	/*
	 * 쿼리 메서드 작성 : 메서드 이름만으로 원하는 질의(쿼리 [검색] : select)를 만들어 내는 메서드를 의미한다.
	 */
	
	public List<BoardVO> findBoardVOByTitle(String title);
	// 쿼리 메서드 중에서 BoardVO는 엔티티빈 클래스명, Title은 컬럼명 => find+엔티티클래스명 + By + 컬럼명
	// 제목으로 검색											find| BoardVO  | By | Title
	
	public Collection<BoardVO> findByWriter(String name);
	// Writer는 엔티티빈 클래스의 속성인 멤버변수명 => findBy+속성명
	// 글쓴이로 검색                            findBy+Writer
	
	// 글쓴이에 대한 like % 검색어키워드 % => '%'+검색어+'%' (Containing)
	/*
	 * findBy와 더불어 가장 많이 사용하는 구문은 like 검색연산자이다.
	 * like 4가지 형태의 쿼리메서드 종류
	 * 형태				쿼리메서드
	 * 단순like			Like	
	 * 키워드+'%'			StartingWith
	 * '%'+검색키워드		EndingWith
	 * '%'+검색키워드+'%'	Containing
	 * 
	 * %는 SQL문에서 와일드카드 문자로 하나이상의 임의의 모르는 문자와 매핑대응한다.
	 */
	public Collection<BoardVO> findByWriterContaining(String writer);
	
	
	// 제목 or 내용 조건 처리 => '%'+제목+'%' + or + '%'+내용+'%'
	public Collection<BoardVO> findByTitleContainingOrContentContaining(String title, String cont);
	
	// title like %?% And Bno > ? , >은 GreaterThan, <은 LessThan        [GreaterThan 을 변수 명에 넣음으로써 bno > 가 성립이 됨]
	public Collection<BoardVO> findByTitleContainingAndBnoGreaterThan(String title, int bno);
	
	// bno > ? order bv bno desc => 게시물 번호 bno가 특정 번호 보다 큰 게시물을 bno 기준으로 내림차순 정렬
	public Collection<BoardVO> findByBnoGreaterThanOrderByBnoDesc(int bno);
	
	/*
	 * JPQL(JPA에서 사용하는 Query Language 이다. Java Persistence Query Language의 약어이다.
	 * JPQL문은 실제 테이블명 대신 엔티티빈 클래스명을 이용하고, 실제 테이블 컬럼명 대신 엔티티빈 클래스의 속성명을 사용한다.
	 */
	@Query("select b from BoardVO b where b.title like %?1% and b.bno > 0 order by b.bno desc")
	public List<BoardVO> findByTitle(String title);
	
	@Query("select b from BoardVO b where b.content like %:content% and b.bno>0 order by b.bno desc")
	//:content는 @Param("content")와 연결된다.
	public List<BoardVO> findByContent(@Param("content") String content);
	
	@Query("select b.bno, b.title, b.writer, b.regdate from BoardVO b where b.title like %?1% and b.bno>0 order by b.bno desc")
	// 원하는 컬럼만 추출할 때는 리턴타입을 Object[] 타입으로 한다.
	public List<Object[]> findByTitle2(String title);
	
}
