<%@ page import="member.vo.MemberVo" %>
<%@ page import="common.RegExp" %><%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 21. 1. 23.
  Time: 오전 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%MemberVo vo = (MemberVo) request.getAttribute("vo");%>
<html>
<head>
    <title>회원정보 수정</title>
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

            var regExpName = new RegExp("<%=RegExp.EXP_MEMBER_NAME%>","g");
            //name 변수를 정규표현식으로 검사
            if (regExpName.exec(name.val()) == null) {
                alert('이름은 2~5자의 한글로 입력해 주세요.');
                name.focus();
                //아이디가 name인 컴포넌트의 값 ''로 변경
                name.val('');
                return ;
            }
            var regExpAge = new RegExp("<%=RegExp.EXP_MEMBER_AGE%>","g");
            if (regExpAge.exec(age.val()) == null) {
                alert('나이는 3자 이내의 숫자로 입력 주세요.');
                age.focus();
                age.val('');
                return ;
            }

            $('#updateForm').submit();
        }
    </script>
</head>
<body>
회원정보 수정폼입니다.
<form action="/member/updateProc" method="post" id="updateForm">
    <div>아이디 : <%=vo.getMber_id()%>
    </div>
    <div>이름<input type="text" name="mber_nm"  id="name" value="<%=vo.getMber_nm()%>"></div>
    <div>나이 <input type="text" name="mber_age" id="age" value="<%=vo.getMber_age()%>"></div>
</form>
<button onclick="validateCheck()">수정</button>
<button onclick="location.href='/member/detail'">취소</button>

</body>
</html>
