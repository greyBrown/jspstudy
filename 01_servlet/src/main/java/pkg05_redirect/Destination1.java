package pkg05_redirect;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

public class Destination1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  /*
     * redirect
     * 
     * 1. 이동할 때 요청과 응답을 모두 전달하지 않는다.
     * 2. 이동 경로를 작성할 때 contextPath 부터 작성해야 한다.
     * 3. 클라이언트는 redirect 경로를 확인할 수 있다.
     * 4. redirect 하는 경우
     *   1) 쿼리 insert
     *   2) 쿼리 update
     *   3) 쿼리 delete 
     */   
	 
	  request.setCharacterEncoding("UTF-8");
	  String luggage = request.getParameter("luggage"); // 이 다음에 이걸 어디에 갖다 붙이지...?

	  response.sendRedirect("/servlet/destination2?luggage=" + URLEncoder.encode(luggage, "UTF-8")); // 아 이걸 생각 못했네 이상한 인자값을 줄려고했네...미안하다..ㅎ...
	  // '김치'라는 한글 때문에 정상적으로 도착이 안됨...영어로 보내면 보내진다. 그래서 URLEncoder(import) 해줘야함!!
	  
	  // 이렇게 Redirect 할때 요청들어온 파라미터를 내가 다시 한번 풀어서 짐을 붙여 보내줘야한다는게 뽀인트!
	  // 간단히 말하면 redirect를 쓰는 경우는  요청이 2번인 경우가 일반적. insert(1번) selelct(2번) 하게 되기 때문. 그래서 redirect를 주로 쓰게 된다.
	  // 나중에 하면...알게 될 거예요...
	  
	  

	  
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
