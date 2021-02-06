package member.action;

import common.Action;
import common.ActionForward;
import member.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class AjaxCheckIdAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String  id = request.getParameter("mber_id");

        if (id == null || id.equals(" ")||!Pattern.matches("^[a-z0-9]{4,8}$",id)){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('이름을 확인해 주세요.');history.back();</script>");
            out.close();
            return null;
        }

        MemberService svc = new MemberService();
        int count = svc.getCheckId(id);
        request.setAttribute("result",count == 0 ? "Y" : "N");

        ActionForward forward = new ActionForward();
        forward.setPath("/views/ajax/result.jsp");
        return forward;
    }
}
