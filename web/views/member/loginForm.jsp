<%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 21. 1. 17.
  Time: 오후 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">

        function validateCheck() {

            var id = $('#id');
            var pwd = $('#pwd');


            if (!id.val()) {
                alert("아이디를 입력해 주세요");
                id.focus();
                return;
            }
            if (!pwd.val()){
                alert("비밀번호를 입력해 주세요");
                pwd.focus();
                return;
            }
            return true;
        }
    </script>
</head>
<body>
<form action="/member/loginProc" method="post" onsubmit="return validateCheck()">
    <div>
    아이디 : <input type="text" name="mber_id" id="id">
    </div>
    <div>
    비밀번호 : <input type="password" name="mber_pwd" id="pwd">
    </div>
  <div>
    <input type="submit" value="로그인">
      <input type="button" value="취소" onclick="location.href='/'">
  </div>
    <a href="/member/join">회원가입</a>
    <a href="/member/searchId">아이디찾기</a>
    <a href="/member/searchPassword">비밀번호찾기</a>
</form>
</body>
</html>
