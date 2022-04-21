--covid 테이블
DROP TABLE covid;
CREATE TABLE covid(
seq NUMBER(10), --순번
stateDt CHAR(8), --기준일
stateTime char(5), --기준시간
decideCnt NUMBER(10), --누적확진자수
deathCnt NUMBER(7), --누적사망자수
createDt VARCHAR2(30), --등록일시
updateDt VARCHAR2(30), --수정일시
PRIMARY KEY (seq)
);

--insert문
INSERT INTO covid(seq,stateDt,stateTime,decideCnt,deathCnt,createDt,updateDt)
VALUES(854,'20220419', '00:00',16471940,21354,'2022-04-19 09:05:13.63','2022-04-19 09:05:31.873');

--리스트
SELECT * FROM covid
order by SEQ;

--일일확진자수 구하기
SELECT  covid.*, 
    --LAG(decideCnt) OVER(ORDER BY stateDt DESC) lag_decideCnt, --전행의 누적확진자수
    --LEAD(decideCnt) OVER(ORDER BY stateDt DESC) lead_decideCnt, --다음행의 누적확진자수 
    LAG(decideCnt) OVER(ORDER BY stateDt DESC) - decideCnt dailyDecideCnt --전행의 누적확진자수 - 현재행 누적확진자수
FROM covid
WHERE stateDt BETWEEN '20220410' and '20220419';




rollback;