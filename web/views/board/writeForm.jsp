<%@ page import="common.RegExp" %><%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 2021-01-31
  Time: 12:15 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String pn = request.getParameter("pn");
    String sf = request.getParameter("sf");
    String sk = request.getParameter("sk");
    String sort = request.getParameter("sort");
%>
<html>
<head>
    <title>글쓰기</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">

        function validateCheck() {

            var sub = $('#sub');
            var cntnt = $('#cntnt');


            if (!sub.val()) {
                alert("제목을 입력해 주세요");
                sub.focus();
                return;
            }
            if (!cntnt.val()){
                alert("내용을 입력해 주세요");
                cntnt.focus();
                return;
            }

            var refExpSub = new RegExp("<%=RegExp.EXP_SUB%>","g");
            if (refExpSub.exec(sub.val())==null){
                alert("제목은 2~50자로 입력해 주세요");
                sub.val('');
                sub.focus();
                return;
            }

            $('#writeForm').submit();
        }
    </script>
</head>
<body>
<form action="/board/writeProc" method="post" id="writeForm">
    제목 : <input type="text" name="sub" id="sub">
    내용 : <textarea cols="10" rows="10" name="cntnt" id="cntnt"></textarea>
</form>
<button onclick="validateCheck()">저장</button>
<button onclick="location.href='/board/list?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>'">취소</button>
</body>
</html>
