package com.dev.service;

import java.util.ArrayList;

import com.dev.dao.MemberDAO;
import com.dev.vo.MemberVO;

/**
 * 회원에 관한 모든 서비스 기능을 처리하는 모델 객체.
 * @author seo
 *
 */
public class MemberService {
    
    /**
     * Singleton(?), MemberService 객체를 한 개만 생성해서 사용하려는 디자인 패턴의 코드.
     */
    private static MemberService service = new MemberService();
    
    /**
     * MVC 디자인 패턴에 맞기 위해, 데이터베이스를 관리하는 MemberDAO 객체 생성.
     * 역시 singleton(?).
     */
    public MemberDAO dao = MemberDAO.getInstance();
    
    
    private MemberService() {}
    // priavate 생성자 -> 외부에서 생성자 접근할 수 없으므로 외부에서 객체를 생성할 수 없음.
    
    /**
     * getInstance 메서드는 public static으로!!
     * MemberService 객체가 필요할 때는 getInstance() 메서드를 사용하여 이미 만들어진 객체를 추출하여 사용.
     * @return
     */
    public static MemberService getInstance() {
        return service;
    }
    
    public void memberInsert(MemberVO member) {
        dao.memberInsert(member);
    }

    public MemberVO memberSearch(String id) {
        MemberVO member = dao.memberSearch(id);
        
        return member;
    }
    
    public void memberUpdate(MemberVO member) {
        dao.memberUpdate(member);
    }

    public void memberDelete(String id) {
        dao.memberDelete(id);
    }

    public ArrayList<MemberVO> memberList() {
        ArrayList<MemberVO> list = dao.memberList();
        return list;
    }
}
