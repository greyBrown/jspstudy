package pkg06_upload;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collection;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, 
                 maxFileSize = 1024 * 1024 * 5,
                 maxRequestSize = 1024 * 1024 * 50) // 이거 무슨 작업한건지 모르겠다...ㅜ 안하면 서버가 안뜨긴 함(익셉션 발생). 사이즈는 임의로 잡으신 것. 뭔지 더 알아보기...


//realpath 는 사실 C:\GDJ77\jspstudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\01_servlet 이런곳임...
//그냥 webapp 여기는 사실 소스코드들만 모여있는곳. realpath 라고 하면 저곳! 

public class Upload extends HttpServlet {
  
  private static final long serialVersionUID = 1L;
   
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // 업로드 경로 (톰캣 내부 경로)
    String uploadPath = request.getServletContext().getRealPath("upload");       
        // 변수 처럼...프로젝트의 시작 시점부터 끝까지 저장되어야 하는 값들이 이 servletContext에 저장된다. 가장 넓은 lifecycle을 가짐 ex)방문자수. 
        //+ project를 context라고 부른다. 그 context
    File uploadDir = new File(uploadPath);
    if(!uploadDir.exists()) {
      uploadDir.mkdir(); //오 반갑다
    }

    
    String originalFilename = null;
    String filesystemName = null;  
    
    // 첨부된 파일 정보 
    //(((+Header : content-disposition 값을 통해 파일인지 아닌지 확인할 수 있다. 이걸 getPart로 알아온다 
    //Part는 예를 들면 각각의 input box 처럼...인터페이스라고 볼 수도 있고...쩃든..)))
   
    Collection<Part> parts = request.getParts();
    for(Part part : parts) {                //array collection 이니까 향포
      //System.out.println(part.getName() + "," + part.getContentType() + "," + part.getSize() + "," + part.getSubmittedFileName()); //name 속성이 출력되는 걸 확인
    //getContentType이 이미지면 image로 시작하는 그거. 이걸 통해서도 알수 있음
      //System.out.println(part.getHeader("Content-Disposition"));//form data 이며...filename은 뭐고 그런거 출력
      
      if(part.getHeader("Content-Disposition").contains("filename")) {
        if(part.getSize() > 0) {
        originalFilename = part.getSubmittedFileName();
        }
      }
      
   // 전송된 파일의 파일명 만들기.
      if(originalFilename != null) {
        int point = originalFilename.lastIndexOf(".");
        String extName = originalFilename.substring(point);  //jpg
        String fileName = originalFilename.substring(0, point); //animal1
        filesystemName = fileName + "_" + System.currentTimeMillis() + extName;
      }
      // 파일시스템명이 null이 아니라면 저장하겠다(null이 같이 출력되니까)
      if(filesystemName != null) {
        part.write(uploadPath + File.separator + filesystemName); //File.separator 서버에 따라 경로구분자 다른거 알아서 처리해주겠다
      }
    } 
    
    // 응답
    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<div><a href=\"/servlet/pkg06_upload/NewFile.html\">입력폼으로 돌아가기</a></div>"); //deployment assembly에서 본 그 경로
    out.println("<hr>");
    out.println("<div>첨부파일명 : " + originalFilename + "</div>");
    out.println("<div>저장파일명 : " + filesystemName +"</div>");
    out.println("<div>저장경로 : " + uploadPath + "</div>" );
    out.println("<hr>");
    

    File[] files = uploadDir.listFiles();
    for(File file : files) {
      String fileName1 = file.getName(); // 파일명_1234567890.jpg
      String ext = fileName1.substring(fileName1.lastIndexOf("."));
      String fileName2 = fileName1.substring(0, fileName1.lastIndexOf("_"));
      out.println("<div><a href=\"/servlet/download?filename=" + URLEncoder.encode(fileName1, "UTF-8") + "\">" + fileName2 + ext + "</a></div>");
    }
    
    

    

    out.flush();
    out.close();
    

    
    
  }

  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    doGet(request, response);
  }

}