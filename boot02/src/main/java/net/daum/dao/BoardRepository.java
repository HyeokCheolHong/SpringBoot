package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.BoardVO;

//2024-12-09 JPA 실습

public interface BoardRepository extends JpaRepository<BoardVO, Integer> {
	
	/*
	 * JPARepository 인터페이스의 부모 인터페이스인 PagingAndSortingRepository는 페이징과 정렬이라는 기능을 제공한다.
	 * PagingAndSortingRepository의 부모 인터페이슨 CRUDRespository이다.
	 * CRUDRepository의 부모 인터페이스는 Repository 이다.
	 * <BoardVO, Integer> 제네릭타입의 첫번째요소는 해당 Entity Bean Class명이고,
	 * 두번째 요소는 해당 엔티티빈 클래스에 설정된 @Id 컬럼 즉 기본키로 설정된 해당 변수 타입을 지정하면 된다.
	 * 그런데 기본타입은 제네릭 타입으로 설정하지 못하기 때문에 해당 타입의 참조 즉 래퍼타입으로 설정하면 된다.
	 */
}
