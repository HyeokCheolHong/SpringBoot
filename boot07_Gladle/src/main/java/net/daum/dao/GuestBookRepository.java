package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.GuestBookVO;

public interface GuestBookRepository extends JpaRepository<GuestBookVO, Integer> {
	
	/*
	 * <>제네릭 타입 첫번째 인자값은 GuestBookVO 엔티티빈 타입이고,
	 * 두번째 인자값은 엔티티빈 클래스의 속성중에 @Id로 설정해 놓은 유일키속성의 타입이다.
	 * 해당 타입은 기본타입으로는 제네릭 타입 설정을 못하기 때문에 참조 래퍼타입으로 설정함.
	 */
	
	
}
