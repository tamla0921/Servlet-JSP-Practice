package com.dev.vo;

/**
 * 회원 정보를 저장할 목적으로 만든 Value Object.
 * 회원 정보 생성, 검색 등을 할 때 정보를 MemberVO 객체에 담아 데이터베이스, 출력 뷰 페이지로 전달.
 * 데이터베이스의 컬럼 이름과 동일하게 작성.
 * @author seo
 *
 */
public class MemberVO {
    
    private String id;
    private String passwd;
    private String name;
    private String mail;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPasswd() {
        return passwd;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    
}
