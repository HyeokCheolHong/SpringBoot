package net.daum.service;

import net.daum.vo.AdminVO;

// 2024-12-18 Spring Admin Project 실습

public interface AdminService {

	void insertAdmin(AdminVO ab);

	AdminVO adminLogin(String admin_id);
	
}
