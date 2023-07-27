package com.dev.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet {

    private static final long SerialVersionUID = 1L;
    String charset = null;
    // 인코딩하려는 문자코드 저장할 변수.
    HashMap<String, Controller> list = null;
    // 클라이언트의 요청에 대하여 실제 실행할 컨트롤러가 누구인지에 대한 정보를 저장할 변수.
    
    /**
     * 현재 servlet 객체가 최초로 요청이 들어왔을 때 한 번만 실행되는 메서드.
     * servlet 초기화 기능.
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        charset = config.getInitParameter("charset");
        list = new HashMap<String, Controller>();
        // HashMap에 데이터 넣기... 객체를 넣어도 가능함!
        list.put("/memberInsert.do", new MemberInsertController());
        list.put("/memberSearch.do", new MemberSearchController());
        list.put("/memberUpdate.do", new MemberUpdateController());
        list.put("/memberDelete.do", new MemberDeleteController());
        list.put("/memberList.do", new MemberListController());
        /*
         * 프런트 컨트롤러가 클라이언트 요청에 대하여 실제로 처리하는 컨트롤러(서브 컨트롤러) 실행하는 기능.
         * 일반적으로 클라이언트가 요청을 보냈을 때 전달되는 URL에서 Context path(웹 애플리케이션 이름) 다음의 값을 key로 지정.
         * </memberInsert.do, new MemberInsertController()>
         */
    }
    
    /**
     * 클라이언트로부터 요청이 들어올 때마다 실행하는 메서드.
     */
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding(charset); //POST 방식으로 전달된 질의 문자열을 인코딩 처리!
        // 이미 프론트 컨트롤러에서 인코딩 처리해서, HashMap을 이용해 객체 생성, request, response 전달하므로
        // 서브 컨트롤러에서도 인코딩된 값을 이용할 수 있음.
        
        String url = req.getRequestURI(); // /dev/memberInsert.do
        String contextPath = req.getContextPath(); // /dev
        String path = url.substring(contextPath.length()); // url.subString(4) -> /memberInsert.dao
        /*  substring
         *      Returns a string that is a substring of this string. The substring begins with the character at the specified index andextends to the end of this string. 
         */
        
        
        Controller subController = list.get(path);
        // init() 메서드에서 열심히 put을 해서 데이터를 만듦.
        // path = /memberInsert.do 라면, list.get(path)의 값은 MemberInsertController 객체.
        
        subController.execute(req, resp);
        // dev 애플리케이션의 Controller 객체는 Controller 인터페이스 상속받아 작성하도록 규정할 것.
        // 따라서 모든 Controller 객체에서는 execute() 메서드를 재정의하여 Controller 기능 구현.
        // list에서 추출한 Controller 객체가 무엇이든지 간에 execute() 메서드가 실행되도록 인터페이스 통일.
    }
}
