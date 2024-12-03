package net.daum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.daum.dao.MemberDAO;
import net.daum.vo.MemberVO;


@SpringBootTest
public class MemberDAOTest {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Test // JUit Test
	public void testInsertMember() throws Exception{
		MemberVO m = new MemberVO();
		
		m.setUserid("abcde123");
		m.setUserpw("77777");
		m.setUsername("홍길동");
		m.setEmail("123@123");
		
		this.memberDAO.insertMember(m);
		// 회원 저장
	}
}
