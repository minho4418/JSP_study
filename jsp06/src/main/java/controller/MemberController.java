package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Member;
import service.MemberService;

@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		String path = request.getContextPath();
		
		if (uri.contains("join")) {
			//회원가입
			//saveDirectory : 파일저장경로(서버의 경로)
			//String saveDirectory = "D:/ksy/savedir";
			//프로젝트의 web.xml의 context-param의 값을 읽기
			String saveDirectory = getServletContext().getInitParameter("savedir");
			
			//size: 업로드파일 사이즈 제한
			int size = 1024 * 1024 * 10; //10mbyte
			//DefaultFileRenamePolicy() : 같은이름의 파일이 있을때 파일이름변경
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", 
					new DefaultFileRenamePolicy());
			
			//MultipartRequest객체의 메소드를 이용해서 데이터 얻기
			String userid = multi.getParameter("userid");
			String passwd = multi.getParameter("passwd");
			String zipcode = multi.getParameter("zipcode");
			String addrload = multi.getParameter("addrload");
			String addrdetail = multi.getParameter("addrdetail");
			//실제저장된 파일이름
			String filename = multi.getFilesystemName("photo");
			//Member객체생성
			Member member = new Member();
			member.setUserid(userid);
			member.setPasswd(passwd);
			member.setZipcode(zipcode);
			member.setAddrload(addrload);
			member.setAddrdetail(addrdetail);
			member.setFilename(filename);
			System.out.println(member);
			
			//서비스 호출
			int cnt = memberService.insert(member);
			System.out.println(cnt+"건 추가");
			
			//redirect이동
			response.sendRedirect(path + "/member/join.jsp?msg="+ URLEncoder.encode(cnt+"건 추가", "utf-8"));
			
		}else if (uri.contains("login")) {
				//로그인
				String userid = request.getParameter("userid");
				String passwd = request.getParameter("passwd");
				System.out.println(userid);
				System.out.println(passwd);
				//서비스호출
				Map<String, Object> rmap = memberService.loginCheck(userid, passwd);
				//msg : 로그인성공, 회원 미존재, 비밀번호 불일치
				
				//로그인 성공시 세션을 생성
				int code = (int)rmap.get("code");
				if (code==0) { //성공
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
					 response.sendRedirect(path +"/main.jsp");
				}else {
					// 로그인 실패시 이동
					String msg = (String)rmap.get("msg");
					response.sendRedirect(path + "/login.jsp?msg="+URLEncoder.encode(msg, "utf-8") );
				}
			}else if (uri.contains("logout")) {
				//로그아웃
				HttpSession session = request.getSession();
				session.invalidate(); //모든 섹션변수 삭제
				response.sendRedirect(path + "/login.jsp?msg="+URLEncoder.encode("로그아웃완료", "utf-8") );
			}else if (uri.contains("info")) {
				//회원정보 조회
				//파라메터에서 userid정보 얻기
				String userid = request.getParameter("userid");
				System.out.println(userid);
				Member member = memberService.selectOne(userid);
				System.out.println(member);
				//forward이동
				request.setAttribute("member", member); //requestScope에 넣겠다
				request.getRequestDispatcher("/member/info.jsp").forward(request, response);
			}else if (uri.contains("list")) {
				//조회정보
				String findkey = request.getParameter("findkey");
				String findvalue = request.getParameter("findvalue");
				//검색조건이 없을때(null일경우) 처리
				if (findkey==null) findkey="userid";
				if (findvalue==null) findvalue="";
				
				Map<String,String> findmap = new HashMap<>();
				findmap.put("findkey", findkey);
				findmap.put("findvalue", findvalue);
				
				List<Member> mlist = memberService.selectList(findmap);
				System.out.println(mlist);
				
				//forward이동
				request.setAttribute("mlist", mlist); //requestScope에 넣겠다
				request.getRequestDispatcher("/member/list.jsp").forward(request, response);
			}else if (uri.contains("modiform")) {
				//수정폼으로 이동
				String userid = request.getParameter("userid");
				System.out.println(userid);
				Member member =memberService.selectOne(userid);
				
				request.setAttribute("member",member);
				request.getRequestDispatcher("/member/modify.jsp")
					.forward(request, response);
				
			}else if (uri.contains("modify")) {
				//수정
				//프로젝트의 web.xml의 context-param의 값을 읽기
				String saveDirectory = getServletContext().getInitParameter("savedir");
				//size: 업로드파일 사이즈 제한
				int size = 1024 * 1024 * 10; //10mbyte
				//DefaultFileRenamePolicy() : 같은이름의 파일이 있을때 파일이름변경
				MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", 
						new DefaultFileRenamePolicy());
				
				//MultipartRequest객체의 메소드를 이용해서 데이터 얻기
				String userid = multi.getParameter("userid");
				String passwd = multi.getParameter("passwd");
				String newpasswd = multi.getParameter("newpasswd"); //변경패스워드
				String zipcode = multi.getParameter("zipcode");
				String addrload = multi.getParameter("addrload");
				String addrdetail = multi.getParameter("addrdetail");
				//실제저장된 파일이름
				String sysfilename =  multi.getFilesystemName("photo");
				System.out.println("실제올라간파일이름:" + sysfilename);
				String filename=null;
				if (sysfilename==null) //파일을 변경하지 않았을경우
					filename = multi.getParameter("filename");
				else
					filename = sysfilename;
				//Member객체생성
				Member member = new Member();
				member.setUserid(userid);
				member.setPasswd(passwd);
				member.setNewpasswd(newpasswd);
				member.setZipcode(zipcode);
				member.setAddrload(addrload);
				member.setAddrdetail(addrdetail);
				member.setFilename(filename);

				System.out.println(member);
				Map<String, Object> rmap = memberService.update(member);
				System.out.println(rmap);
				if ((int)rmap.get("code") == 0 ) {
					//redirect 개인정보 이동
					response.sendRedirect(path + "/info.member?userid="+userid);					
				}else { //실패일경우
					//redirect 수정폼으로 이동
//					response.sendRedirect(path + "/modiform.member?userid="+userid+"&msg="
//							+ URLEncoder.encode((String)rmap.get("msg"), "utf-8") );
					
					//forward방식()
					request.setAttribute("member", member);
					request.setAttribute("msg", rmap.get("msg"));
					request.getRequestDispatcher("/member/modify.jsp")
						.forward(request, response);
				}
					
			}else if (uri.contains("remove")) {
				//삭제(회원탈퇴)
				String userid = request.getParameter("userid");
				int cnt = memberService.delete(userid);
				
				//섹션지우기
				HttpSession session = request.getSession();
				session.invalidate();
				
				//redirect방식 이동
				response.sendRedirect(path + "/main.jsp?msg="+URLEncoder.encode("회원탈퇴 완료","utf-8"));									
				
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
