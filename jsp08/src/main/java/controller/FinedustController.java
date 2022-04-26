package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Country;
import dto.Finedust;
import service.FinedustService;

@WebServlet("*.finedust")
public class FinedustController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FinedustService finedustService = new FinedustService();
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		//컨텍스트 패스
		String path = request.getContextPath();
		System.out.println(path);
		
		if (uri.contains("list")) {
			 
			List<Finedust> dustlist = finedustService.selectList();
			System.out.println(dustlist);
			
			request.setAttribute("dustlist", dustlist);
			request.getRequestDispatcher("/view/finedustlist.jsp")
				.forward(request, response);
		}else if (uri.contains("dbsave")) {
			//파싱후 db저장하기 위해서
			String districtName = request.getParameter("districtName");
			System.out.println(districtName);
			//파싱후 db저장
			int cnt = finedustService.dustParsing();

			//redirect방식:url변경
			String msg = URLEncoder.encode(cnt+"건 저장", "utf-8");
			response.sendRedirect(path+"/list.finedust?msg="+msg);
		}else if (uri.contains("detail")) {
			//상세조회로 이동
			 String districtName = request.getParameter("districtName");
			 System.out.println(districtName);
			 //한건조회
			 List<Finedust> dust= finedustService.selectList_name(districtName);
			 System.out.println(dust);
			//forward방식:countrydetail.jsp로 이동
			request.setAttribute("dust", dust);
			request.getRequestDispatcher("/view/finedustdetail.jsp")
				.forward(request, response);
	}
}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
