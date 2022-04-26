--����iso
drop table iso;
CREATE TABLE iso(
code VARCHAR2(2),
name VARCHAR2(50),
PRIMARY KEY (code)
);

desc iso;

select * from iso
order by name;


--���������������̺�
CREATE TABLE country(
sfty_notice_id   CHAR(16) PRIMARY KEY, --�������� ID
continent_cd CHAR(2),  --����ڵ�
continent_eng_nm VARCHAR2(50), --���������
continent_nm VARCHAR2(100),  --�ѱ۴����
country_iso_alp2 CHAR(2),  --ISO 2�ڸ��ڵ�
country_eng_nm VARCHAR2(50), --����������
country_nm VARCHAR2(100), --�ѱ۱�����
title VARCHAR2(300), --����
file_cnt VARCHAR2(3), --���ϰ���
file_download_url VARCHAR2(200), --���ϴٿ�ε� URL
file_path VARCHAR2(100), --���ϰ��
html_origin_cn CLOB, --HTML���� ����
txt_origin_cn CLOB, --txt��������
wrt_dt CHAR(10) --�ۼ���
);

--����Ʈ��ȸ
SELECT * FROM country;
--�Ѱ���ȸ
SELECT * FROM country
WHERE sfty_notice_id = 'ATC0000000008456';


--�߰�
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

--������

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
resultCode CHAR(2), --����ڵ�
resultMsg VARCHAR2(50),  --����޽���
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







