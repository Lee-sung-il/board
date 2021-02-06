package member.action;

import common.Action;
import common.ActionForward;
import common.LoginManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class PasswordAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        LoginManager lm = LoginManager.getInstance();
        String sq = lm.getMemberSq(session);
        if (sq == null) {
            String requestURI = request.getRequestURI();
            session.setAttribute("ref",requestURI);
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요합니다.');location.href='/member/login';</script>");
            out.close();
            return null;
        }
        ActionForward forward = new ActionForward();
        forward.setPath("/views/member/passWordForm.jsp");
        return forward;
    }
}
