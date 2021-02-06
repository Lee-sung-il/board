<%@ page import="member.vo.MemberVo" %><%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 21. 1. 23.
  Time: 오전 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% MemberVo vo = (MemberVo) request.getAttribute("vo"); %>
<html>
<head>
    <title>회원정보</title>
</head>
<body>
<div>
    회원번호 : <%=vo.getMber_sq()%>
</div>
<div>
    아이디 : <%=vo.getMber_id()%>
</div>
<div>
    이름 : <%=vo.getMber_nm()%>
</div>
<div>
    나이 : <%=vo.getMber_age()%>
</div>
<div>
    가입일시 : <%=vo.getMber_dttm()%>
</div>
<a href="/member/update">회원정보수정</a>
<a href="/member/leave">회원탈퇴</a>
<a href="/member/password">비밀번호변경</a>
</body>
</html>
