package com.gdu.prj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.gdu.prj.dto.BoardDto;

/*
 * view - (filter) - controller - service - dao - db
 */

public class BoardDaoImpl implements BoardDao {
  
  
  // dao 는 db 를 처리한다.
  
  private Connection con;
  private PreparedStatement ps;
  private ResultSet rs;
  
  // Connection Pool 관리를 위한 DataSource 객체 선언
  private DataSource dataSource;
  
  // SingletonPattern
  private static BoardDao boardDao = new BoardDaoImpl();
  private BoardDaoImpl() {
    // META-INF\context.xml 파일에 명시된 Resource 를 이용해 DataSource 객체 생성하기
    try {
      Context context = new InitialContext();
      Context env = (Context)context.lookup("java:comp/env");
      dataSource = (DataSource)env.lookup("jdbc/myoracle");
    } catch (NamingException e) {
        System.out.println("관련 자원을 찾을 수 없습니다.");
    }
  }
  public static BoardDao getInstance() {
    return boardDao;
  }
  
  
  @Override
  public int insertBoard(BoardDto board) {
    int insertCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "INSERT INTO BOARD_T(BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, CURRENT_DATE, CURRENT_DATE)";
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle());        // 첫번째 물음표는 TITLE 이다.
      ps.setString(2, board.getContents());     // 두번째 물음표는 CONTENTS 이다.
      insertCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();   //this.close(); 아까만든 close 메소드 호출
    }
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {
    int updateCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "UPDATE BOARD_T SET TITLE = ?, CONTENTS = ?, MODIFIED_AT = CURRENT_DATE WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setString(1, board.getTitle());
      ps.setString(2, board.getContents());
      ps.setInt(3, board.getBoard_no());
      updateCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    
    return updateCount;
  }

  @Override
  public int deleteBoard(int board_no) {
    int deleteCount=0;
    try {
      con = dataSource.getConnection();
      String sql = "DELETE FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return deleteCount;
  }

  @Override
  public int deleteBoards(String param) {
    int deleteCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "DELETE FROM BOARD_T WHERE BOARD_NO IN("+ param +")"; //setString으로인해 작따가 포함되어 ?가 아닌 +param 으로 넣어줌. 좋은 해결책은 아니지만 지금은 이렇게 해봅니다. 
      ps = con.prepareStatement(sql);
      deleteCount = ps.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return deleteCount;
  }
  
  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> params) {   
    List<BoardDto> boardList = new ArrayList<>();
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT"
                 + "  FROM (SELECT ROW_NUMBER() OVER (ORDER BY BOARD_NO DESC) AS RN, BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT " // 이 자리가 정렬 자리임.  네이버처럼 변수로 처리할 수도 있죠.
                 + "          FROM BOARD_T)"
                 + "WHERE RN BETWEEN ? AND ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, (int)params.get("begin"));   // MAP에 Object 로 저장되어 있으니까 int 로 꺼낼 때 다운캐스팅 해줘야함.
      ps.setInt(2, (int)params.get("end"));   
      
      rs = ps.executeQuery();
      while(rs.next()) {
        BoardDto board = BoardDto.builder()
                          .board_no(rs.getInt(1))
                          .title(rs.getString(2))
                          .contents(rs.getString(3))
                          .modified_at(rs.getDate(4))
                          .created_at(rs.getDate(5))
                        .build();
        boardList.add(board);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return boardList; 
  }

  @Override
  public int getBoardCount() {
    int boardCount = 0;
    try {
      con = dataSource.getConnection();
      String sql = "SELECT COUNT(*) FROM BOARD_T";
      ps = con.prepareStatement(sql);
      rs = ps.executeQuery();                          // select 무조건 rs 가 받습니다. 기억나죠?! row의 개수는 1개(게시글이 없더라도 0행이라는건 출력됨)
      if(rs.next()) {                                  // 그래서 한번만 호출 ->while 이 아니라 if
        boardCount = rs.getInt(1);
      }
    } catch (Exception e) {
       e.printStackTrace();
    } finally {
      close();
    }
    return boardCount;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {
    BoardDto board = null;
    
    
    try {
      con = dataSource.getConnection();
      String sql = "SELECT BOARD_NO, TITLE, CONTENTS, MODIFIED_AT, CREATED_AT FROM BOARD_T WHERE BOARD_NO = ?";
      ps = con.prepareStatement(sql);
      ps.setInt(1, board_no);
      rs = ps.executeQuery();               //board_no가 pk 니까 검색결과의 row는 1 혹은 0임. while 아니라 if! 반드시 if! 기필코 if!
      if(rs.next()) {
        board = BoardDto.builder()
                     .board_no(rs.getInt(1))
                     .title(rs.getString(2))
                     .contents(rs.getString(3))
                     .modified_at(rs.getDate(4))
                     .created_at(rs.getDate(5))
                     .build();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      close();
    }
    return board;
  }
  
  

  @Override
  public void close() {
    try {
     if(rs != null) rs.close();
     if(ps != null) ps.close();
     if(con != null) con.close();  // Connection 반납으로 동작 (jdbc 에서는 소멸이었다면 여기서는 반납의 형태가 된다)
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
