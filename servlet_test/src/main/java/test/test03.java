package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class test03 extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  request.setCharacterEncoding("UTF-8");
	  
	  String what = request.getParameter("select");
	  
	  String day = "day";
	  String time = "time"; 
	  

	  
	   
	  response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    if(what.equals(day)) {
      out.println("<script>");
      out.println("alert('요청결과는 " + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now())  + " 입니다.');");
      out.println("</script>");
      
    } else if(what.equals(time)) {
      out.println("<script>");
      out.println("alert('요청결과는 " + DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalTime.now())  + " 입니다.');");
      out.println("</script>");
    }
	  
	  

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
