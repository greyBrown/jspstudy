package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class test02response extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("UTF-8");
	  String a = request.getParameter("a");
    String b = request.getParameter("b");
    String c = request.getParameter("answer");
    
    Optional<String> opt1 = Optional.ofNullable(a);
    int aa = Integer.parseInt(opt1.orElse("0").isEmpty() ? "0" : opt1.orElse("0"));

    Optional<String> opt2 = Optional.ofNullable(b);
    int bb = Integer.parseInt(opt2.orElse("0").isEmpty() ? "0" : opt2.orElse("0"));
    
    Optional<String> opt3 = Optional.ofNullable(c);
    int cc = Integer.parseInt(opt3.orElse("0").isEmpty() ? "0" : opt3.orElse("0"));
    
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    if(cc == aa * bb) {
      out.println("정답입니다.");
    } else {
      out.println("틀렸습니다.");
    }
      
   

	  
	  
	  
	  
	  
	  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
