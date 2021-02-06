<%@ page import="member.vo.MemberVo" %><%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 21. 1. 24.
  Time: 오전 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String id = (String) request.getAttribute("id");%>
<html>
<head>
    <title>아이디 찾기 결과</title>
</head>
<body>
<div>
아이디 : <%=id%>
</div>
<a href="/member/login">로그인</a>
<a href="/member/searchPassword">비밀번호찾기</a>
<a href="/">홈으로</a>
</body>
</html>
