package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Covid;
import service.CovidXMLService;

@WebServlet("*.covid")
public class CovidController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CovidXMLService covidXMLService = new CovidXMLService(); 
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//컨텍스트 패스
		String path = request.getContextPath();
		
		if (uri.contains("list")) {
			//코로나 현황 조회
			String startDt = request.getParameter("startDt");
			String endDt = request.getParameter("endDt");
			System.out.println(startDt);
			System.out.println(endDt);
			
			List<Covid> clist = covidXMLService.selectList(startDt, endDt);
			System.out.println("controller clist:" + clist);
			
			//forward방식
			request.setAttribute("clist", clist);
			request.getRequestDispatcher("/view/covidlist.jsp")
				.forward(request, response);
			
		}else if (uri.contains("dbsave")) {
			//파싱후 db저장
			String startDt = request.getParameter("startDt");
			String endDt = request.getParameter("endDt");
			int cnt = covidXMLService.covidParsing(startDt, endDt);
			
			//redirect 리스트로 이동
			//메시지전송 : cnt건 db저장 완료
			String msg = URLEncoder.encode(cnt +"건 db저장 완료", "utf-8") ;
			response.sendRedirect(path + "/view/covidlist.jsp?msg="+msg+"&startDt="+startDt+"&endDt="+endDt);
			
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
