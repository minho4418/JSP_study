package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Board;

class JunitTest {
	private BoardDAO bdao = new BoardDAO();
	
	@Test
	void testSqlSession() {
		MBConn.getSession();
	}

	@Test
	void testInsert() {
		Board board = new Board();
		board.setUserid("java");
		board.setSubject("제목1");
		board.setContent("내용1");
		board.setIp("192.168.0.100");
		int cnt = bdao.insert(board);
		System.out.println(cnt+"건 추가");
	}
	
	@Test
	void testUpdate() {
		Board board = new Board();
		board.setBnum(1);
		board.setUserid("ㅇㅇ");
		board.setSubject("제목수정1");
		board.setContent("내용수정1");
		board.setIp("192.168.0.100");
		int cnt = bdao.update(board);
		System.out.println(cnt+"건 추가");
	}
	@Test
	void testDelete() {
		int cnt = bdao.delete(1);
		System.out.println(cnt+"건 추가");
	}
	@Test
	void testSelectOne() {
		Board board = bdao.selectOne(2);
		System.out.println(board);
	}
	@Test
	void testSelectList() {
		Map<String, Object> findmap = new HashMap<String, Object>();
		findmap.put("findkey", "subject");
		findmap.put("findvalue", "제목");
		List<Board> board = bdao.selectList(findmap);
		System.out.println(board);
	}
}
