package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.service.BoardService;
import board.vo.BoardVo;
import common.Action;
import common.ActionForward;
import common.RegExp;

import static common.RegExp.TYPE_NUMBER;

import java.io.PrintWriter;

public class DetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// sq 데이터 로드
		String sq = request.getParameter("sq");
		
		// sq 데이터 검사
		RegExp re = new RegExp();
		if (sq == null || sq.equals("") || !re.validateCheck(TYPE_NUMBER, sq) || Integer.parseInt(sq) < 1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 접근');history.back()';</script>");
			out.close();
			return null;
		}
		
		// sq에 해당하는 글 정보 로드
		BoardService svc = new BoardService();
		
		boolean isSuccess = svc.increseArticleHitCount(Integer.parseInt(sq));
		if (!isSuccess) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('조회수 수정에 실패하였습니다.');history.back()';</script>");
			out.close();
			return null;
		}
		
		
		BoardVo vo = svc.getArticleDetail(Integer.parseInt(sq));
		if (vo == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('글 정보를 불러오는데 실패하였습니다.');history.back()';</script>");
			out.close();
			return null;
		}
		
		// attribute에 vo 저장
		request.setAttribute("vo", vo);
		
		// view 경로 설정
		ActionForward forward = new ActionForward();
		forward.setPath("/views/board/detail.jsp");
		return forward;
	}
}
















