package pkg04_forward;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class StopOver extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  /*
	   * forward
	   * 
	   * 1. 이동할 때 요청과 응답 모두 전달된다.
	   * 2. 이동 결로를 작성할 때 contextPath는 제외하고 작성해야 한다.(URLMapping 만 작성한다.)
	   * 3. 클라이언트는 forward 경로를 확인할 수 없다.
	   * 4. forward 하는 경우
	   *   1) 단순 이동
	   *   2) 쿼리 select
	   * 
	   * + forward 는 이미 서버 내부로 들어온 상태이기 때문에 URLMapping 으로만 이동하는 것
	   * + forward 를 하게 되면 최종목적이의 주소를 알 수 없음. (forward의 특징)
	   *  + 개발환경에서는 주로 단순 페이지이동에 forward 가 주로 쓰인다.
	   *  + select 했을 때도 forward 가 쓰임
	   */

	  request.getRequestDispatcher("/destination").forward(request, response);    // /servlet/destination 이렇게 서블릿까지 적으면 404 에러뜸.
	  
	  
	  
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
