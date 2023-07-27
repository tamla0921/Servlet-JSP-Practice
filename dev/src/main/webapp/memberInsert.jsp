<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>

<head>
    <title>생성</title>
</head>

<body>
    <h3>회원 가입</h3>
    
    ${error }
    <%-- request.getAttribute("error") --%>
    <%-- 회원정보를 제대로 입력하지 않을 경우, 현재 페이지로 돌아오면서 request에 error 이름으로 오류 메시지 저장해서 보냄. --%>
    <%-- MemberInsertController에서 request에 "error" 속성 부여. --%>
    <%-- 속성일 경우, 그냥 error로만 적어도 가능한 듯! --%>
    
    
    <%-- <form> 태그의 action 속성은 폼 데이터(form data)를 서버로 보낼 때 해당 데이터가 도착할 URL을 명시 --%>
    <%-- web.xml, servlet-mapping, url-pattern에서 .do로 끝나는 url은 프론트 컨트롤러 서블릿이 작동한다고 작성 --%>
    <form action="memberInsert.do" method="post">
    
        ID : <input type="text" name="id"> <br/>
        비밀번호 : <input type="password" name="passwd"> <br/>
        이름 : <input type="text" name="name"> <br/>
        E-Mail : <input type="text" name="mail"> <br/>
        
        <input type="submit" value="가입">  
    </form>
</body>
</html>