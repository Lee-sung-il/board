package member.action;

import common.Action;
import common.ActionForward;
import common.LoginManager;
import common.RegExp;
import member.service.MemberService;
import member.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import static common.RegExp.*;
import static common.RegExp.TYPE_MEMBER_AGE;

public class UpdateProcAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        LoginManager lm = LoginManager.getInstance();
        String sq = lm.getMemberSq(session);

        String name = request.getParameter("mber_nm");
        String  age = request.getParameter("mber_age");
        RegExp re = new RegExp();

        if (!re.validateCheck(TYPE_MEMBER_NAME,name)
                || !re.validateCheck(TYPE_MEMBER_AGE,age)
              ) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('입력한 정보를  확인해 주세요.');history.back();</script>");
            out.close();
            return null;
        }
        MemberVo vo = new MemberVo();
        vo.setMber_sq(Integer.parseInt(sq));
        vo.setMber_nm(name);
        vo.setMber_age(Integer.parseInt(age));

        MemberService svc = new MemberService();
        boolean isSuccess = svc.updateMemberInfo(vo);
        if (!isSuccess){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('입력한 정보를  확인해 주세요.');history.back();</script>");
            out.close();
            return null;
        }
        ActionForward forward = new ActionForward();
        forward.setPath("/member/detail");
        return forward;
    }
}
