package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.BbsVO;

//2024-12-18 Spring Admin Project 실습

public interface AdminBbsRepository extends JpaRepository<BbsVO, Integer> {
	
}
