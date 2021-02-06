package member.action;

import common.Action;
import common.ActionForward;
import common.RegExp;
import member.service.MemberService;
import member.vo.MemberVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static common.RegExp.TYPE_MEMBER_AGE;

public class searchPasswordProcAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        RegExp re = new RegExp();
        if (!re.isNotEmpty(id)|| !re.isNotEmpty(name)|| !re.validateCheck(TYPE_MEMBER_AGE,age)){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('입력한 정보를  확인해 주세요.');history.back();</script>");
            out.close();
            return null;
        }
//        java Bean에 데이터 세팅
        MemberVo vo = new MemberVo();
        vo.setMber_id(id);
        vo.setMber_nm(name);
        vo.setMber_age(Integer.parseInt(age));

        // vo에 해당하는 데이터 로드
        MemberService svc = new MemberService();
        int count = svc.getMemberCount(vo);
        if (count < 1) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('입력한 정보를  확인해 주세요.');history.back();</script>");
            out.close();
            return null;

        }

        request.setAttribute("vo",vo);



//        페이지 이동
        ActionForward forward = new ActionForward();
        forward.setPath("/views/member/searchPasswordProc.jsp");
        return forward;
    }

}

