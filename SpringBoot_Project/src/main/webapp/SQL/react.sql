-- 2024-12-18 React-Spring 연결 게시판 실습
-- react_no_seq 시퀀스 생성
create sequence react_no_seq
start with 1
increment by 1
nocache;

select * from board_form_data order by no desc;