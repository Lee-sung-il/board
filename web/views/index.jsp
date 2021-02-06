<%@ page import="common.LoginManager" %>
<%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 21. 1. 16.
  Time: 오전 11:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    LoginManager lm = LoginManager.getInstance();
    String sq = lm.getMemberSq(session);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% if (sq == null) { %>
<a href="/member/join">회원가입</a>
<a href="/member/login">로그인</a>
<%} else {%>
<a href="/member/detail">회원정보</a>
<a href="/member/logout">로그아웃</a>
<%}%>
<a href="/board/list?pn=1&sf=&sk=&sort=desc">게시판</a>
</body>
</html>
