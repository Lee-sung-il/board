<%@ page import="common.RegExp" %>
<%@ page import="member.vo.MemberVo" %><%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 21. 1. 24.
  Time: 오후 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberVo vo = (MemberVo) request.getAttribute("vo");
%>
<html>
<head>
    <title>회원가입</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
        function validateCheck() {
            var pwd = $('#pwd');
            var confirmPwd = $('#confirmPwd');


            if (!pwd.val()){
                alert("비밀번호를 입력해 주세요");
                pwd.focus();
                return;
            }
            if (!confirmPwd.val()){
                alert("비밀번호확인을 입력해 주세요");
                confirmPwd.focus();
                return;
            }

            var refExpPwd = new RegExp("<%=RegExp.EXP_MEMBER_PWD%>","g");
            if (refExpPwd.exec(pwd.val())==null){
                alert("비밀번호는 4~12자의 영문, 숫자, 특수기호(!,@,#,$,%,^,&,*)로 입력해 주세요");
                pwd.val('');
                pwd.focus();
                return;
            }

            if (pwd.val() != confirmPwd.val()) {
                alert("비밀번호 일치하지 않습니다.");
                confirmPwd.val('');
                confirmPwd.focus();
                return;
            }

            $('#updateForm').submit();
        }
    </script>
</head>
<body>
비밀번호 변경 폼입니다.
<form action="/member/updatePasswordProc" method="post" id="updateForm">
    <div>비밀번호<input type="password" name="pwd" id="pwd"></div>
    <div>비밀번호확인<input type="password" name="confirmPwd" id="confirmPwd"></div>
    <div>
        <input type="hidden" name="id" value="<%=vo.getMber_id()%>">
    </div>
    <div>
        <input type="hidden" name="name" value="<%=vo.getMber_nm()%>">
    </div>
    <div>
        <input type="hidden" name="age" value="<%=vo.getMber_age()%>">
    </div>
</form>
<button onclick="validateCheck()">수정</button>
<button onclick="location.href='/member/detail'">취소</button>

</body>
</html>
