package com.gdu.prj.utils;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MyPageUtils {
  
  private int total;    // 전체 게시글 개수                      (DB에서 구한다.)
  private int display;  // 한 페이지에 표시할 게시글 개수        (요청 파라미터로 받아온다.)
  private int page;     // 현재 페이지 번호                      (요청 파라미터로 받아온다.)
  private int begin;    // 한 페이지에 표시할 게시글의 시작 번호 (계산한다.)
  private int end;      // 한 페이지에 표시할 게시글의 종료 번호 (계산한다.)
  
  public void setPaging(int total, int display, int page) {
    this.total = total;
    this.display = display;
    this.page = page;
    
    begin = (page - 1) * display + 1; // 만약 3page 뽑겠다 -> 2*40+1 =  41번부터 가져와라
    end   = begin + display - 1;

    //BOARD_NO 의 경우 수정 삽입 삭제 등의 과정에서 BOARD_NO가 변하면서[(123)(2번삭제)(13)] 데이터개수가 차이가 나기 때문에 사용할 수 없음.
    // -> 정렬 후 생성한 ROW_NUMBER 를 통해 계산한다. (ROWNUM의 1-20, 21-40, 41-60)
    // 1000-1002 이런 경우...between 1000 and 1020 쿼리문 결과랄 다를 바가 없으므로 딱히 보정 안해줘도 됨.
  }
  
  
  
  
  
  
  
  
  

}
