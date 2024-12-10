package net.daum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import net.daum.vo.Profile;

//2024-12-10 JPA 연관관게 수업

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
