package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CultureService;

@WebServlet("*.culture")
public class CultureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CultureService cultureService = new CultureService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//컨텍스트 패스
		String path = request.getContextPath();
		if (uri.contains("addr")) {
			//파싱후 view에 맵전달
			String name = request.getParameter("name");
			Map<String , Object> map = cultureService.cultureParsing(name);
			System.out.println(map);
			//위도,경도 구하기
			Map<String , Double> geomap = cultureService.geocoding((String)map.get("ADDR"));
			
			//forward방식 이동
			request.setAttribute("map", map);
			request.setAttribute("geomap", geomap);
			request.getRequestDispatcher("/view/cultureaddr.jsp").forward(request, response);
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
