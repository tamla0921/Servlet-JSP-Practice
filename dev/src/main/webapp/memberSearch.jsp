<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
    <title>조회</title>
</head>
<body>
    <h3>회원정보 검색</h3>
    
    ${error }
    
    <form action="memberSearch.do" method="post">
        ID : <Input type="text" name="id" />
        <input type="hidden" name="job" value="search" />
        <%--
            화면에서는 출력되지는 않지만, 서버에 특정한 값을 전달!!!
            회원정보 검색/수정/삭제 작업 모두 데이터베이스에서 회원을 검색하는 일이 선행되어야 함.
            => 회원 id를 통해 검색하는 작업은 모두 memberSearch.do 요청으로 공통적으로 처리.
            
            그리고 검색 후 이동하는 뷰 페이지가 다르므로, 현재 진행 중인 작업이 검색, 수정, 삭제 중 어떤 작업인지를 알아야
            해당 뷰 페이지로 이동할 수 있음.
            => 현재 진행 중인 작업이 무엇인지를 나타내기 위해 job 변수 사용
            => job = search 이므로 검색 작업을 의미.
         --%>
        <input type="submit" value="검색 " />
    </form>
</body>
</html>