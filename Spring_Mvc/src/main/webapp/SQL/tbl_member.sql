--tbl_member8테이블 생성
create table tbl_member8(
	userid varchar2(50) primary key, --회원 아이디
	userpw varchar2(100) not null, -- 회원 비밀번호
	username varchar2(20) not null, -- 회원 이름
	email varchar2(100), -- 회원 이메일
	regdate date default sysdate -- 가입날짜
);

select * from tbl_member8;

--tbl_board테이블을 생성
create table tbl_board(
	bno number(38) primary key, -- 게시판 번호
	writer varchar2(50) not null, -- 글 작성자
	title varchar2(200) not null, -- 글 제목
	content varchar2(4000) not null, -- 글 내용
	viewcnt int default 0, -- 조회수 / default 0 제약조건 할당시 해당 컬럼에 레코드 저장하지 않아도 기본값인 0이 저장된다.
	regdate date -- 글 등록날짜
);

select * from tbl_board order by bno desc;

-- 2024-12-04 작성
-- 사이트 완성후 고객이 게시판 내용 밑의 댓글수를 카운터해서 출력해 달라는 요구사항
-- => tbl_board 게시판테이블에 댓글수를 카운터해서 저장할 컬럼 replycnt를 추가
alter table tbl_board add (replycnt int default 0);

-- tbl_reply 댓글 테이블의 게시판번호에 해당하는 갯글수를 카운터해서 tbl_board 테이블에 생성된 replycnt 컬럼 레코드로 업데이트
update tbl_board set replycnt = (select count(rno) from tbl_reply where bno=tbl_board.bno) where bno > 0;

-- 2024-12-04 여기까지

--bno_seq 시퀀스 생성
create sequence bno_seq
	start with 1 -- 1부터 시작
	increment by 1 -- 1씩 증가
	nocache -- 임시메모리 사용 안함
;

--bno_seq 시퀀스 다음번호값 확인
select bno_seq.nextval as "시퀀스 번호" from dual;

-- 2024-12-02 댓글 DB 작성 SQL
--tbl_reply 댓글 테이블 작성
CREATE TABLE tbl_reply (
    rno NUMBER(38) PRIMARY KEY, -- 댓글 번호
    bno NUMBER(38) DEFAULT 0, -- 외래키(foreign key) 제약조건으로 추가 설정. tbl_board 게시판 테이블의 bno 컬럼 번호값만 저장됨
    replyer VARCHAR2(100) NOT NULL, -- 댓글 작성자
    replytext VARCHAR2(4000) NOT NULL, -- 댓글 내용
    regdate DATE, -- 등록 날짜
    updatedate DATE -- 수정 날짜
);

select * from tbl_reply order by rno desc;

-- 외래키 추가설정
alter table tbl_reply add constraint tbl_reply_bno_fk
foreign key(bno) references tbl_board(bno);

-- 댓글 시퀀스 생성
create sequence rno_seq
start with 1
increment by 1
nocache;

-- rno_seq 다음 시퀀스 번호값 확인
select rno_seq.nextval as "다음 시퀀스 번호값" from dual;


-- 2024-12-04 스프링 AOP와 트랜잭션 실습을 위한 테이블 설계
create table tbl_user(
	uid2 varchar2(50) primary key, -- 회원 아이디
	upw varchar2(100) not null, -- 회원 비밀번호
	uname varchar2(50) not null, -- 회원 이름
	upoint number(38) default 0 -- 메시지가 저장되면 포인터 점수 10점 업데이트
);

insert into tbl_user (uid2, upw, uname) values('user00', '77777', '홍길동');
insert into tbl_user (uid2, upw, uname) values('user01', '88888', '강감찬');

select * from tbl_user order by uid2 asc;

update tbl_user set upoint=10 where uid2='user01';

create table tbl_message(
	mid number(38) primary key,
	targetid varchar2(50) not null, -- 나중에 추가적인 외래키 설정. tbl_user테이블의 uid2 컬럼 아이디값만 저장됨
	sender varchar2(30) not null, -- 메시지를 보낸사람
	message varchar2(4000) not null, -- 보낸 메시지
	senddate date -- 메시지를 보낸 날짜
);

-- targetid 컬럼에 추가적인 외래키(foreign key)를 설정
alter table tbl_message add constraint tbl_message_targetid_fk foreign key(targetid) references tbl_user(uid2);

-- mid_no_seqe 시퀀스 생성
create sequence mid_no_seq
	start with 1
	increment by 1
	nocache;

-- mid_no_seq 시퀀스 번호를 생성
select mid_no_seq.nextval as "시퀀스 번호값" from dual;

select * from tbl_message order by mid asc;

delete from tbl_message where mid=5;


-- 2024-12-04 문제) 공지사항 만들기 tbl_gongji 테이블 생성
create table tbl_gongji(
	gno number(38) primary key, -- 공지번호
	gname varchar2(50) not null, -- 공지작성자
	gtitle varchar2(200) not null, -- 공지제목
	gcont varchar2(4000) not null, -- 공지내용
	ghit number(38) default 0, -- 공지 조회수
	gdate date -- 공지 작성일자
);

select * from tbl_gongji order by gno asc;

-- 2024-1204 문제) 공지사항 만들기 tbl_gongji 시퀀스 생성
create sequence gno_seq
	start with 1
	increment by 1
	nocache
	nocycle;
	
-- gno_seq 시퀀스 번호 확인
	select gno_seq.nextval as "공지 번호" from dual;
	

-- 2024-12-05 문제) 공지사항 만들기 정답
create table tbl_gongji_teacher(
	gno number(38) primary key, -- 공지번호
	gname varchar2(50) not null, -- 공지작성자
	gtitle varchar2(200) not null, -- 공지제목
	gcont varchar2(4000) not null, -- 공지내용
	ghit number(38) default 0, -- 공지 조회수
	gdate date -- 공지 작성일자
);

alter table tbl_gongji_teacher modify gdate date default sysdate;

select * from tbl_gongji_teacher order by gno asc;

-- 2024-1204 문제) 공지사항 만들기 정답 tbl_gongji_teacher 시퀀스 생성
create sequence gno_seq_teacher
	start with 1
	increment by 1
	nocache
	nocycle;
	
-- gno_seq 시퀀스 번호 확인
	select gno_seq_teacher.nextval as "공지 번호" from dual;