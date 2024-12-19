package net.daum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.daum.service.ReactService;
import net.daum.vo.BoardFormDataVO;

//2024-12-18 React-Spring 연결 게시판 실습

@RestController
public class ReactController {
	
	@Autowired
	private ReactService reactService;
	
	@GetMapping("/index_react")
	public ModelAndView index_react() {
		return new ModelAndView("./react/index_react");
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	/*
	 * CORS (Cross-Origin Resource Sharing) 정책이 발생합니다. 이 정책은 웹 브라우저에서 실행되는 JavaScript가 다른 출처의 리소스에 액세스하는 것을 제한합니다. 
	 * 브라우저는 보안상의 이유로 스크립트가 다른 출처의 리소스에 액세스하는 것을 허용하지 않습니다.
	 * 서버 측에서 CORS 문제를 해결하려면 서버 응답 헤더에 Access-Control-Allow-Origin 헤더를 포함해야 합니다. 이 헤더는 허용된 출처를 지정하는데 사용됩니다.
	 * 스프링 부트에서 CORS를 활성화하려면 다음과 같이 설정할 수 있습니다.
	 * 위의 코드에서 @CrossOrigin(origins = "http://localhost:3000") 어노테이션을 사용하여 특정 출처 (여기서는 http://localhost:3000)에서의 요청을 허용하도록 설정했습니다. 
	 * 따라서 리액트 애플리케이션 (http://localhost:3000)에서의 요청이 허용됩니다.
	 * 또는 모든 출처에서의 요청을 허용하려면 다음과 같이 설정할 수도 있습니다: java @CrossOrigin(origins = "*")
	 * 그러나 보안상의 이유로 가능한 한 출처를 제한하는 것이 좋습니다. 필요에 따라 다른 CORS 설정도 추가로 사용할 수 있습니다.
    */
	@PostMapping("/board_FormData_ok") // 리액트 게시판 저장
	public String handleFormData(@RequestBody BoardFormDataVO boardData) {
		// 전송된 JSON 데이터를 @RequestBody 애노테이션에 의해서 BoardFormDataVO 객체타입으로 변환한다
		
		this.reactService.reactInsert(boardData);
		
		return "{\"message\" : \"Data received successfully\"}";
	}
	
	// 리액트가 아닌 jQuery 비동기식 자료 저장
	@PostMapping("/board_FormData_ok2")
	public ResponseEntity<String> board_FormData_ok2(@RequestBody BoardFormDataVO vo) {
		
		ResponseEntity<String> entity=null;
		
		try {
			this.reactService.reactInsert(vo);
			// 댓글 등록
			
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			// 댓글 저장 성공시 '성공'문구와 200 OK 상태 코드 반환
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			// 예외 에러 발생시 예외 에러 메시지와 400 BAD 상태 코드 반환
		}
		
		return entity;
	}
	
	// 리액트 게시판 목록
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/board_FormData_List")
	public ResponseEntity<List<BoardFormDataVO>> board_FormData_list() {
		
		ResponseEntity<List<BoardFormDataVO>> entity=null;
		
		try {
			entity = new ResponseEntity<>(this.reactService.boardFormDatalist(), HttpStatus.OK);
			// 댓글 저장 성공시 '성공'문구와 200 OK 상태 코드 반환
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			// 예외 에러 발생시 예외 에러 메시지와 400 BAD 상태 코드 반환
		}
		
		return entity;
	} // board_FormData_list()
	
	// 번호에 해당하는 리액트 게시판 내용보기
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/board_FormData_cont/{no}")
	public ResponseEntity<BoardFormDataVO> board_FormData_cont(@PathVariable int no) {
		// 브라우저 주소창에서 전달된 게시판 번호를 {no} @PathVariable 애노테이션으로 추출한다.
		
		ResponseEntity<BoardFormDataVO> entity = null;
		
		try {
			entity = new ResponseEntity<>(this.reactService.getBoardDataCont(no), HttpStatus.OK);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	} // board_FormData_Cont
	
	
	// 리액트 게시판 수정
	@CrossOrigin(origins = "http://localhost:3000")
	@PutMapping("/board_FormData_Edit/{no}")
	public ResponseEntity<String> board_FormData_Edit(@PathVariable int no, @RequestBody BoardFormDataVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			vo.setNo(no); // 번호 저장
			this.reactService.updateBoard(vo); // 게시판 수정
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	} // board_FormData_Edit()
	
	
	// 리액트 게시판 삭제
	@CrossOrigin(origins = "http://localhost:3000")
	@DeleteMapping("/board_FormData_Del/{no}")
	public ResponseEntity<String> board_FormData_del(@PathVariable int no) {
		
		ResponseEntity<String> entity = null;
		
		try {
			this.reactService.delBoard(no); // 게시물 삭제
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			
			/*
			 * 문제) JPA로 번호에 해당하는 게시글 삭제되게 만들어 보고, 백엔드 테스트는 ARC에서 한 다음
			 * 		웹브라우저에서 최종적으로 리액트 컴포넌트UI와 스프링과 연동해서 삭제되게 해본다.
			 */
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	} // board_FormData_Del()
	
}
