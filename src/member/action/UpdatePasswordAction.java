package member.action;

import common.*;
import member.service.MemberService;
import member.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

import static common.RegExp.*;

public class UpdatePasswordAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        LoginManager lm = LoginManager.getInstance();
        String sq = lm.getMemberSq(session);
        if (sq == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요합니다.');location.href='/member/login';</script>");
            out.close();
            return null;
        }

        String nowPwd = request.getParameter("nowPwd");
        String pwd = request.getParameter("mber_pwd");
        String confirmPwd = request.getParameter("confirm_mber_pwd");

        RegExp re = new RegExp();

        if (nowPwd == null || nowPwd.equals("")
                ||!re.validateCheck(TYPE_MEMBER_PWD,pwd)
                || !pwd.equals(confirmPwd)) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('입력한 정보를  확인해 주세요.');history.back();</script>");
            out.close();
            return null;
        }
        MemberService svc = new MemberService();
        MemberVo vo = svc.getMemberDetail(Integer.parseInt(sq));

        if (vo == null) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 정보를 불러오는데 실패하였습니다.');history.back();</script>");
            out.close();
            return null;
        }
        if (!BCrypt.checkpw(nowPwd,vo.getMber_pwd()))
        {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호 실패하였습니다.');history.back();</script>");
            out.close();
            return null;
        }
        MemberVo vo1 = new MemberVo();

        vo1.setMber_sq(Integer.parseInt(sq));
        vo1.setMber_pwd(BCrypt.hashpw(pwd,BCrypt.gensalt(12)));
        boolean isSuccess = svc.updatePassword(vo1);
        if (!isSuccess){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('비밀번호 변경 실패하였습니다.');history.back();</script>");
            out.close();
            return null;
        }


        ActionForward forward = new ActionForward();
        forward.setPath("/member/detail");
        return forward;
    }
}
