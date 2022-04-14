--�Խù����̺�(board)
--�÷�:bnum(�Խù���ȣ:pk: s_bnum(��������)), userid(�ۼ���id), subject(����), content(����),readcnt(��ȸ��),ip(�ۼ�����ip),regidate(�������)
DROP TABLE board CASCADE CONSTRAINTS;
CREATE TABLE board(
bnum NUMBER(5),
userid VARCHAR2(30),
subject VARCHAR2(100),
content VARCHAR2(1000),
readcnt NUMBER(5) DEFAULT 0, --��ȸ��
ip VARCHAR2(50),
regidate DATE DEFAULT sysdate,
PRIMARY KEY (bnum)
);
DROP SEQUENCE s_bnum;
CREATE SEQUENCE s_bnum;

--�Խù��������̺�(boardfile)
--�÷�:bfnum(���Ϲ�ȣ:pk:s_bfnum), bnum(�Խù���ȣ:fk), filename(�����̸�)
DROP TABLE boardfile;
CREATE TABLE boardfile(
bfnum NUMBER(5),
bnum NUMBER(5),
filename VARCHAR2(50),
PRIMARY KEY(bfnum),
FOREIGN KEY (bnum) REFERENCES board(bnum)
);
DROP SEQUENCE s_bfnum;
CREATE SEQUENCE s_bfnum;

--������̺�(reply)
--�÷�:rnum(��۹�ȣ:pk: s_rnum), bnum(�Խù���ȣ:fk), content(����ǳ���), 
--       restep(����Ǽ���), relevel(����Ƿ���),  regidate(�������)
DROP TABLE reply;
CREATE TABLE reply(
rnum NUMBER(5),
bnum NUMBER(5),
content VARCHAR2(1000),
restep NUMBER(3),
relevel NUMBER(3),
regidate DATE DEFAULT sysdate,
PRIMARY KEY(rnum),
FOREIGN KEY(bnum) REFERENCES board(bnum)
);
DROP SEQUENCE s_rnum; 
CREATE SEQUENCE s_rnum; 

--�Խù�---------------------------------------------------------------------------------
--�Խù� �߰�
SELECT s_bnum.nextval FROM dual; --�������̺��� �̿��ؼ� bnum�� ���Ѵ�
INSERT INTO board(bnum,userid,subject,content,ip)
VALUES (s_bnum.nextval, 'java', '����1', '����1', '192.168.0.10');

--�Խù� ����
UPDATE board
SET userid ='',
subject = '',
content ='',
ip = ''
WHERE bnum = 3;

--�Խù� ����
DELETE FROM board
WHERE bnum = 3;
--�Խù� �Ѱ���ȸ
SELECT  * FROM board
WHERE bnum = 4;

--�Խù� ��ȸ��+1
UPDATE board
SET readcnt = readcnt +1
WHERE bnum = 6;

--�Խù� ����Ʈ
SELECT * 
FROM(SELECT ROWNUM rnum, b.* 
           FROM(SELECT * FROM board
                     WHERE userid LIKE '%' || 'hong' || '%'
                      ORDER BY bnum DESC) b)
WHERE rnum BETWEEN 21 AND 30;                      

--��ü�Խù���
SELECT COUNT(*) totalCnt FROM board
WHERE userid LIKE '%' || 'java' || '%';

-----------------------------------------------------------------------------------
--�Խù� ����
--�Խù� ���� �߰�
INSERT INTO boardfile(bfnum, bnum, filename)
VALUES(s_bfnum.nextval, 4, 'a.png');
--�Խù� ���� ����
UPDATE boardfile
SET filename = 'b.png'
WHERE bfnum = 2;

--�Խù� ���� �Ѱǻ���
DELETE FROM boardfile
WHERE bfnum = 2;

--bnum�� ���ϵ� ����
DELETE FROM boardfile
WHERE bnum = 1;

--�Խù� ���� �Ѱ���ȸ
SELECT * FROM boardfile
WHERE bfnum = 2;
---------------------------------------------------------------------------------------
--����߰�
INSERT INTO reply(rnum, bnum ,content, restep, relevel)
VALUES(s_rnum.nextval,169, '���1', 1,1);

--��ۼ���
UPDATE reply
SET content='��ۼ���'
WHERE rnum = 2;

--�ۼ����� �����(restep+1)
UPDATE reply
SET restep= restep+1
WHERE bnum = 169
AND restep >= 1;

--��ۻ���
DELETE FROM reply
WHERE rnum = 2;

--��� �Ѱ� ��ȸ
SELECT * FROM reply
WHERE rnum = 3;

--��۸���Ʈ
SELECT * FROM reply
WHERE bnum = 169
ORDER BY restep;

rollback;

---------------------------------------------------------------------------------------
--�Խù������� �߰� �͸� ���ν���
BEGIN
    FOR i IN 1..110 LOOP
        INSERT INTO board(bnum,userid,subject,content,ip)
        VALUES (s_bnum.nextval, 'hong', '����2', '����2', '192.168.0.10');
    END LOOP;
END;


COMMIT;
rollback;
