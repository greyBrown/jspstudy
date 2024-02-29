package pkg02_request;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyRequest extends HttpServlet {
  
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	  // 1. 요청 UTF-8 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
   // 2. 요청 파라미터
	 //   1) 모든 파라미터는 String 타입이다.
   //   2) 파라미터가 없으면 null 이다.
	 //   3) 파라미터의 값이 없으면 ""(빈 문자열) 이다. 
	  
	  /* if 문을 이용한 null&빈문자열 처리 */ // -> 500오류가 뜨지 않고 무사히 데이터가 넘어오는 걸 확인
	  String strNumber = request.getParameter("number"); //getParameter 매우매우중요!! 이 친구가 요청을 받아낸다.
	  int number = 0; 
	  if(strNumber != null && !strNumber.isEmpty())    //isEmpty로 빈문자열처리
	     number = Integer.parseInt(strNumber);
	  System.out.println(number);
	  
	  /* Optional<T> 클래스를 이용한 null&빈문자열 처리 */ //이걸 많이 활용하는게 좋음. 이걸 사용하는 클래스들이 점점 나오기 때문
	  String strNumber2 = request.getParameter("number2");
    Optional<String> opt = Optional.ofNullable(strNumber2);
    double number2 = Double.parseDouble(opt.orElse("0").isEmpty() ? "0" : opt.orElse("0"));
    System.out.println(number2);
	  // -> 만약 strNumber2가 null 이라면 0을 꺼내라. + 삼향연산자로 빈문자열처리
	 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  //오늘은 toGET은 지우고 처음부터 POST 작업 해보겠습니다.
		
	  //1. 요청 UTF-8 인코딩
	  request.setCharacterEncoding("UTF-8");
	  
	  //2. 요청 파라미터
	  //  1) name 속성이 파라미터이다.
	  //  2) 동일한 name 속성을 가진 입력 요소들은 다음과 같이 처리한다.
	  //   (1) type="radio" : 변수 처리한다. 
	  //   (2) 이외의 경우  : 배열 처리한다.
	  String name = request.getParameter("name"); //input type="text" -> 체크 안할시 빈문자열
	  String email = request.getParameter("email");
	  String gender = request.getParameter("gender"); //input type="checkbox", "radio" -> 체크 안할시 null
	  String[] hobbies = request.getParameterValues("hobbies");
	  String[] mobile = request.getParameterValues("mobile");
	  // 입력이 안됐는데 보내는걸 프론트쪽에서 잘 막아놔야함
	  
	  System.out.println(name);
	  System.out.println(email);
	  System.out.println(gender); //on 이라고 출력됨. 선택한 경우 on.(원래는 프론트쪽에서 다르게 만들어야함 -> 이때 필요했던게 value)
	  System.out.println(Arrays.toString(hobbies));
	  System.out.println(Arrays.toString(mobile)); //select 에서 넘어오는건 option 의 내부 텍스트임.
	  
	  //쉽게 이해하자면 name은 파라미터명이고 value는 파라미터값 정도인걸까...
	  
	  
	  
	  
	}

}
