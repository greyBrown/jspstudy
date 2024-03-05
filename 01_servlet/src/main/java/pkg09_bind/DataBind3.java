package pkg09_bind;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DataBind3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  /*
     * 데이터 저장 영역
     * 
     * 1. ServletContext      : 컨텍스트 종료(애플리케이션 실행 종료) 전까지 데이터를 유지한다. 
     * 2. HttpSession         : 세션 종료(웹 브라우저 종료) 전까지 데이터를 유지한다.
     * 3. HttpServletRequest  : 요청 종료(응답) 전까지 데이터를 유지한다.  (주로 이게많이 쓰임)
     */
    
    /*
     * 데이터 처리 메소드
     * 
     * 1. setAttribute(속성, 값)  : Object 타입의 값을 저장한다.
     * 2. getAttribute(속성)      : Object 타입의 값을 반환한다. (캐스팅이 필요할 수 있다.)
     * 3. removeAttribute(속성)   : 제거한다.
     */
    
	  // HttpServletRequest에 데이터 저장하기
    request.setAttribute("c", "일반데이터");      // 일반적인 정보들은 여기에 저장. 70%~80%은 여기에 저장됨
    
   // 데이터 확인 페이지로 이동하기 
    //+ sendRedirect는 요청 -> 새로운 주소 알려줌 -> 다시 요청 이런 구조이기 때문에 응답이 종료될때 데이터가 사라지는 SendRedirect 방법으로 데이터를 확인할 수 없음.
    request.getRequestDispatcher("/dataConfirm").forward(request, response);     // forward 는 서버내부주소경로만.
    // HttpServletReques 는 response.redirect로 이동할 수 없다. request.forward 로 이동해야함. 이거 중요!!
	
    //+request 가 중요함. 웹에서 대부분의 정보는 request 에서 저장되어 활용되기 때문에 웹 내부에서 핵심적임!
    //request.setAttribute("c", "일반데이터"); -> request.getRequestDispatcher("/dataConfirm").forward(request, response); 요건 통째로 외우는게 좋음. JSP Spring 까지 계속 응용되어 나옴...
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
