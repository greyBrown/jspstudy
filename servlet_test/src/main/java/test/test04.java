package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class test04 extends HttpServlet {
private static final long serialVersionUID = 1L;
       
 
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    
    String author = request.getParameter("author");
    String title = request.getParameter("title");
    String content = request.getParameter("content");
 

    
     
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter pout = response.getWriter();



    String filename = title+ ".txt";
    
    String uploadPath = request.getServletContext().getRealPath("upload");
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists()) {
      uploadDir.mkdir();
    }
    
    File file = new File(uploadDir, filename);
    
    BufferedWriter out = null;
    out = new BufferedWriter(new FileWriter(file));
    out.write(content);
    out.close();
   
    pout.println("<script>");
    pout.println("alert('" + DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now()) + author +" 공지사항 "  + filename +" 파일이 생성되었습니다.');");
    pout.println("</script>");

    pout.flush();
    pout.close();
  
}


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
doGet(request, response);
}

}


