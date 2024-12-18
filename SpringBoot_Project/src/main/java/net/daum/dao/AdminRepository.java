package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.AdminVO;

// 2024-12-18 Spring Admin Project 실습 (JPARepository를 상속받으면 최종부모의 상속도 전부 받을수 있다)

public interface AdminRepository extends JpaRepository<AdminVO, String> {

}
