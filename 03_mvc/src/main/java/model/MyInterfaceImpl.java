package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import common.ActionForward;
import jakarta.servlet.http.HttpServletRequest;

public class MyInterfaceImpl implements MyInterface {
  
  // 서비스가 date 문자열 그 자체가 아니라 뷰(JSP)에게 이 DATE를 보여주겠다는 것(경로)를 반환한다. DATE는 자체는 request에 저장된다. 
  // 그리고 forward(with Request)로 슝 보낸다.

  @Override
  public ActionForward getDate(HttpServletRequest request) {
    
    request.setAttribute("date", DateTimeFormatter.ofPattern("yyyy. MM. dd.").format(LocalDate.now()));
    
    return new ActionForward("/view/date.jsp", false);       //false니까 forward 라는 뜻. 변수만들 때 그렇게 지정했으니까!
  }

  @Override
  public ActionForward getTime(HttpServletRequest request) {
    
    request.setAttribute("time", DateTimeFormatter.ofPattern("HH:mm:ss.SSS").format(LocalTime.now()));
    
    return new ActionForward("/view/time.jsp", false);
  }

  @Override
  public ActionForward getDateTime(HttpServletRequest request) {
    
    request.setAttribute("datetime", DateTimeFormatter.ofPattern("yyyy. MM. dd. HH:mm:ss.SSS").format(LocalDateTime.now()));
    
    return new ActionForward("/view/datetime.jsp", false);
  }

}
