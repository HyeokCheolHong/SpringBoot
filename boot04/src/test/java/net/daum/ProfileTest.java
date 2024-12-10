package net.daum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import lombok.extern.java.Log;
import net.daum.dao.MemberRepository;
import net.daum.dao.ProfileRepository;
import net.daum.vo.MemberVO;
import net.daum.vo.Profile;

@SpringBootTest
@Log // Lombok로그를 이용할때 사용하는 애너테이션
@Commit // 테스트 결과를 데이터베이스에 commit하는 용도로 사용

//2024-12-10 JPA 연관관게 수업

public class ProfileTest {
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private ProfileRepository profileRepo;
	
	// 20명 회원 데이터 추가
	@Test
	public void testInsertMembers() {
		
		IntStream.range(1,21).forEach(i -> { // 1부터 20까지의 숫자를 생성
			
			MemberVO m = new MemberVO();
			
			m.setUid2("user"+i);
			m.setUpw("pw"+i);
			m.setUname("name"+i);
			
//			this.memberRepo.save(m);
//			추가적인 회원Data 생성을 막기위한 주석처리
			
		});
	}
	
	// 특정회원에 프로필 사진을 추가
	@Test
	public void testInsertProfile() {
		
		MemberVO member = new MemberVO();
		member.setUid2("user1");
		
		for(int i=1; i<5; i++) {
			Profile profile01 = new Profile();
			
			profile01.setFname("face"+i+".jpg");
			if(i==1) {
				profile01.setCurrent2(true);
				// face1.jpg가 현재 사용중인 프로필 사진
			}
			
			profile01.setMember(member);
			
//			this.profileRepo.save(profile01);
//			추가적인 프로필사진Data 생성을 막기위한 주석처리
		}
	} // testInsertProfile()
	
//	// user1 아이디 정보와 프로필 사진 개수 => Fetch Join
//	// MemberRepository의 getMemberVOWithProfileCount() 함수
//	@Test
//	public void testFetchJoin01() {
//		List<Object[]> result = this.memberRepo.getMemberVOWithProfileCount("user1");
//		
//		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
//	} // testFetchJoin()
//	단방향 Fetch Join2 을 위한 주석처리
	
	// 단방향 Fetch Join2 => user1 회원 아이디 정보와 현재 사용중인 프로필 사진 정보
	@Test
	public void testFetchJoin02() {
		List<Object[]> result = memberRepo.getMemberVOWithProfile("user1");
		result.forEach(arr -> System.out.println(Arrays.toString(arr)));
	}
}
