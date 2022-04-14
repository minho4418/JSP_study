--게시물테이블(board)
--컬럼:bnum(게시물번호:pk: s_bnum(시퀀스명)), userid(작성자id), subject(제목), content(내용),readcnt(조회수),ip(작성자의ip),regidate(등록일자)
DROP TABLE board CASCADE CONSTRAINTS;
CREATE TABLE board(
bnum NUMBER(5),
userid VARCHAR2(30),
subject VARCHAR2(100),
content VARCHAR2(1000),
readcnt NUMBER(5) DEFAULT 0, --조회수
ip VARCHAR2(50),
regidate DATE DEFAULT sysdate,
PRIMARY KEY (bnum)
);
DROP SEQUENCE s_bnum;
CREATE SEQUENCE s_bnum;

--게시물파일테이블(boardfile)
--컬럼:bfnum(파일번호:pk:s_bfnum), bnum(게시물번호:fk), filename(파일이름)
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

--댓글테이블(reply)
--컬럼:rnum(댓글번호:pk: s_rnum), bnum(게시물번호:fk), content(댓글의내용), 
--       restep(댓글의순서), relevel(댓글의레벨),  regidate(등록일자)
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

--게시물---------------------------------------------------------------------------------
--게시물 추가
SELECT s_bnum.nextval FROM dual; --더미테이블을 이용해서 bnum을 구한다
INSERT INTO board(bnum,userid,subject,content,ip)
VALUES (s_bnum.nextval, 'java', '제목1', '내용1', '192.168.0.10');

--게시물 수정
UPDATE board
SET userid ='',
subject = '',
content ='',
ip = ''
WHERE bnum = 3;

--게시물 삭제
DELETE FROM board
WHERE bnum = 3;
--게시물 한건조회
SELECT  * FROM board
WHERE bnum = 4;

--게시물 조회수+1
UPDATE board
SET readcnt = readcnt +1
WHERE bnum = 6;

--게시물 리스트
SELECT * 
FROM(SELECT ROWNUM rnum, b.* 
           FROM(SELECT * FROM board
                     WHERE userid LIKE '%' || 'hong' || '%'
                      ORDER BY bnum DESC) b)
WHERE rnum BETWEEN 21 AND 30;                      

--전체게시물수
SELECT COUNT(*) totalCnt FROM board
WHERE userid LIKE '%' || 'java' || '%';

-----------------------------------------------------------------------------------
--게시물 파일
--게시물 파일 추가
INSERT INTO boardfile(bfnum, bnum, filename)
VALUES(s_bfnum.nextval, 4, 'a.png');
--게시물 파일 수정
UPDATE boardfile
SET filename = 'b.png'
WHERE bfnum = 2;

--게시물 파일 한건삭제
DELETE FROM boardfile
WHERE bfnum = 2;

--bnum의 파일들 삭제
DELETE FROM boardfile
WHERE bnum = 1;

--게시물 파일 한건조회
SELECT * FROM boardfile
WHERE bfnum = 2;
---------------------------------------------------------------------------------------
--댓글추가
INSERT INTO reply(rnum, bnum ,content, restep, relevel)
VALUES(s_rnum.nextval,169, '댓글1', 1,1);

--댓글수정
UPDATE reply
SET content='댓글수정'
WHERE rnum = 2;

--글순서를 재수정(restep+1)
UPDATE reply
SET restep= restep+1
WHERE bnum = 169
AND restep >= 1;

--댓글삭제
DELETE FROM reply
WHERE rnum = 2;

--댓글 한건 조회
SELECT * FROM reply
WHERE rnum = 3;

--댓글리스트
SELECT * FROM reply
WHERE bnum = 169
ORDER BY restep;

rollback;

---------------------------------------------------------------------------------------
--게시물데이터 추가 익명 프로시저
BEGIN
    FOR i IN 1..110 LOOP
        INSERT INTO board(bnum,userid,subject,content,ip)
        VALUES (s_bnum.nextval, 'hong', '제목2', '내용2', '192.168.0.10');
    END LOOP;
END;


COMMIT;
rollback;
