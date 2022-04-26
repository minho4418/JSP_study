--국가iso
drop table iso;
CREATE TABLE iso(
code VARCHAR2(2),
name VARCHAR2(50),
PRIMARY KEY (code)
);

desc iso;

select * from iso
order by name;


--국가안전정보테이블
CREATE TABLE country(
sfty_notice_id   CHAR(16) PRIMARY KEY, --안전공지 ID
continent_cd CHAR(2),  --대륙코드
continent_eng_nm VARCHAR2(50), --영문대륙명
continent_nm VARCHAR2(100),  --한글대륙명
country_iso_alp2 CHAR(2),  --ISO 2자리코드
country_eng_nm VARCHAR2(50), --영문국가명
country_nm VARCHAR2(100), --한글국가명
title VARCHAR2(300), --제목
file_cnt VARCHAR2(3), --파일갯수
file_download_url VARCHAR2(200), --파일다운로드 URL
file_path VARCHAR2(100), --파일경로
html_origin_cn CLOB, --HTML원본 내용
txt_origin_cn CLOB, --txt원본내용
wrt_dt CHAR(10) --작성일
);

--리스트조회
SELECT * FROM country;
--한건조회
SELECT * FROM country
WHERE sfty_notice_id = 'ATC0000000008456';


--추가
INSERT INTO country(
sfty_notice_id ,
continent_cd ,
continent_eng_nm ,
continent_nm ,
country_iso_alp2 ,
country_eng_nm ,
country_nm ,
title,
file_cnt ,
file_download_url,
file_path,
html_origin_cn,
txt_origin_cn,
wrt_dt)
VALUES(
'1',
'',
'',
'',
'',
'',
'',
'',
'',
'',
'',
'',
'',
''
);


INSERT INTO country(
sfty_notice_id ,
continent_cd ,
continent_eng_nm ,
continent_nm ,
country_iso_alp2 ,
country_eng_nm ,
country_nm ,
title,
file_cnt ,
file_download_url,
file_path,
html_origin_cn,
txt_origin_cn,
wrt_dt)
VALUES(
#{sfty_notice_id} ,
#{continent_cd} ,
#{continent_eng_nm} ,
#{continent_nm} ,
#{country_iso_alp2} ,
#{country_eng_nm} ,
#{country_nm} ,
#{title},
#{file_cnt} ,
#{file_download_url},
#{file_path},
#{html_origin_cn},
#{txt_origin_cn},
#{wrt_dt}
);

--수정문

UPDATE country 
SET = '',
continent_cd = '',
continent_eng_nm = '',
continent_nm = '',
country_iso_alp2 = '',
country_eng_nm = '',
country_nm = '',
title= '',
file_cnt= '',
file_download_url= '',
file_path= '',
html_origin_cn= '',
txt_origin_cn= '',
wrt_dt = ''
WHERE sfty_notice_id = '';


CREATE TABLE finedust(
resultCode CHAR(2), --결과코드
resultMsg VARCHAR2(50),  --결과메시지
numOfRows VARCHAR2(4), --
pageNo VARCHAR2(4),  --
totalCount CHAR(4),  
sn VARCHAR2(5) primary key, 
dataDate VARCHAR2(10), --
districtName VARCHAR2(100), --
moveName VARCHAR2(100), --
itemCode VARCHAR2(10), --
issueGbn VARCHAR2(10), --
issueDate VARCHAR2(10), 
issueTime VARCHAR2(5), 
issueVal VARCHAR2(10),
clearDate VARCHAR2(10),
clearTime VARCHAR2(5),
clearVal VARCHAR2(10)
);

select * from finedust;

SELECT * FROM finedust
		ORDER BY districtName;
        
commit;
rollback;







