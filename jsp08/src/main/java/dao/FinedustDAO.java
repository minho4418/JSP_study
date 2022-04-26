package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Finedust;

public class FinedustDAO {

	public int insert(List<Map<String, String>> clist) {
		//파싱한 맵의 리스트 저장
		int cnt=0;//저장건수
		SqlSession session =  MBConn.getSession(); //세션맷기
		for(Map<String, String> dust:clist) {
			try {
				cnt += session.insert("FinedustMapper.insert", dust);
			} catch (Exception e) {
				//pk중복으로 인해서 에러 발생시 
				System.out.println(dust.get("sn") + " : 예외발생");
				//수정
				cnt += session.update("FinedustMapper.update", dust);
				
				e.printStackTrace();
			}
		}
		//mybatisConfig의 transactionManager=>jdbc
		//여러건 insert후 commit을 한번만
		session.commit(); 
		session.close();
			
		return cnt;
	}
	
	public List<Finedust> selectList(){
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("FinedustMapper.selectList");
		}
	}
	
	public List<Finedust> selectList_name(String districtName) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("FinedustMapper.selectList_name", districtName);
		}
	}
}
