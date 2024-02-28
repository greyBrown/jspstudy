package pkg03_response;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class MyResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. 응답 Content-Type 설정 + UTF-8 인코딩
	  //    1) HTML  : text/html
	  //    2) XML   : application/xml
	  //    3) JSON  : application/json
	  response.setContentType("text/html; charset=UTF-8"); //여기서의 항목 구분방법이 ;임
	  
	  // 2. 응답 출력 스트림 알아내기(문자 출력 스트림)
	  PrintWriter out = response.getWriter(); // doGet 메소드에서 예외회피가 되어있어서 try-catch문이 필요없음(서블릿은 그렇지만, 나중에 자바파일에서는 해줘야함 ㅎ;)
	  
	  // 3. 응답 만들기
	  out.println("<!DOCTYPE html>");
	  out.println("<html lang=\"ko\">");
	  out.println("<head>");
	  out.println("<meta charset=\"UTF-8\">");
	  out.println("<title>Insert title here</title>");
	  out.println("</head>");
	  out.println("<body>");
	  out.println("<h1>안녕하세요</h1>");
	  out.println("</body>");
	  out.println("</html>");
	  out.flush();
	  out.close();
	  
	  
	  // 작업이 노가다라 그렇지 만드는 자체는 어렵지 않아요. html을 이클립스에서 직접 손으로 친다...ㅎㅎ...
	  // 응답만들기...과거의 유물같은 거예요. 어떻게 발전되는지 아는게 좋으니까 한번 하고 지나감..
	  // 서블릿 자체가 JSP가 나오는 토대 같은 것임.
	  // 요청버튼을 누르면 여기서 HTML로 작성한 페이지가 뜨는것임! 간단하죠
	  
	 
	  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
