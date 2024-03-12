package com.gdu.prj.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dao.BoardDaoImpl;
import com.gdu.prj.dto.BoardDto;

import junit.framework.Assert;
//import 되어있으니까 클래스명.메소드명() 이렇게 안하고 메소드명() 이렇게만 해도 됨


/*
 *  Junit4 단위 테스트 수행하기
 *  1. junit.jar 와 hamcrest-core.jar 를 프로젝트에 등록한다. 
 *  2. JUnit Test Case 를 추가한다.
 *  3. @Test Annotation 이 추가된 메소드를 작성한다.
 *  4. [Run As] - [JUnit Test]
 */

/*
 * JUnit4 단위 테스트 대상
 * 1. 영속 계층(Persistance Layer. 즉 DAO)을 테스트한다.
 * 2. WAS (Tomcat 을 의미함)의 개입이 없다. -> WAS 가 실행하는 코드는 테스트를 할 수 없다.
 *    대표적으로 DBCP 설정을 위해 context.xml 파일을 처리하는 과정은 WAS가 담당하므로
 *    04_dbcp 프로젝트는 JUnit 단위 테스트가 불가능하다. 
 */

/*
 * JUnit4 주요 Annotation
 * 1. @Test       : 실제 테스트를 수행하는 메소드
 * 2. @Before     : @Test 메소드 호출 이전에 동작하는 메소드
 * 3. @BeforeAll  : JUnit Test Case(BoardTest.java) 실행 이전에 동작하는 메소드 (static 처리)
 * 4. @After      : @Test 메소드 호출 이후에 동작하는 메소드
 * 5. @AfterAll   : JUnit Test Case(BoardTest.java) 실행 이후에 동작하는 메소드 (static 처리)
 */

public class BoardTest {

  // BoardDao (SingletonPattern)
  private BoardDao boardDao =  BoardDaoImpl.getInstance();
  
 // @Test 이렇게 주석처리하면 test 목록에서 제외됨!
  public void 목록개수테스트() {
    
    int total = boardDao.getBoardCount();
    assertEquals(1000, total);   // total == 1000 이면 테스트 성공   // arg -> 기대 == 실제 (성공(녹색)/실패(빨강색)) // 이것도 당연히 프로젝트 실행~~
  }
  
 // @Test
  public void 목록테스트() {
    Map<String, Object> params = Map.of("sort", "DESC", 
                                        "begin", 1, 
                                        "end", 20);
    
    List<BoardDto> boardList = boardDao.selectBoardList(params);
    
    assertEquals(20, boardList.size());
    
  }
  
 // @Test
  public void 등록테스트() {
    
    BoardDto board = BoardDto.builder()
                        .title("테스트제목")
                        .contents("테스트내용")
                        .build();
    
    int insertCount =boardDao.insertBoard(board);
    
    assertEquals(1, insertCount);
    
    // 데이터 1,000 개가 있는 상태에서 이걸 삽입하면 시퀀스가 돌아가면서 board_no 1이 2개가 생겨 pk를 위반하게 된다.
    // -> 기존 데이터 삭제하고 진행.
    // DB에 테스트코드가 들어와있는걸 확인 가능. 테스트로 해도 실제로 삽입 됩니당
    
  }

 // @Test
  public void 수정테스트() {
   
    BoardDto board = BoardDto.builder()
                          .board_no(1)
                          .title("[수정]테스트제목")
                          .contents("[수정]테스트내용")
                          .build();
    
    int updateCount = boardDao.updateBoard(board);
    
    assertEquals(1, updateCount);
    
    
  }
  
  //@Test
  public void 상세테스트() {
    
    BoardDto board = boardDao.selectBoardByNo(1);
    
    assertNotNull(board);  // board 가 null 이 아니면 테스트 성공
    
    
  }
  
  @Test
  public void 삭제테스트() {
    int deleteCount = boardDao.deleteBoard(1);
    assertEquals(1, deleteCount);
  }
  
  
  
  
  
  
  
  
  
  
}
