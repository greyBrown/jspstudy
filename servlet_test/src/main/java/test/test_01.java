package test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class test_01 extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	  request.setCharacterEncoding("UTF-8");
	  
    
    String id = request.getParameter("id");
    String pw = request.getParameter("pw");
    String check = request.getParameter("check");
    String name = request.getParameter("name");
    String birth = request.getParameter("birth");
    String month = request.getParameter("month");
    String day = request.getParameter("day");
    String gender = request.getParameter("gender");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    String phonedd = request.getParameter("phonedd");

    
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    if(id == null || id.isEmpty()) {
      out.println("아이디는 필수입니다.");
      return;
    }
    
    if(!check.equals(pw)) {
      out.println("비밀번호를 확인하세요.");
      return;
    }
      
    out.println("<ul>");
    out.println("<li>아이디: " + id );
    out.println("<li>비밀번호: " + pw );
    out.println("<li>이름: " + name );
    out.println("<li>생년월일: " + birth +"년" + month + "월" + day + "일");
    out.println("<li>성별: " + gender );
    out.println("<li>이메일: " + email );
    out.println("<li>휴대전화: " + phone + phonedd ); 
    out.println("</ul>");

	  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
