package com.dev.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dev.service.MemberService;
import com.dev.vo.MemberVO;

/**
 * 클라이언트로부터 /memberInsert.do 요청을 처리하는 객체로서
 * 회원정보 생성에 관한 기능을 execute() 메서드에 구현.
 * @author seo
 *
 */
public class MemberInsertController implements Controller {
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // 파라미터 추출
        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        
        // 유효성 체크
        if (id.isEmpty() || passwd.isEmpty() || name.isEmpty() || mail.isEmpty()) {
            request.setAttribute("error", "모든 항목을 빠짐없이 입력해주시기 바랍니다.");
            HttpUtil.forward(request, response, "/memberInsert.jsp");
            
            return;
        }
        
        // VO 객체에 데이터 바인딩
         MemberVO member = new MemberVO();
         member.setId(id);
         member.setPasswd(passwd);
         member.setName(name);
         member.setMail(mail);
         
         // Service 객체의 메서드 호출
         MemberService service = MemberService.getInstance();
         service.memberInsert(member);
         
         // Output View 페이지로 이동
         request.setAttribute("id", id);
         HttpUtil.forward(request, response, "/result/memberInsertOutput.jsp");
        
    }
}
