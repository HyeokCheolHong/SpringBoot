package net.daum.dao;

//2024-12-04 스프링 AOP와 트랜잭션 실습을 위한 코드

public interface PointDAO {
	
	void updatePoint(String sender, int point);
}
