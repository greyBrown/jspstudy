package pkg06_upload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	  request.setCharacterEncoding("UTF-8");
	  
	  String filename = request.getParameter("filename");
	  
	  String uploadPath = request.getServletContext().getRealPath("upload");  //getRealPath() -> 폴더 upload 까지에 이르는 실제 경로를 반환
	  
	  File file = new File(uploadPath, filename);
	  System.out.println(file.exists());
	  
	  // 원본 파일(서버) 입력 스트림 생성
	  BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
	  
	  // 다운로드용 응답 헤더("Content-Dispotision: attachment;")
	  response.setHeader("Content-Disposition", "attachment"); // 다운로드 대화상자 나옴
	  response.setHeader("Content-Disposition", "attachment; filename=" + filename); //다운로드 대화상자 없이 지정한 filename 으로 곧바로 진행  //filename= 이렇게 간단하게만 적어줘도 알아서 해줌!! 고맙다...
	  
	  // 복사 파일(클라이언트) 출력 스트림 생성
	  BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream()); // 클라이언트 측으로 보내는 스트림은 응답을 통해서 얻어낸다.(response)
	  
	  // 복사 (서버 -> 클라이언트)
	  byte[] b = new byte[1024];
	  int readByte = 0;
	  while((readByte = in.read(b)) != -1){
	    out.write(b, 0, readByte);
	  }
	  
	  // 스트림 닫기
	  out.close();
	  in.close();
	  
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
