package net.daum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.daum.service.MessageService;
import net.daum.vo.MessageVO;

//2024-12-04 스프링 AOP와 트랜잭션 실습을 위한 코드

@RestController
@RequestMapping("/message") // 컨트롤러 자체 매핑주소 등록
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	// 메시지 추가
	// @PostMapping("insertMessage")와 동일하나 @PostMapping나오기전 사용
	@RequestMapping(value="/insertMessage", method=RequestMethod.POST) // post로 접근하는 매핑주소를 처리, insertMessage 매핑주소 등록
		// 주소등록
	
	public ResponseEntity<String> insertM(@RequestBody MessageVO vo) {
		/*
		 * @RequestBody 애노테이션은 전송된 JSON 데이터를 MessageVO 객체타입으로 변환을 해준다.
		 */
		
		ResponseEntity<String> entity = null;
		
		try {
			this.messageService.addMessage(vo);// 메시지 추가
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK); // 메시지 추가 성공시 SUCCESS문자와 HttpStatus.OK 상태인 200 상태코드 반환
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
