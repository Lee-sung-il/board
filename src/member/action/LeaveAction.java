package member.action;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import member.service.MemberService;
import member.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LeaveAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ActionForward forward = new ActionForward();
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
        MemberService svc = new MemberService();
        boolean isSuccess =  svc.leaveMember(Integer.parseInt(sq));
        if (!isSuccess){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원탈퇴에 실패 했습니다..');history.back();</script>");
            out.close();
            return null;
        }
        lm.removeSession(sq);

        forward.setPath("/");
        forward.setRedirect(true);
        return forward;
    }
}
