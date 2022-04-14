package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.BoardDAO;
import dao.BoardfileDAO;
import dto.Board;
import dto.Boardfile;

public class BoardService {
	private BoardDAO boardDAO = new BoardDAO();
	private BoardfileDAO boardfileDAO = new BoardfileDAO();
	
	public void insert(Board board, List<String> filenames) {
		//게시물 저장
		int cnt = boardDAO.insert(board);
		System.out.println(cnt + "건 board 추가");
		System.out.println("service:" + board);
		//게시물파일저장
		//bnum는 board저장시 생성(boardMapper에서 selectKey로 세팅)
		cnt = 0; //초기화
		for(String filename :filenames) {
			Boardfile boardfile = new Boardfile();
			boardfile.setBnum(board.getBnum());
			boardfile.setFilename(filename);
			cnt += boardfileDAO.insert(boardfile);
		}
		System.out.println(cnt+"건 boardfile 추가");
	}

	public List<Board> selectList(Map<String,Object> findmap) {

		//1)페이징처리(sql문의 데이터조회하기 위해)
		int perPage = 10;	//한페이지의 게시물수:
		int curPage = (int) findmap.get("curPage");	//현재페이지
		int startnum = (curPage-1)*perPage +1; //시작번호
		int endnum = startnum + perPage -1;  //끝번호
		findmap.put("startnum", startnum);
		findmap.put("endnum", endnum);
		
		//2)페이징블럭 처리(list.jsp의 페이지번호를 구하기 위해)
		//2-1)전체게시물수
		int totCnt = boardDAO.select_totalcnt(findmap);
		int totPage = totCnt / perPage;
		if (totCnt % perPage > 0) totPage++; //나머지가 있으면 +1
		findmap.put("totPage", totPage);
		
		//2-1)시작페이지,끝페이지 구하기
		int perBlock = 10;
		int startPage = curPage - ((curPage-1)%perBlock);
		int endPage = startPage + perBlock -1;
		//endPage수정
		if (endPage>totPage) endPage = totPage;
		findmap.put("startPage", startPage); //블럭의 시작페이지
		findmap.put("endPage", endPage);  //블럭의 끝페이지

		System.out.println("findmap:" + findmap);
		return boardDAO.selectList(findmap);
	}

	public Board selectOne(int bnum) {
		return boardDAO.selectOne(bnum);
	}
	
	public void delete(int bnum) {
		//게시물파일 삭제(자식):반드시 자식부터 삭제
		int cnt = boardfileDAO.delete_bnum(bnum);
		System.out.println(cnt +"건 boardfile삭제");
		
		//게시물 삭제(부모)
		cnt = boardDAO.delete(bnum);
		System.out.println(cnt+"건 board 삭제");
		
	}

	public void update(Board board, List<String> filenames, String[] removefiles) {
		//1)게시물수정
		boardDAO.update(board);
		
		//2)추가할파일들 추가
		for(String filename:filenames) {
			Boardfile boardfile = new Boardfile();
			boardfile.setBnum(board.getBnum());
			boardfile.setFilename(filename);
			boardfileDAO.insert(boardfile);
		}
		
		//3)파일들 삭제
		if (removefiles == null) return ;
		for(String bfnum:removefiles) {
			boardfileDAO.delete(Integer.parseInt(bfnum));
		}
		
	}
	//조회수+1
	public void update_readcnt(int bnum) {
		boardDAO.update_readcnt(bnum);
	}
	
	
	
	
	
}
