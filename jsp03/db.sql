--hrd (���θ� ȸ������ ver1.0)

--ȸ������
drop table member_tbl_02 CASCADE CONSTRAINTS;
CREATE TABLE member_tbl_02(
custno number(6) not null primary key,
custname varchar2(20),
phone varchar2(13),
address varchar2(60),
joindate date, --��������
grade char(1), -- A:VIP, B:�Ϲ�, C:����
city char(2)
);
insert into member_tbl_02 values (100001, '���ູ', '010-1111-2222', '���� ���빮�� �ְ�1��', '20151202', 'A', '01');
insert into member_tbl_02 values (100002, '���ູ', '010-1111-3333', '���� ���빮�� �ְ�2��', '20151206', 'B', '01');
insert into member_tbl_02 values (100003, '�����', '010-1111-4444', '�︪�� �︪�� ����1��', '20151001', 'B', '30');
insert into member_tbl_02 values (100004, '�ֻ��', '010-1111-5555', '�︪�� �︪�� ����2��', '20151113', 'A', '30');
insert into member_tbl_02 values (100005, '����ȭ', '010-1111-6666', '���ֵ� ���ֽ� �ܳ�����', '20151225', 'B', '60');
insert into member_tbl_02 values (100006, '������', '010-1111-7777', '���ֵ� ���ֽ� ��������', '20151211', 'C', '60');

select nvl(max(custno) +1,1) from member_tbl_02;

--ȸ�� �߰�
insert into member_tbl_02 
values ((select nvl(max(custno) +1,1) from member_tbl_02), 
    '������', '010-1111-7777', '���ֵ� ���ֽ� ��������', '20151211', 'C', '60');

--�������� ���ٸ�
create SEQUENCE custseq 
START WITH 100007 ; --������ ���� ��ȣ

select custseq.nextval from dual;

select to_char(joindate, 'YYYY"��" MM"��" DD"��"' )
from member_tbl_02;
--ȸ����ȸ
select custno, custname, phone, address, to_char(joindate,'YYYY-MM-DD') joindate,
    decode(grade, 'A', 'VIP', 'B', '�Ϲ�', 'C', '����') grade, city
from member_tbl_02
order by custno;



--��������
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
--ȸ������ �ǸŹ�ȣ ���ϱ�
select nvl(max(saleno)+1,1) saleno
from money_tbl_02
where custno = 9000;

--ȸ������ ���� ��ȸ
select mn.custno, mb.custname, 
    decode(mb.grade,'A','VIP','B', '�Ϲ�', 'C', '����') grade,
    sum(mn.price) price
from money_tbl_02 mn join member_tbl_02 mb on(mn.custno = mb.custno)
group by mn.custno, mb.custname, mb.grade
order by price desc;

select * from member;









