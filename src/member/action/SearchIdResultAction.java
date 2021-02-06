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

public class SearchIdResultAction implements Action {
    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        RegExp re = new RegExp();
        if (!re.isNotEmpty(name)|| !re.validateCheck(TYPE_MEMBER_AGE,age)){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('입력한 정보를  확인해 주세요.');history.back();</script>");
            out.close();
            return null;
        }
//        java Bean에 데이터 세팅
        MemberVo vo = new MemberVo();
        vo.setMber_nm(name);
        vo.setMber_age(Integer.parseInt(age));

        // vo에 해당하는 데이터 로드
        MemberService svc = new MemberService();
        String id = svc.getMemberId(vo);
        if (id == null){
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('입력한 정보에 해당하는 회원 정보가 없습니다.');history.back();</script>");
            out.close();
            return null;
        }

//       아이디 attribute에 저장
        request.setAttribute("id",id);

//        페이지 이동
        ActionForward forward = new ActionForward();
        forward.setPath("/views/member/searchResult.jsp");
        return forward;
    }
}
