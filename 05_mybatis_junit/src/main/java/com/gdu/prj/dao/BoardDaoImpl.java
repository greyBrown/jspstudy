package com.gdu.prj.dao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.gdu.prj.dto.BoardDto;

public class BoardDaoImpl implements BoardDao {

  // SqlSession (Connection/PreparedStatement/ResultSet 처리) 만드는 SqlSessionFactory 객체 선언
  private SqlSessionFactory factory = null;
  
  // SingletonPattern
  private static BoardDao boardDao = new BoardDaoImpl();
  private BoardDaoImpl() {
    
    // mybatis-config.xml 파일을 이용한 SqlSessionFactory 객체 생성
    try {
      String resource = "com/gdu/prj/config/mybatis-config.xml";
      InputStream in = Resources.getResourceAsStream(resource);
      factory = new SqlSessionFactoryBuilder().build(in);
    } catch (Exception e) {
        e.printStackTrace();
    }
    
  }
  public static BoardDao getInstance() {
    return boardDao;
  }
  
  
  
  @Override
  public int insertBoard(BoardDto board) {
    SqlSession sqlSession = factory.openSession(false); // autoCommit을 하지 않는다.
    int insertCount = sqlSession.insert("com.gdu.prj.dao.board_t.insertBoard", board);  //마침표로 구분해준다. (경로.메소드)
    if(insertCount == 1) {      //사실 이 메소드 이름...(insert) 여기다가 update delete 써도 오류 안난다. 하지만 사람이 엄청 헷갈리니까 똑바로 적기~~
      sqlSession.commit();      //select는 아님! select라고 해야함.
    }
    sqlSession.close();
    return insertCount;
  }

  @Override
  public int updateBoard(BoardDto board) {
    SqlSession sqlSession = factory.openSession(false);
    int updateCount = sqlSession.update("com.gdu.prj.dao.board_t.updateBoard", board);
    if(updateCount ==1) {
      sqlSession.commit();
    }
    sqlSession.close();
    return updateCount;
  }

  @Override
  public int deleteBoard(int board_no) {
    SqlSession sqlSession = factory.openSession(false);
    int deleteCount = sqlSession.delete("com.gdu.prj.dao.board_t.deleteBoard", board_no);
    if(deleteCount == 1) {
      sqlSession.commit();
    }
    sqlSession.close();
    
    return deleteCount;
  }

  @Override
  public List<BoardDto> selectBoardList(Map<String, Object> params) {
    SqlSession sqlSession = factory.openSession();
    List<BoardDto> boardList = sqlSession.selectList("com.gdu.prj.dao.board_t.selectBoardList", params);
    sqlSession.close();
    return boardList;
  }

  @Override
  public int getBoardCount() {

    SqlSession sqlSession = factory.openSession();
    int total = sqlSession.selectOne("com.gdu.prj.dao.board_t.getBoardCount");
    sqlSession.close();
    return total;
  }

  @Override
  public BoardDto selectBoardByNo(int board_no) {

    SqlSession sqlSession = factory.openSession();
    BoardDto board = sqlSession.selectOne("com.gdu.prj.dao.board_t.selectBoardByNo", board_no);
    sqlSession.close();
    return board;
  }

}
