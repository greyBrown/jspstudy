package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MyInterface;
import model.MyInterfaceImpl;

import java.io.IOException;

import common.ActionForward;

public class MyController extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  /* 요청 UTF-8 인코딩*/
	  
	  request.setCharacterEncoding("UTF-8");
	  
	  /* 어떤 요청인지 확인 (URLMapping 확인)*/
	  
	  String requestURI = request.getRequestURI();      /* http://localhost:8080/mvc/getDate.do */
	  String contextPath = request.getContextPath();    /*/mvc */
    String urlMapping  = requestURI.substring(requestURI.indexOf(contextPath) + contextPath.length());
    
    /* MyInterface 타입의 MyInterfaceImpl 객체 생성*/
    MyInterface myInterface = new MyInterfaceImpl();
    
    
    /* 메소드 호출 결과(view  + 이동방식(forward/redirect))를 저장할 ActionForward 객체 */
    ActionForward actionForward = null;
    
    
    /* 요청에 따른 메소드 호출 */
    switch(urlMapping) {
    case "/getDate.do" :
      actionForward = myInterface.getDate(request);
      System.out.println(request.getAttribute("date"));
      break;
    case "/getTime.do" :
      actionForward = myInterface.getTime(request);
      System.out.println(request.getAttribute("time"));
      break;
    case "/getDateTime.do" :
      actionForward = myInterface.getDateTime(request);
      System.out.println(request.getAttribute("datetime"));
      break;
    }
 
	  /* 어디로 어떻게 이동할 것인지 결정 */
    if(actionForward != null) {
      if(actionForward.isRedirect()) {
        response.sendRedirect(actionForward.getView());
      } else {
        request.getRequestDispatcher(actionForward.getView()).forward(request, response);;
      }
    } 
    
    
     
    
    /* 개인적인 정리 : view controller model(원래는 service 가 따로 있지만 지금은 model 로~~ 곧 DB 취합하면 service 로 씁니당*/
    /*view(jsp에서 사용자 요청정보 받아옴 이거 어딨냐?! request 에 있다!) 
     * -> controller(서블릿에서 받아서 각각의 메소드(인터페이스임플)로 보내버림) 
     * -> model(myInterfaceImpl)여기서 메소드로 각각 처리한다. 그리고 request에 그 정보를 받고, 반환값으로 경로를 반환한다. 
     * 그 경로로 request가 답장을 가지고 간다. 그 경로가 컨트롤러의 경로! 다시 컨트롤러로 와서 메소드 실행으로 인해 생긴 request 값을 view로 뿜어준다.    */	
	  
    
    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
