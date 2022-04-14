package dao;

import java.util.List;

import org.junit.jupiter.api.Test;

import dto.Reply;

class JunitTestReply {

	ReplyDAO replyDAO = new ReplyDAO();
	@Test
	void testInsert() {
		Reply reply = new Reply();
		reply.setBnum(169);
		reply.setContent("댓글2");
		reply.setRestep(1);
		reply.setRelevel(1);
		
		replyDAO.insert(reply);
	}
	
	@Test
	void testUpdate() {
		Reply reply = new Reply();
		reply.setRnum(4);
		reply.setContent("댓글수정");
		
		int cnt = replyDAO.update(reply);
		System.out.println(cnt+"건 수정");
	}

	@Test
	void testDelete() {
		int cnt = replyDAO.delete(4);
		System.out.println(cnt+"건 삭제");
	}
	
	@Test
	void testSelectOne() {
		Reply reply = replyDAO.selectOne(5);
		System.out.println(reply);
	}
	
	@Test
	void testSelectList() {
		List<Reply> rlist = replyDAO.selectList(169);
		System.out.println(rlist);
	}
	
}
