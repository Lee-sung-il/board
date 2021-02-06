<%@ page import="common.RegExp" %><%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 21. 1. 24.
  Time: 오전 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>아이디 찾기</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
        function validateCheck() {
            var name = $('#name');
            var age = $('#age');

            if (!name.val()){
                alert("이름 입력해 주세요");
                name.focus();
                return;
            }
            if (!age.val()){
                alert("나이를 입력해 주세요");
                age.focus();
                return;
            }

            var regExpAge = new RegExp("<%=RegExp.EXP_MEMBER_AGE%>","g");
            if (regExpAge.exec(age.val()) == null) {
                alert('나이는 3자 이내의 숫자로 입력 주세요.');
                age.focus();
                age.val('');
                return ;
            }

            $('#searchIdForm').submit();

        }
    </script>
</head>
<body>
아이디 찾기 폼입니다.
<form action="/member/seachIdResult" method="post" id="searchIdForm">
    <div>이름<input type="text" name="name"  id="name"></div>
    <div>나이 <input type="text" name="age" id="age"></div>
</form>
<button onclick="validateCheck()">찾기</button>
<button onclick="location.href='/member/login'">취소</button>
</body>
</html>
