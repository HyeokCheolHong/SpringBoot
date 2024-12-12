-- 2024-12-12 Spring Security 실습
-- member7_no_seq 시퀀스 cache를 nocache로 수정
alter sequence member7_no_seq
nocache;

select * from tbl_members7 order by mem_id;

select * from tbl_member_roles7 order by fno desc;