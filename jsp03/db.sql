--hrd (쇼핑몰 회원관리 ver1.0)

--회원정보
drop table member_tbl_02 CASCADE CONSTRAINTS;
CREATE TABLE member_tbl_02(
custno number(6) not null primary key,
custname varchar2(20),
phone varchar2(13),
address varchar2(60),
joindate date, --가입일자
grade char(1), -- A:VIP, B:일반, C:직원
city char(2)
);
insert into member_tbl_02 values (100001, '김행복', '010-1111-2222', '서울 동대문구 휘경1동', '20151202', 'A', '01');
insert into member_tbl_02 values (100002, '이축복', '010-1111-3333', '서울 동대문구 휘경2동', '20151206', 'B', '01');
insert into member_tbl_02 values (100003, '장믿음', '010-1111-4444', '울릉군 울릉읍 독도1리', '20151001', 'B', '30');
insert into member_tbl_02 values (100004, '최사랑', '010-1111-5555', '울릉군 울릉읍 독도2리', '20151113', 'A', '30');
insert into member_tbl_02 values (100005, '진평화', '010-1111-6666', '제주도 제주시 외나무골', '20151225', 'B', '60');
insert into member_tbl_02 values (100006, '차공단', '010-1111-7777', '제주도 제주시 감나무골', '20151211', 'C', '60');

select nvl(max(custno) +1,1) from member_tbl_02;

--회원 추가
insert into member_tbl_02 
values ((select nvl(max(custno) +1,1) from member_tbl_02), 
    '차공단', '010-1111-7777', '제주도 제주시 감나무골', '20151211', 'C', '60');

--시퀀스를 쓴다면
create SEQUENCE custseq 
START WITH 100007 ; --시퀀스 시작 번호

select custseq.nextval from dual;

select to_char(joindate, 'YYYY"년" MM"월" DD"일"' )
from member_tbl_02;
--회원조회
select custno, custname, phone, address, to_char(joindate,'YYYY-MM-DD') joindate,
    decode(grade, 'A', 'VIP', 'B', '일반', 'C', '직원') grade, city
from member_tbl_02
order by custno;



--매출정보
drop table money_tbl_02;
CREATE TABLE money_tbl_02(
custno number(6) not null, 
saleno number(8) not null, 
pcost number(8) not null, 
amount number(4) not null, 
price number(8) not null, 
pcode varchar2(4) not null, 
sdate date,
primary key (custno, saleno), 
FOREIGN key (custno) REFERENCES member_tbl_02(custno)
);
insert into money_tbl_02 values (100001, 20160001, 500, 5, 2500, 'A001', '20160101');
insert into money_tbl_02 values (100001, 20160002, 1000, 4, 4000, 'A002', '20160101');
insert into money_tbl_02 values (100001, 20160003, 500, 3, 1500, 'A008', '20160101');
insert into money_tbl_02 values (100002, 20160004, 2000, 1, 2000, 'A004', '20160102');
insert into money_tbl_02 values (100002, 20160005, 500, 1, 500, 'A001', '20160103');
insert into money_tbl_02 values (100003, 20160006, 1500, 2, 3000, 'A003', '20160103');
insert into money_tbl_02 values (100004, 20160007, 500, 2, 1000, 'A001', '20160104');
insert into money_tbl_02 values (100004, 20160008, 300, 1, 300, 'A005', '20160104');
insert into money_tbl_02 values (100004, 20160009, 600, 1, 600, 'A006', '20160104');
insert into money_tbl_02 values (100004, 20160010, 3000, 1, 3000, 'A007', '20160106');

commit;
rollback;
--회원별로 판매번호 구하기
select nvl(max(saleno)+1,1) saleno
from money_tbl_02
where custno = 9000;

--회원별로 매출 조회
select mn.custno, mb.custname, 
    decode(mb.grade,'A','VIP','B', '일반', 'C', '직원') grade,
    sum(mn.price) price
from money_tbl_02 mn join member_tbl_02 mb on(mn.custno = mb.custno)
group by mn.custno, mb.custname, mb.grade
order by price desc;

select * from member;









