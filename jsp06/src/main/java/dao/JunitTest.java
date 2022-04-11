package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Member;


public class JunitTest {
	MemberDAO mdao = new MemberDAO();
	
	@Test
	void testInsert() {
		Member member = new Member();
		member.setUserid("java");
		member.setPasswd("1111");
		member.setSalt("1111");
		member.setZipcode("55555");
		member.setAddrload("서울시 관악구");
		member.setAddrdetail("르네상스 6층");
		member.setFilename("a.png");
		
		int cnt = mdao.insert(member);
		System.out.println(cnt +"건 추가");
	}

	@Test
	void testUpdate() {
		Member member = new Member();
		member.setUserid("java");
		member.setPasswd("2222");
		member.setSalt("2222");
		member.setZipcode("55555");
		member.setAddrload("서울시 관악구");
		member.setAddrdetail("르네상스 6층");
		member.setFilename("b.png");
		
		int cnt = mdao.update(member);
		System.out.println(cnt +"건 수정");
	}

	@Test
	void testDelete() {
		int cnt = mdao.delete("java");
		System.out.println(cnt +"건 삭제");
		
	}

	@Test
	void testSelectList() {
		Map<String,String> findmap = new HashMap<>();
		findmap.put("findkey", "addrload");
		findmap.put("findvalue", "관악");
		
		List<Member> blist = mdao.selectList(findmap);
		System.out.println(blist);
	}

	@Test
	void testSelectOne() {
		Member member = mdao.selectOne("java");
		System.out.println(member);
	}

}
