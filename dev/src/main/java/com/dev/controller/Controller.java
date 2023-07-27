package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 모든 Controller 객체에서 컨트롤러 기능을 구현하는 메서드를 통일하기 위해 만든 객체.
 * @author seo
 *
 */
public interface Controller {
    /**
     * 모든 Controller 클래스들이 반드시 재정의 해, 컨트롤러 기능 구현.
     * FrontController 객체의 service() 메서드에서 호출.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
