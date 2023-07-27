package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

/**
 * memberSearch.do 요청을 처리하는 객체.
 * @author seo
 *
 */
public class MemberSearchController implements Controller {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        // Parameter 추출
        String id = request.getParameter("id");
        String job = request.getParameter("job");
        String path = null;
        
        if (job.equals("search")) {
            path = "/memberSearch.jsp";
        } else if (job.equals("update")) {
            path = "/memberUpdate.jsp";
        } else if (job.equals("delete")) {
            path = "/memberDelete.jsp";
        }
        
        // 유효성 체크
        if (id.isEmpty()) {
            request.setAttribute("error", "ID를 입력해주시기 바랍니다.");
            HttpUtil.forward(request, response, path);
            return;
        }
        
        // Service 객체의 메서드 호출.
        MemberService service = MemberService.getInstance();
        MemberVO member = service.memberSearch(id);
            // 입력받은 id에 해당하는 정보를 데이터베이스에 추출하여 MemberVO 객체에 담아서 반환.
        
        // Output View 페이지로 이동
        if (member == null) {
            // 클라이언트가 입력한 id와 일치하는 id 정보가 데이터베이스에 없는 경우.
            request.setAttribute("result", "검색된 정보가 없습니다.");
        }
        
        request.setAttribute("member", member);
        
        if(job.equals("search")) {
            path = "/result/memberSearchOutput.jsp";
            HttpUtil.forward(request, response, path);
        } else if (job.equals("update")) {
            path = "/memberUpdate.jsp";
            HttpUtil.forward(request, response, path);
        } else if (job.equals("delete")) {
            path = "/memberDelete.jsp";
            HttpUtil.forward(request, response, path);
        }
        
    } // execute() 메서드 끝.
} // 클래스 끝.
