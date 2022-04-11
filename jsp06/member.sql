--회원
--컬럼:아이디, 비밀번호(64byte), 우편번호, 도로명주소, 상세주소, 사진, 등록일자
DROP TABLE member;
CREATE TABLE member(
userid VARCHAR2(30),
passwd CHAR(64), --sha256암호화
salt CHAR(16), --암호화시 필요한 salt
zipcode CHAR(5),
addrload VARCHAR2(100),
addrdetail VARCHAR2(100),
filename VARCHAR2(100), --파일이름
regidate DATE DEFAULT sysdate,
PRIMARY KEY (userid)
);

--리스트 
SELECT * FROM member
WHERE addrload like '%' || '관악' ||'%';

--한건조회
SELECT * FROM member
WHERE userid = 'java';
--추가
INSERT INTO member(userid,passwd,salt, zipcode,addrload,addrdetail,filename)
VALUES('java', '1111','1111', '77777', '서울시 관악구 신림로', '르네상스6층', 'a.png' );

--수정
UPDATE member
SET passwd = '',
salt ='', 
zipcode ='',
addrload ='',
addrdetail ='',
filename =''
WHERE userid = 'java';

--삭제
DELETE FROM member
WHERE userid = 'java';

ROLLBACK;
COMMIT;



