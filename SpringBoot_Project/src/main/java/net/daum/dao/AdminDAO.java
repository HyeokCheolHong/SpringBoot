package net.daum.dao;

import net.daum.vo.AdminVO;

// 2024-12-18 Spring Admin Project 실습

public interface AdminDAO {

	void insertAdmin(AdminVO ab);

	AdminVO adminLogin(String admin_id);

}
