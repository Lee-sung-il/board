<%@ page import="common.RegExp" %>
<%@ page import="static common.RegExp.EXP_MEMBER_ID" %><%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 21. 1. 16.
  Time: 오후 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
        function validateCheck() {
            var nowPwd = $('#nowPwd');
            var pwd = $('#pwd');
            var confirmPwd = $('#confirmPwd');

            if (!nowPwd.val()){
                alert("현재 비밀번호를 입력해 주세요");
                nowPwd.focus();
                return;
            }

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
<form action="/member/updatePassword" method="post" id="updateForm">
    <div>현재비밀번 <input type="password" name="nowPwd"  id="nowPwd">
    </div>
    <div>비밀번호<input type="password" name="mber_pwd" id="pwd"></div>
    <div>비밀번호확인<input type="password" name="confirm_mber_pwd" id="confirmPwd"></div>
</form>
<button onclick="validateCheck()">수정</button>
<button onclick="location.href='/member/detail'">취소</button>

</body>
</html>
