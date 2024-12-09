-- 2024-12-09 JPA 실습

--bno_seq8 시퀀스 메모리를 nocache로 수정
alter sequence bno_seq8
nocache; -- nocache로 수정

select * from tbl_boards3 order by bno desc;