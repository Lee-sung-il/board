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
        var flag = false;
        function initFlag() {
            flag = false;

        }
        function CheckId() {
            var id = $('#id');
            if (!id.val()) {
                alert("아이디를 입력해 주세요");
                id.focus();
                return;
            }
            var refExpId = new RegExp("^[a-z0-9]{4,8}$","g");
            if (refExpId.exec(id.val())==null){
                alert("아이디는 4~8자의 영문소문자, 숫자로 입력해 주세요");
                id.val('');
                id.focus();
                return;
            }
        //    아이디 겁사 Ajax
            $.ajax({
                url : "/member/ajaxCheckId",
                type : "post",
                dataType : "json",
                data : {
                    mber_id : id.val()
                },
                error : function () {
                    alert('서버 통신 실패')
                },
                success : function (data) {
                    if (data.flag == 'Y'){
                        alert('사용할 수 있는 아이디입니다.');
                        flag = true;
                    }else {
                        alert('중복된 아이디입니다.');
                        initFlag();
                    }

                }
            });

        }
        function validateCheck() {

            var id = $('#id');
            var pwd = $('#pwd');
            var confirmPwd = $('#confirmPwd');
            var name = $('#name');
            var age = $('#age');

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
            if (!confirmPwd.val()){
                alert("비밀번호확인을 입력해 주세요");
                confirmPwd.focus();
                return;
            }
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
            var refExpId = new RegExp("<%=RegExp.EXP_MEMBER_ID%>","g");
            if (refExpId.exec(id.val())==null){
                alert("아이디는 4~8자의 영문소문자, 숫자로 입력해 주세요");
                id.val('');
                id.focus();
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
            if (!flag) {
                alert('아이디 중복검사를 해주세요');
                return;
            }

            $('#joinForm').submit();
        }
    </script>
</head>
<body>
회원가입 폼입니다.
<form action="/member/joinProc" method="post" id="joinForm">
    <div>아이디 <input type="text" name="mber_id"  id="id" oninput="initFlag()">
    <button type="button" onclick="CheckId()">중복확인</button>
    </div>
    <div>비밀번호<input type="password" name="mber_pwd" id="pwd"></div>
    <div>비밀번호확인<input type="password" name="confirm_mber_pwd" id="confirmPwd"></div>
    <div>이름<input type="text" name="mber_nm"  id="name"></div>
    <div>나이 <input type="text" name="mber_age" id="age"></div>
</form>
<button onclick="validateCheck()">가입</button>
<button onclick="location.href='/'">취소</button>

</body>
</html>
