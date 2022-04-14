package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Board;
import dto.Boardfile;
import dto.Reply;
import service.BoardService;
import service.BoardfileService;
import service.ReplyService;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService boardService = new BoardService();
	private BoardfileService boardfileService = new BoardfileService(); 
	private ReplyService replyService = new ReplyService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String path = request.getContextPath();
		
		if (uri.contains("add")) {
			//게시물등록
			/* 파일저장경로:web.xml에서 읽기 */
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*20; // 20mbyte
			MultipartRequest multi = 
					new MultipartRequest(request,saveDirectory,size,"utf-8", 
							new DefaultFileRenamePolicy());
			
			//클라이언트 접속 ip정보
			String ip = request.getRemoteAddr();
			String userid = multi.getParameter("userid");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			//board dto만들기
			Board board = new Board();
			board.setUserid(userid);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(ip);
			System.out.println(board);
			
			//실제올라간 파일의 이름리스트
			List<String> filenames = new ArrayList<>();
			//파일의 이름의 모음
			Enumeration<String> files = multi.getFileNames();
			while(files.hasMoreElements()) { //다음자료가 있다면
				String name = files.nextElement(); //file엘리먼트의 name
				//System.out.println(name);
				String filename = multi.getFilesystemName(name); //실제저장된 파일이름
				//System.out.println(filename);
				if (filename != null) filenames.add(filename); //추가된 파일이름 리스트
			}
			System.out.println(filenames);
			
			//service 호출
			boardService.insert(board, filenames);
			
			//redirect이동
			response.sendRedirect(path + "/board/list?msg=" 
						+ URLEncoder.encode("추가완료", "utf-8"));
		} else if (uri.contains("list")) {
			//리스트
			String findkey = request.getParameter("findkey");
			String findvalue = request.getParameter("findvalue");
			String curPage_s = request.getParameter("curPage");
			//curPage가 null일때 처리
			int curPage = 1;
			if (curPage_s != null)  
				curPage = Integer.parseInt(curPage_s) ;
			System.out.println("현재페이지:" + curPage);
			
			//조회조건map만들기
			Map<String,Object> findmap = new HashMap<>();
			if (findkey==null || findkey.equals("")) findkey = "userid";
			if (findvalue ==null) findvalue = "";
			findmap.put("findkey", findkey);
			findmap.put("findvalue", findvalue);
			findmap.put("curPage", curPage);
			
			List<Board> blist = boardService.selectList(findmap);
			System.out.println(blist);
			//forward이동
			request.setAttribute("blist", blist);
			request.setAttribute("findmap", findmap); //페이징처리위해서 view에 넘기기
			
			request.getRequestDispatcher("/view/board/list.jsp")
				.forward(request, response);
		} else if (uri.contains("detail")) {
			//상세페이지
			int bnum = Integer.parseInt(request.getParameter("bnum")) ;
			System.out.println(bnum);
			//조회수+1
			boardService.update_readcnt(bnum);
			
			//1)게시물 조회
			Board board = boardService.selectOne(bnum);
			System.out.println(board);
			//2)게시물파일들 조회
			List<Boardfile> bflist = boardfileService.selectList(bnum);
			System.out.println(bflist);
			//3)댓글조회
			List<Reply> rlist = replyService.selectList(bnum);
			System.out.println(rlist);
			
			//forward방식
			request.setAttribute("board", board);
			request.setAttribute("bflist", bflist);
			request.setAttribute("rlist", rlist);
			
			request.getRequestDispatcher("/view/board/detail.jsp")
				.forward(request, response);
		}else if (uri.contains("remove")) {
			//삭제
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			System.out.println(bnum);
			boardService.delete(bnum);
			
			//redirect이동
			response.sendRedirect(path+"/board/list");
		}else if (uri.contains("modiform")) {
			//수정폼으로 이동
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			//board조회
			Board board = boardService.selectOne(bnum);
			//boardfile조회
			List<Boardfile> bflist = boardfileService.selectList(bnum);
			//forward이동 : modify.jsp로 이동
			request.setAttribute("board", board);
			request.setAttribute("bflist", bflist);
			request.getRequestDispatcher("/view/board/modify.jsp")
				.forward(request, response);
		}else if (uri.contains("modify")) {
			//수정
			/* 파일저장경로:web.xml에서 읽기 */
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*20; // 20mbyte
			MultipartRequest multi = 
					new MultipartRequest(request,saveDirectory,size,"utf-8", 
							new DefaultFileRenamePolicy());
			
			//클라이언트 접속 ip정보
			String ip = request.getRemoteAddr();
			int bnum = Integer.parseInt(multi.getParameter("bnum"));
			String userid = multi.getParameter("userid");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			//board dto만들기
			Board board = new Board();
			board.setBnum(bnum);
			board.setUserid(userid);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(ip);
			System.out.println(board);
			
			//실제올라간 파일의 이름리스트
			List<String> filenames = new ArrayList<>();
			//파일의 이름의 모음
			Enumeration<String> files = multi.getFileNames();
			while(files.hasMoreElements()) { //다음자료가 있다면
				String name = files.nextElement(); //file엘리먼트의 name
				String filename = multi.getFilesystemName(name); //실제저장된 파일이름
				if (filename != null) filenames.add(filename); //추가된 파일이름 리스트
			}
			System.out.println("추가할파일리스트:" + filenames);
			
			//삭제할 파일번호들
			String[] removefiles = multi.getParameterValues("removefile");
			System.out.println("삭제할파일리스트" + Arrays.toString(removefiles));
			
			//서비스호출
			boardService.update(board, filenames, removefiles);
			
			//detail로 이동
			response.sendRedirect(path+"/board/detail?bnum="+bnum);
			
		}else {
			System.out.println("잘못된 uri");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
