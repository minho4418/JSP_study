--ȸ��
--�÷�:���̵�, ��й�ȣ(64byte), �����ȣ, ���θ��ּ�, ���ּ�, ����, �������
DROP TABLE member;
CREATE TABLE member(
userid VARCHAR2(30),
passwd CHAR(64), --sha256��ȣȭ
salt CHAR(16), --��ȣȭ�� �ʿ��� salt
zipcode CHAR(5),
addrload VARCHAR2(100),
addrdetail VARCHAR2(100),
filename VARCHAR2(100), --�����̸�
regidate DATE DEFAULT sysdate,
PRIMARY KEY (userid)
);

--����Ʈ 
SELECT * FROM member
WHERE addrload like '%' || '����' ||'%';

--�Ѱ���ȸ
SELECT * FROM member
WHERE userid = 'java';
--�߰�
INSERT INTO member(userid,passwd,salt, zipcode,addrload,addrdetail,filename)
VALUES('java', '1111','1111', '77777', '����� ���Ǳ� �Ÿ���', '���׻�6��', 'a.png' );

--����
UPDATE member
SET passwd = '',
salt ='', 
zipcode ='',
addrload ='',
addrdetail ='',
filename =''
WHERE userid = 'java';

--����
DELETE FROM member
WHERE userid = 'java';

ROLLBACK;
COMMIT;



