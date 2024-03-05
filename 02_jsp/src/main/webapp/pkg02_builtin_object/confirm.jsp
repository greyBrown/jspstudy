<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  
  <%
   // 업로드 된 파일 목록 가져오기
   String uploadPath = application.getRealPath("upload");
   File uploadDir = new File(uploadPath);
   File[] uploadFiles = uploadDir.listFiles();  //이렇게 하면 업로드 된 파일 목록들을 전부 샥 긁어온다.
   
   // 가장 최근에 업로드한 파일명 출력하기
   out.println("<div>최근 업로드 파일명 : " + session.getAttribute("uploadName")+ "</div>"); //세션을 이용. save 와 confirm이 속성으로 데이터를 주고받지 않아도 가져올 수 있는 데이터. 등록해놓으면 꺼내쓸 수 있다.
   
   // 결과 화면 만들기
   for (File uploadFile : uploadFiles){
     out.println("<div>");
     out.println("<a href=\"" + uploadFile.getPath().substring(2) + "\">" + uploadFile.getName() + "</a>"); // 절대경로 회피를 위해 루트를 제거. substring(2). 이미지가 아니라서 파일이 열리진 않습니다. 참고만~~
     out.println("</div>");
   }
   
   // +사실 realPath 는 사실 서블릿 JSP 수업할 때 쓰는거지 실제로 쓰진 않습니다. 외부경로 써요! 이거...서버 껐다 키면 사라지고 그런 거라서 원래는 따로 폴더 만듦.
  %>
  
<!--  
  -> 이 부분이 위의 결과화면 만들기 부분과 같이 대체될 수 있다. <%%> 없이 자바로만 작성하기 (자바 out.println으로 html 태그 넣는 방식)
  <% for (File uploadFile : uploadFiles){ %>
  <div>
    <a href="<%=uploadFile.getPath()%>"><%=uploadFile.getName()%></a>
  </div>
  <% } %> 
  -->
  
  <hr>
  
  <div>
  <a href="<%=request.getContextPath()%>/pkg02_builtin_object/write.jsp">작성화면</a>
  </div>

</body>
</html>