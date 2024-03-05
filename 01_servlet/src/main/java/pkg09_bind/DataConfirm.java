package pkg09_bind;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DataConfirm
 */
public class DataConfirm extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  // ServletContext 데이터 확인
	  System.out.println(request.getServletContext().getAttribute("a"));
	  
	  // HttpSession 데이터 확인
	  System.out.println(request.getSession().getAttribute("b"));
	  
	  // HttpServletRequest 확인
	  System.out.println(request.getAttribute("c"));
	  
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
