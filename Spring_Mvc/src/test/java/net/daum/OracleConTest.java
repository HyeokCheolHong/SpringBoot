package net.daum;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.jupiter.api.Test;

// 오라클 연결 테스트 코드 클래스
public class OracleConTest {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	// oracle.jdbc는 패키지명, OracleDriver는 오라클 jdbc 드라이버 클래스명
	
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	// 오라클 접속주소 / 1521 : 오라클 연결 포트번호 / xe : 전역 데이터베이스 명
	
	private static final String USER = "fintech";
	// 오라클 사용자 명
	private static final String PW = "56789";
	// 오라클 사용자 비밀번호
	
	@Test
	public void testCon() throws Exception{
		Class.forName(DRIVER);
		//jdbc 드라이버 클래스 로드
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			// 자바 7버전에서 AutoCloseable 인터페이스를 구현상속받은 자손은 try() 소괄호내에서 con을 생성하면 finally문에서 명시적으로 close()를 하지않아도 자동으로 닫힌다.
			System.out.println(con);
			// Oracle Driver 접속주소 출력
		} catch (Exception e) {
			e.printStackTrace();
			// 예외 족적을 남김
		}
	}
}
