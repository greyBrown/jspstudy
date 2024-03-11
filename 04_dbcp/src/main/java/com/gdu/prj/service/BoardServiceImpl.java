package com.gdu.prj.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gdu.prj.common.ActionForward;
import com.gdu.prj.dao.BoardDao;
import com.gdu.prj.dao.BoardDaoImpl;
import com.gdu.prj.dto.BoardDto;
import com.gdu.prj.utils.MyPageUtils;

import jakarta.servlet.http.HttpServletRequest;

public class BoardServiceImpl implements BoardService {
  
  // service 는 dao 를 호출한다.
  private BoardDao boardDao = BoardDaoImpl.getInstance();
  
  // 목록 보기는 MyPageUtils 객체가 필요하다.
  private MyPageUtils myPageUtils = new MyPageUtils();

  @Override
  public ActionForward addBoard(HttpServletRequest request) {
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    BoardDto board = BoardDto.builder()
                        .title(title)
                        .contents(contents)
                      .build();
    int insertCount = boardDao.insertBoard(board);
    // redirect 경로는 URLMapping 으로 작성한다. (외워용 ㅎ 패턴 메뉴얼이 이럼. 제목에 파일경로 뜨면 비정상인거임...)       !!!!!!!!!! 뭘로 작성한다고?? URLMapping으로!!!!!! forward가 ~~jsp로 이동하는거고 redirect는 URL 요청!!
    String view = null;
    if(insertCount == 1) {
      view = request.getContextPath() + "/board/list.brd"; //"/board/list.jsp" 이 주소는 안됨 list.brd 로 가야지 service 가 돌면서 새로운 목록을 가지고 온다. jsp 주소는 컨트롤러에 적는다.
    } else if(insertCount ==0) {
      view = request.getContextPath() + "/main.brd";
    }
    //INSERT 이후 이동은 redirect 이다.
    return new ActionForward(view, true);
  }

  @Override
  public ActionForward getBoardList(HttpServletRequest request) {
    
    // 전체 게시글 개수
    int total = boardDao.getBoardCount();
    
    // 한 페이지에 표시할 게시글 개수
    Optional<String> optDisplay = Optional.ofNullable(request.getParameter("display"));
    int display = Integer.parseInt(optDisplay.orElse("20"));
    
    // 현재 페이지 번호
    Optional<String> optPage = Optional.ofNullable(request.getParameter("page"));
    int page = Integer.parseInt(optPage.orElse("1"));
    
    // 페이징 처리에 필요한 변수 값 계산하기
    myPageUtils.setPaging(total, display, page);
    
    // 목록을 가져올 때 필요한 변수를 Map 에 저장함
    Map<String, Object> params = Map.of("begin", myPageUtils.getBegin(), 
                                          "end", myPageUtils.getEnd()); 
    
    // 목록 가져오기
    List<BoardDto> boardList = boardDao.selectBoardList(params);            // 이제 게시글이 20개씩 뜬다! 아무런 파라미터가 없어도 opt 로 20으로 설정. ?page=3&display=5 등으로 접근할 수 있다 오오오오오 
                                                                            // 그때 콘솔에 파라미터 뜨게 작업해놔서 콘솔에 파라미터 뭐 넣었는지 뜬다 오오오ㅗ
    request.setAttribute("total", total);
    request.setAttribute("boardList", boardList);
    return new ActionForward("/board/list.jsp", false);
  }

  @Override
  public ActionForward getBoardByNo(HttpServletRequest request) {
    Optional<String> opt = Optional.ofNullable(request.getParameter("board_no"));
    int board_no = Integer.parseInt(opt.orElse("0"));
    BoardDto board = boardDao.selectBoardByNo(board_no);
    String view = null;
    if(board != null) {
      view = "/board/detail.jsp";
      request.setAttribute("board", board);
    } else {
      view ="/index.jsp";
    }
    return new ActionForward(view, false);
  }

  @Override     //editBoard 는 편집화면으로 가는 기능을 제공(select). 편집 자체는 modifyBoard가 진행한다.
  public ActionForward editBoard(HttpServletRequest request) { //name 이 있다고 박아놨기에 null 값은 오지 않지만, 빈문자열 처리는 해줘야함(사실 잘 이해안감)
    String param = request.getParameter("board_no");
    int board_no = 0;
    if(!param.isEmpty()) {
      board_no = Integer.parseInt(param);
    }
    BoardDto board = boardDao.selectBoardByNo(board_no);
    String view = null;
    if(board != null) {
      view = "/board/edit.jsp";
      request.setAttribute("board", board);
    } else {
      view = "/index.jsp";
    }
    return new ActionForward(view, false);
  }

  @Override
  public ActionForward modifyBoard(HttpServletRequest request) {
    int board_no = Integer.parseInt(request.getParameter("board_no"));
    String title = request.getParameter("title");
    String contents = request.getParameter("contents");
    BoardDto board = BoardDto.builder()
                           .title(title)
                           .contents(contents)
                           .board_no(board_no)
                         .build();
    int updateCount = boardDao.updateBoard(board);
    String view = null;
    if(updateCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/detail.brd?board_no=" + board_no;  // 성공!!! 헤헤
    }
    return new ActionForward(view, true);
  }

  @Override
  public ActionForward removeBoard(HttpServletRequest request) {
    String param = request.getParameter("board_no");
    int board_no = 0;
    if(!param.isEmpty()) {
      board_no = Integer.parseInt(param);
    }
    int deleteCount = boardDao.deleteBoard(board_no);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    return new ActionForward(view, true);            //Insert Update Delete 는 redirect 3종 쎄뚜
  }
  
  @Override
  public ActionForward removeBoards(HttpServletRequest request) {
    String param = request.getParameter("param");
    int deleteCount = boardDao.deleteBoards(param);
    String view = null;
    if(deleteCount == 0) {
      view = request.getContextPath() + "/main.brd";
    } else {
      view = request.getContextPath() + "/board/list.brd";
    }
    return new ActionForward(view, true);
  } 
  
  
}
