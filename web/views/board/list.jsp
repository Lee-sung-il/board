<%@ page import="board.vo.BoardVo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="common.PageInfo" %>
<%@ page import="common.LoginManager" %><%--
  Created by IntelliJ IDEA.
  User: iseong-il
  Date: 2021-01-30
  Time: 10:04 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<BoardVo> list = (ArrayList<BoardVo>) request.getAttribute("list");
    PageInfo pi = (PageInfo) request.getAttribute("pi");
    String  sk = request.getParameter("sk");
    String sf = request.getParameter("sf");
    String sort = request.getParameter("sort");
    String pn = request.getParameter("pn");

%>
<html>
<head>
    <title>게시판</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function (){
        $('#sort').val('<%=sort%>').prop('selected',true);
        var sf1 = '<%=sf%>';
        if (sf1 == ''){
            $('#sf').val('sub').prop('selected',true);
        }else{
            $('#sf').val('<%=sf%>').prop('selected',true);
        }});



        function searchArticle() {
            $("#sk").keyup(function (event) {
                // 엔터키 검사
                if (event.keyCode == 13) {
                    var sk = $('#sk');
                    if (!sk.val()) {
                        alert('검색키워드를 입력해 주세요');
                        sk.focus();
                        return;
                    }

                    var regExp = new RegExp("^[0-9a-zA-Z가-힣 ]{2,10}", "g");

                    if (regExp.exec(sk.val()) == null) {
                        alert('검색 키워드는 2~10자의 영문,슷지,한글로 입력해주세요.');
                        sk.focus();
                        sk.val('');
                        return;
                    }
                    location.href = "/board/list?pn=1&sf=" + $("#sf option:selected").val() + "&sk=" + sk.val() +"&sort=desc";

                }
            });

        }
        function  validateCheck() {
                var sk = $('#sk');
                if (!sk.val()){
                    alert('검색키워드를 입력해 주세요');
                    sk.focus();
                    return;
                }

                var regExp = new RegExp("^[0-9a-zA-Z가-힣 ]{2,10}","g");

                if (regExp.exec(sk.val())==null) {
                    alert('검색 키워드는 2~10자의 영문,슷지,한글로 입력해주세요.');
                    sk.focus();
                    sk.val('');
                    return;
                }
                location.href="/board/list?pn=1&sf="+$("#sf option:selected").val() +"&sk=" +sk.val()+"&sort=desc";
        }
        function sortArticle() {
            location.href="/board/list?pn=1&sf=<%=sf%>&sk=<%=sk%>"+"&sort="+$("#sort option:selected").val();
        }

    </script>
</head>
<body>
총 <%=pi.getTotalArticleCount()%> 개의 글이 있습니다.

<div>
    <select  id="sort" onchange="sortArticle()">
        <option value="desc"  >최신순</option>
        <option value="asc">오래된순</option>
    </select>

    <select name="sf" id="sf">
        <option value="sub" >제목</option>
        <option value="subttl">제목 + 내용</option>
    </select>
    <input type="text" id="sk" name="sk" value="<%=sk%>" placeholder="2자 이상의 검색키워드" oninput="searchArticle()">
    <input type="button" value="검색" onclick="validateCheck()">
    <button onclick="location.href='/board/list?pn=1&sf=&sk=&sort=desc'">초기화</button>

</div>

<table>
    <tr>
        <td>번호</td>
        <td>제목</td>
        <td>작성자</td>
        <td>작성일시</td>
        <td>조회수</td>
    </tr>
    <%
        for (int i = 0; i < list.size(); i++){
    %>
    <tr>
        <td onclick="location.href='/board/detail?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>">&sq='<%=list.get(i).getBd_sq()%></td>
        <td><%=list.get(i).getBd_sub()%></td>
        <td><%=list.get(i).getMber_id()%></td>
        <td><%=list.get(i).getBd_dttm()%></td>
        <td><%=list.get(i).getBd_hit()%></td>
    </tr>
    <%
        }
    %>
</table>
<a href="/board/list?pn=<%=pi.getPrePageNumber()%>&sf=&sk=&sort=desc"><</a>
<%
    for (int i = pi.getStartPageNumber(); i <= pi.getEndPageNumber(); i++) {
%>
<a href="/board/list?pn=<%=i%>&sf=&sk=&sort=desc"><%=i%></a>
<%
    }
%>
<a href="/board/list?pn=<%=pi.getNextPageNumber()%>&sf=&sk=&sort=desc">></a>
<%
    LoginManager lm = LoginManager.getInstance();
    String sq = lm.getMemberSq(session);
%>
<%if (sq == null) { %>
    <button onclick = "alert('로그인을 해주세요.');location.href='/member/login'" > 글쓰기 </button >
<%}else {%>
<button onclick = "location.href='/board/write?pn=<%=pn%>&sf=<%=sf%>&sk=<%=sk%>&sort=<%=sort%>'" > 글쓰기 </button >
<%}%>


</body>
</html>
