--covid ���̺�
DROP TABLE covid;
CREATE TABLE covid(
seq NUMBER(10), --����
stateDt CHAR(8), --������
stateTime char(5), --���ؽð�
decideCnt NUMBER(10), --����Ȯ���ڼ�
deathCnt NUMBER(7), --��������ڼ�
createDt VARCHAR2(30), --����Ͻ�
updateDt VARCHAR2(30), --�����Ͻ�
PRIMARY KEY (seq)
);

--insert��
INSERT INTO covid(seq,stateDt,stateTime,decideCnt,deathCnt,createDt,updateDt)
VALUES(854,'20220419', '00:00',16471940,21354,'2022-04-19 09:05:13.63','2022-04-19 09:05:31.873');

--����Ʈ
SELECT * FROM covid
order by SEQ;

--����Ȯ���ڼ� ���ϱ�
SELECT  covid.*, 
    --LAG(decideCnt) OVER(ORDER BY stateDt DESC) lag_decideCnt, --������ ����Ȯ���ڼ�
    --LEAD(decideCnt) OVER(ORDER BY stateDt DESC) lead_decideCnt, --�������� ����Ȯ���ڼ� 
    LAG(decideCnt) OVER(ORDER BY stateDt DESC) - decideCnt dailyDecideCnt --������ ����Ȯ���ڼ� - ������ ����Ȯ���ڼ�
FROM covid
WHERE stateDt BETWEEN '20220410' and '20220419';




rollback;