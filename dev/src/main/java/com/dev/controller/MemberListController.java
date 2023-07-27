package com.dev.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

public class MemberListController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberService service = MemberService.getInstance();
        ArrayList<MemberVO> list = service.memberList();
        
        request.setAttribute("list", list);
        HttpUtil.forward(request, response, "/result/memberListOutput.jsp");
    }
}
