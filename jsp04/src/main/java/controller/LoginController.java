package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;

//확장자패턴 매핑
@WebServlet("*.log")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//service객체생성
	private LoginService loginService = new LoginService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String contextpath = request.getContextPath(); // /jsp04_session
		
		if (uri.contains("login")) {
			//로그인
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			System.out.println(userid);
			System.out.println(passwd);
			//서비스호출
			Map<String, Object> rmap = loginService.loginCheck(userid, passwd);
			//msg : 로그인성공, 회원 미존재, 비밀번호 불일치
			
			//로그인 성공시 세션을 생성
			int code = (int)rmap.get("code");
			if (code==0) {
				 //세션객체생성
				 HttpSession session = request.getSession(); //sessionid별로 ssession생성
				 //세션에 값 담기
				 session.setAttribute("userid", userid);
				 session.setAttribute("passwd", passwd);
				 //세션의 유효시간
				 session.setMaxInactiveInterval(60*60*3); //3시간
				 
				 //쿠키에 userid저장
				 //idsave값 읽기
				 String idsave = request.getParameter("idsave");
				 System.out.println("idsave:" + idsave);
				 Cookie useridCookie = new Cookie("userid", userid);
				 //useridCookie.setMaxAge(10); //10초
				 if (idsave==null) { //기억하지 않기
					 useridCookie.setMaxAge(0); //cookie삭제
				 }
				 response.addCookie(useridCookie);
				 
				 //메인으로 이동
				 response.sendRedirect(contextpath +"/view/main.jsp");
			}else {
				// 로그인 실패시 이동
				String msg = (String)rmap.get("msg");
				response.sendRedirect(contextpath + "/view/login.jsp?msg="+URLEncoder.encode(msg, "utf-8") );
			}
		}else if (uri.contains("logout")) {
			//로그아웃
			HttpSession session = request.getSession();
			session.invalidate(); //모든 섹션변수 삭제
			response.sendRedirect(contextpath + "/view/login.jsp?msg="+URLEncoder.encode("로그아웃완료", "utf-8") );
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
