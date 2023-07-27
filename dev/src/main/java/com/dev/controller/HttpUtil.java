package com.dev.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 컨트롤러가 마지막으로 처리해야 하는 기능인 처리 결과(Output View) 페이지로 이동 작업.
 * 모든 컨트롤러에서 공통으로 처리하는 기능을 별도의 클래스에 구현하여 재사용.
 * @author seo
 *
 */
public class HttpUtil {

    public static void forward(HttpServletRequest request, HttpServletResponse response, String path) {
        
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(path);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            System.out.println("forward 오류 : " + ex);
        }
    }
}
