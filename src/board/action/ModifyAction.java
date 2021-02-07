package board.action;

import board.service.BoardService;
import board.vo.BoardVo;
import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.RegExp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import static common.RegExp.TYPE_NUMBER;

public class ModifyAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        LoginManager lm = LoginManager.getInstance();
        String mber_Sq = lm.getMemberSq(session);
        if (mber_Sq == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요합니다.');location.href='/member/login';</script>");
            out.close();
            return null;
        }

//        데이터 로드(페이지 번호, sf,sk,sort,sq)
        String pn = request.getParameter("pn");
        String sf = request.getParameter("sf");
        String sk = request.getParameter("sk");
        String sort = request.getParameter("sort");
        String bd_sq = request.getParameter("sq");
//        페이지 번호, sf,sk,sort 는 null 검사 sq 빈값 ,null, 숫자, 0보다 큰지 검사
        RegExp re = new RegExp();
        if (pn == null || sf == null || sk == null || sort == null ||
                bd_sq == null || bd_sq.equals("") || !re.validateCheck(TYPE_NUMBER, bd_sq) || Integer.parseInt(bd_sq) < 1)
        {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근');history.back();</script>");
            out.close();
            return null;
        }
//        bd_sq를 가지고 데이터베이스에서 글 작성자 bd_mber_sq를 불러온다.
        BoardService svc = new BoardService();
        BoardVo vo = svc.getArticleDetail(Integer.parseInt(bd_sq));
        if (vo == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('글 정보를 불러오는데 실패하였습니다.');history.back()';</script>");
            out.close();
            return null;
        }

//        sq와 bd_mber_sq 같은지 비교
        if (Integer.parseInt(mber_Sq) != vo.getBd_mber_sq()) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('잘못된 접근');history.back()';</script>");
            out.close();
            return null;
        }

        //attribute 저장
        request.setAttribute("vo",vo);

//        경로설정
        ActionForward forward = new ActionForward();
        forward.setPath("/views/board/modifyForm.jsp");
        return forward;
    }
}