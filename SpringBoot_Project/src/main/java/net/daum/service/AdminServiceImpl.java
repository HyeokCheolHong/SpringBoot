package net.daum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.daum.dao.AdminDAO;
import net.daum.vo.AdminVO;

// 2024-12-18 Spring Admin Project 실습

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDao;

	@Override
	public void insertAdmin(AdminVO ab) {
		this.adminDao.insertAdmin(ab);
	}

	@Override
	public AdminVO adminLogin(String admin_id) {
		return this.adminDao.adminLogin(admin_id);
	}
	
	
}
