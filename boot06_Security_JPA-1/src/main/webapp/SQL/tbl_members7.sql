-- 2024-12-12 Spring Security 실습
-- member7_no_seq 시퀀스 cache를 nocache로 수정
alter sequence member7_no_seq
nocache;

select * from tbl_members7 order by mem_id;

select * from tbl_member_roles7 order by fno desc;


-- 2024-12-13 Spring Security 사용사 자동로그인 실습
-- 스프링 시큐리티 자동로그인 정보를 저장유지할 테이블 생성
create table persistent_logins(
	username varchar2(64) not null, -- 아이디
	series varchar2(64) primary key, -- 비번
	token varchar2(64) not null, -- 토큰
	last_used timestamp not null -- 로그인 한 날짜 시간
);

select * from persistent_logins;