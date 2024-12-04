package net.daum.dao;

import net.daum.vo.MessageVO;

//2024-12-04 스프링 AOP와 트랜잭션 실습을 위한 코드

public interface MessageDAO {

	void insertMem(MessageVO vo);

}
