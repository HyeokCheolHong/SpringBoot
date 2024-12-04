package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.daum.dao.MessageDAO;
import net.daum.dao.PointDAO;
import net.daum.vo.MessageVO;

//2024-12-04 스프링 AOP와 트랜잭션 실습을 위한 코드

@Service // @Service 애노테이션을 설정함으로써 스프링에서 서비스로 인식한다.
public class MessageServiceImpl implements MessageService {

	// Service Impl에서는 MessageDAO와 PointDAO가 동시에 코드된다.
	
	@Autowired
	private MessageDAO messageDao; // messageDAO => message.xml => tbl_message 테이블

	@Autowired
	private PointDAO pointDao; // pointDAO => point.xml => tbl_user 테이블
	
	
	// 기존에는 sql에 잘못된 데이터가 들어가면 uppoint가 상승안하면서 message에는 기록을 남김
	@Transactional // 트랜잭션 적용
	// 트랜잭션 추가함으로써 sql에 잘못된 데이터가 들어갈때 uppoint가 상승 안하면서 message가 남지 않도록함
	@Override
	public void addMessage(MessageVO vo) {
		this.messageDao.insertMem(vo); // 메시지추가
		this.pointDao.updatePoint(vo.getSender(), 10);
		// 메시지 추가와 메시지 하나 저장(전송)시 포인트 점수 10점 업데이트
	}
}
