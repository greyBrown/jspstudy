<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>편집화면</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
</head>
<body>

<div>
  <form id="frm-edit"
        method="POST"
        action="${contextPath}/board/modify.brd">
           <div>
      <label for="board_no">게시글번호</label>
      <input type="text" id="board_no" name="board_no" value="${board.board_no}" readonly> <%-- DB에서 가져온 제목과 내용을 뿌려준다. 편집이란게 그런거니까! --%>
    </div>
    <div>
      <label for="title">제목</label>
      <input type="text" id="title" name="title" value="${board.title}"> <%-- DB에서 가져온 제목과 내용을 뿌려준다. 편집이란게 그런거니까! --%>
    </div>
    <div>
      <textarea rows="5" cols="50" name="contents">${board.contents}</textarea>
    </div>
    <div>
      <button type="submit">수정완료</button>
      <button type="reset">초기화</button>
      <button type="button" id="btn-list">목록보기</button>
    </div>
  </form>
</div>

<script>

  document.getElementById('frm-edit').addEventListener('submit', (evt)=>{
	  const title = document.getElementById('title');
	  if(title.value.trim() === ''){                    //trim을 추가해서 제목에 공백만('    ')채우는 것을 방지.
		  alert('제목은 필수입니다.');
		  title.focus();
		  evt.preventDefault();
		  return;
	  }
  })

</script>

</body>
</html>