package net.daum;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.daum.dao.BoardRepository;
import net.daum.vo.BoardVO;

//2024-12-09 JPA 실습

@SpringBootTest
public class Boot03ApplicationTests {

	@Autowired
	private BoardRepository BoardRepo;
	
	@Test
	public void testBoardInsert20() {
		
		for(int i = 1; i <= 20; i++) {
			BoardVO b = new BoardVO();
			
			b.setWriter("user0" + (i%10));
			b.setTitle("게시판 제목...:" + i);
			// Query에서 활용하기 위해서 ...: 을 넣어준다
			
			b.setContent("내용..." + i);
			
			// 아래 제목으로 검색하기를 하기 위해서 새로 추가되지 않게하기위한 주석처리
			// this.BoardRepo.save(b);
		}
	}
	
	// 쿼리메서드 중에서 제목으로 검색
//	@Test
//	public void testByTitle() {
		
		// 자바 8 이전
		/* List<BoardVO> blist = this.BoardRepo.findBoardVOByTitle("게시판 제목...:17");
		
		if(blist != null && blist.size() > 0) {
			// size()메서드는 컬렉션 원소개수를 반환
			for(int i = 0; i < blist.size(); i++) {
				System.out.println(blist.get(i));
				// get()메서드로 원소값을 가져온다.
			}
		} else {
			System.out.println("게시판 목록이 없습니다!");
		}
		*/ // java 8 이후 version인 람다식 표현을 위한 주석처리
		
		// 자바 8 이후
		// 새로운 생성을 위한 주석처리
		//this.BoardRepo.findBoardVOByTitle("게시판 제목...:17").forEach((board) -> System.out.println(board));
//	}
	
//	@Test
//	public void testByWriter() {
//		Collection<BoardVO> blist = this.BoardRepo.findByWriter("user00");
//		blist.forEach(b -> {
//			System.out.println(b);
//		});
//		// 주석처리 특정 단어 검색 확인을 위한 주석처리 
//	}
	
		
	// 글쓴이에 05가 포함된 게시물을 검색 => '%'+'05'+'%' like 검색
//	@Test
//	public void testByWriterContaining() {
//		Collection<BoardVO> blist = this.BoardRepo.findByWriterContaining("05");
//		blist.forEach(b->System.out.println(b));
//	}
	
	// 제목에 '2'가 포함되거나 내용에 '5'가 포함된 경우
//	@Test
//	public void testByTitleOrContentContaining() {
//		Collection<BoardVO> blist = this.BoardRepo.findByTitleContainingOrContentContaining("2", "5");
//		blist.forEach(b->System.out.println(b));
//	}
	
	
	// 제목에 '5'가 포함되어 있고 게시물 번호가 5보다 큰 자료를 검색
//	@Test
//	public void testByTitleContainingAndBnoGreaterThan() {
//		Collection<BoardVO> blist = BoardRepo.findByTitleContainingAndBnoGreaterThan("5", 5);
//		blist.forEach(b->System.out.println(b));
//	}
	
	// bno가 10보다 큰 게시물번호를 기준으로 내림차순 정렬
//	@Test
//	public void testBnoOrderBy() {
//		Collection<BoardVO> blist = BoardRepo.findByBnoGreaterThanOrderByBnoDesc(10);
//		blist.forEach(b->System.out.println(b));
//	}
	
	// 제목이 들어간 title검색
//	@Test
//	public void testByTitle2() {
//		this.BoardRepo.findByTitle("제목").forEach(b->System.out.println(b));
//	}
	
	// @Param 내용에 대한 검색 처리
//	@Test
//	public void testByContent2() {
//	  this.BoardRepo.findByContent("내용").forEach(board -> System.out.println(board));
//	}
	
	// 원하는 컬럼만 추출할때(title="제목")인경우 Object 리턴타입을 가지는 경우
	@Test
	public void testByTitle3() {
		this.BoardRepo.findByTitle2("제목").forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}
