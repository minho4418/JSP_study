package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import dao.BoardDAO;
import dto.Board;

//확장자 패턴
@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardDAO bdao = new BoardDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		if (uri.contains("add")) {
			//게시물 등록
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			//board객체 생성
			Board board = new Board(writer,subject,content);
			System.out.println(board);
			
			//dao의 insert 
			int cnt = bdao.insert(board);
			System.out.println(cnt);
			
			//게시물등록으로 이동
			String msg = URLEncoder.encode("추가 완료", "utf-8");
			response.sendRedirect("/jsp02_board/board/add.jsp?msg="+msg);
		}else if (uri.contains("list")) {
			//리스트
			String findkey = request.getParameter("findkey");
			String findvalue = request.getParameter("findvalue");
			if (findkey==null) findkey="writer";
			if (findvalue == null) findvalue ="";
			List<Board> blist = bdao.selectList(findkey, findvalue);
			System.out.println(blist);
			//forward 이동
			request.setAttribute("blist", blist);
			request.getRequestDispatcher("/board/list.jsp")
				.forward(request, response);
		}else if (uri.contains("modiform")) {
			//수정폼으로 이동
			int seq = Integer.parseInt(request.getParameter("seq"));
			//dao selectOne호출
			Board board = bdao.selectOne(seq);
			//modify.jsp로 이동(board)
			request.setAttribute("board", board);
			request.getRequestDispatcher("/board/modify.jsp")
				.forward(request, response);
		}else if (uri.contains("modify")) {
			//수정
			int seq = Integer.parseInt(request.getParameter("seq"));
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
//			Board board = new Board(seq,writer,subject,content);
			Board board = new Board();
			board.setSeq(seq);
			board.setWriter(writer);
			board.setSubject(subject);
			board.setContent(content);
			System.out.println(board);
			
			int cnt = bdao.update(board);
			System.out.println(cnt);
			
			/*
			 * request.setAttribute("msg", "수정 완료");
			 * request.getRequestDispatcher("/board/list.jsp") .forward(request, response);
			 */
			
			String msg = URLEncoder.encode("수정 완료", "utf-8");
			response.sendRedirect("/jsp02_board/board/list.jsp?msg="+msg);
		}else if (uri.contains("remove")) {
			//삭제
			//checkbox(여러개)를 파라메터로 읽기
			String[] removes = request.getParameterValues("removes");
			System.out.println(Arrays.toString(removes));
			int cnt=0;
			for(String seq:removes) {
				cnt = bdao.delete(Integer.parseInt(seq));
			}
			System.out.println(cnt);
			
			//redirect 조회로 이동(list.board)
			response.sendRedirect("/jsp02_board/list.board?msg=" + URLEncoder.encode("삭제완료","utf-8"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
