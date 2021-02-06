package board.action;

import board.service.BoardService;
import board.vo.BoardVo;
import common.Action;
import common.ActionForward;
import common.LoginManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class WriteProcAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        LoginManager lm = LoginManager.getInstance();
        String sq = lm.getMemberSq(session);

        if (sq == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요합니다.');location.href='member/login';</script>");
            out.close();
            return null;
        }
        String sub = request.getParameter("sub");
        String cntnt = request.getParameter("cntnt");


        BoardVo vo = new BoardVo();
        vo.setBd_mber_sq(Integer.parseInt(sq));
        vo.setBd_sub(sub);
        vo.setBd_cntnt(cntnt);

        BoardService svc = new BoardService();
        svc.getBoardInsert(vo);

        ActionForward forward = new ActionForward();
        forward.setPath("/board/list?pn=1&sf=&sk=&sort=desc");
        forward.setRedirect(true);
        return forward;
    }
}
