package s01;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/J20220329_04")
public class J20220329_04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		StringBuffer url = request.getRequestURL();
		System.out.println(uri);
		System.out.println(url);
		
		String pname = request.getParameter("pname");
		int price = Integer.parseInt(request.getParameter("price"));
		int qty = Integer.parseInt(request.getParameter("qty"));
		System.out.println(pname);
		System.out.println(price);
		System.out.println(qty);
		
		int sum = price * qty;
		
		pname = URLEncoder.encode(pname, "utf-8");
		//forward이동
		/*
		 * request.setAttribute("sum", sum);
		 * request.getRequestDispatcher("/view/servlet/20220329_04_sales.jsp")
		 * .forward(request, response);
		 */
		//redirect방식
		//절대경로 : contextpath포함
		response.sendRedirect("/jsp01/view/servlet/20220329_04_sales.jsp?pname="+pname+"&sum="+sum);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
