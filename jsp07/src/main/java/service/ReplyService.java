package service;

import java.util.List;

import dao.ReplyDAO;
import dto.Reply;

public class ReplyService {
	private ReplyDAO replyDAO = new ReplyDAO();
	
	public void insert(Reply reply) {
		//댓글의순서(restep)
		reply.setRestep(reply.getRestep()+1); 
		//댓글의레벨(relevel)
		reply.setRelevel(reply.getRelevel()+1);
		
		//기존댓글의 순서를 수정 
		replyDAO.update_restep(reply);
		
		int cnt = replyDAO.insert(reply);
		System.out.println(cnt+"건 추가");
	}

	public List<Reply> selectList(int bnum) {
		return replyDAO.selectList(bnum);
	}
}
