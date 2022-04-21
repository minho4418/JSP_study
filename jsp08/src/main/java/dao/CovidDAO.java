package dao;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLNonTransientConnectionException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Covid;

public class CovidDAO {
	public int insert(List<Map<String, String>> covidList) {
		//반복문을 이용
		int cnt=0;//저장건수
		SqlSession session =  MBConn.getSession(); //세션맷기
		for(Map<String, String> covid:covidList) {
			try {
				cnt += session.insert("CovidMapper.insert", covid);
			} catch (Exception e) {
				//pk중복으로 인해서 에러 발생시 
				System.out.println(covid.get("seq") + " : 예외발생");
				//수정
				cnt += session.update("CovidMapper.update", covid);
				
				e.printStackTrace();
			}
		}
		//mybatisConfig의 transactionManager=>jdbc
		//여러건 insert후 commit을 한번만
		session.commit(); 
		session.close();
			
		return cnt;
	}
	
	//코로나 확진자 리스트
	public List<Covid> selectList(Map<String, String> map){
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("CovidMapper.selectList", map);
		}
	}
	
	
}
