package member.action;

import common.*;
import member.service.MemberService;
import member.vo.MemberHistoryVo;
import member.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class LoginProcAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("mber_id");
        String pwd = request.getParameter("mber_pwd");

        RegExp re = new RegExp();
        if (!re.isNotEmpty(id) || !re.isNotEmpty(pwd)){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 또는 비밀번호를 입력해주세요.');history.back();</script>");
            out.close();
            return null;
        }
        MemberService svc = new MemberService();
        MemberVo vo = svc.getMemberInfo(id);
        if (vo == null || !BCrypt.checkpw(pwd,vo.getMber_pwd())) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('아이디 또는 비밀번호를 입력해주세요.');history.back();</script>");
            out.close();
            return null;
        }

//        로그인 처리(로드인 매닞 클래스를 통해 세션 추가)

        LoginManager lm = LoginManager.getInstance();
        vo.setMber_pwd(null);
        lm.setSession(request.getSession(),vo.getMber_sq());

//       히스토리 저장
        MemberHistoryVo mhvo = new MemberHistoryVo();
        mhvo.setHist_mber_sq(vo.getMber_sq());
        mhvo.setHist_gb(true);

        svc.insertMemberHistory(mhvo);

        HttpSession session = request.getSession();
        String  ref = (String) session.getAttribute("ref");

        ActionForward forward = new ActionForward();

        if (ref == null) {
            forward.setPath("/");
        }else {
            forward.setPath(ref);
            session.removeAttribute("ref");
        }
        forward.setRedirect(true);
        return forward;
    }
}
