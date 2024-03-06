<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#a {
display: none;
}
#b {
display: none;
}
</style>
</head>
<body>



<%
int a = ((int)(Math.random() * 8)) + 2;
int b = ((int)(Math.random() * 8)) + 2;
%>

 <form id="frm" 
        method="GET" 
        action="/test/test02response">
<div>
<button type="button" id="new">새로고침</button>
</div>
<input id="a" name="a" value="<%=a%>"></div>
<input id="b" name="b" value="<%=b%>"></div>
<div>
<%=a%> X <%=b%> = <input type="text" id="answer" name="answer">
<button type="submit" >계산</button> 
</div>
</form>

<script>
 document.getElementById("new").addEventListener("click", function(){
	 window.location="/test/test02.jsp";
 })

</script>







</body>
</html>