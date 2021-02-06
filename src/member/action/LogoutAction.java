package member.action;

import common.Action;
import common.ActionForward;
import common.LoginManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        HttpSession session = request.getSession();
        LoginManager lm = LoginManager.getInstance();
        if (lm.getMemberSq(session) == null){
            session.invalidate();
            forward.setPath("/");
            forward.setRedirect(true);
            return forward;
        }else {
            lm.removeSession(lm.getMemberSq(session));
        }
        forward.setPath("/");
        forward.setRedirect(true);
        return forward;
    }
}
