package com.dev.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dev.vo.MemberVO;

/**
 * 회원에 관한 데이터베이스 처리 기능을 전담하는 DAO 모델 객체.
 * @author seo
 *
 */
public class MemberDAO {
    
    private static MemberDAO dao = new MemberDAO();
    
    private MemberDAO() {}
    
    public static MemberDAO getInstance() {
        return dao;
    }

    /**
     * Oracle 연결하기.
     * @return
     */
    public Connection connect() {
        Connection conn = null;
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
        } catch (Exception e) {
            System.out.println("오류 발생 : " +  e);
        }
        return conn;
    }
    
    /**
     * 오라클 연결 종료하기 (ResultSet 먼저).
     * @param conn
     * @param ps
     * @param rs
     */
    public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.println("오류 발생 : " +  e);
            }
        }
        close(conn, ps);
    }
    
    /**
     * 메서드 오버로딩. rs를 종료 후...
     * @param conn
     * @param ps
     */
    public void close(Connection conn, PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception e) {
                System.out.println("오류 발생 :" + e);
            }
        }
        
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("오류 발생 : " + e);
            }
        }
    }
    
    public void memberInsert(MemberVO member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = connect();
            pstmt = conn.prepareStatement("insert into member values(?, ?, ?, ?)");
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPasswd());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getMail());
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("오류 발생 : " + e);
        } finally {
            close(conn, pstmt);
        }
    }

    public MemberVO memberSearch(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        MemberVO member = null;
        
        try {
            conn = connect();
            pstmt = conn.prepareStatement("select * from member where id=?");
            
            pstmt.setString(1, id);
            
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
            // 보통은 while 문인데 여기서는 if문? 값을 한 개만 추출?
                member = new MemberVO();
                
                member.setId(rs.getString(1));
                member.setPasswd(rs.getString(2));
                member.setName(rs.getString(3));
                member.setMail(rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("오류 발생 : " + e);
        } finally {
            close(conn, pstmt, rs);
        }
        
        return member;
    } // memberSearch() 메서드 닫음.

    public void memberUpdate(MemberVO member) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = connect();
            pstmt = conn.prepareStatement("update member set passwd=?, name=?, mail=? where id=?");
            
            pstmt.setString(1, member.getPasswd());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getMail());
            pstmt.setString(4, member.getId());
            pstmt.executeUpdate();
            
        } catch (Exception e) {
            System.out.println("오류 발생 : " + e);
        } finally {
            close(conn, pstmt);
        }
        
    }

    public void memberDelete(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = connect();
            pstmt = conn.prepareStatement("delete from member where id=?");
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("오류 발생 : " + e);
        } finally {
            close(conn, pstmt);
        }
        
    }

    public ArrayList<MemberVO> memberList() {
        ArrayList<MemberVO> list = new ArrayList<MemberVO>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        MemberVO member = null;
        
        try {
            conn = connect();
            pstmt = conn.prepareStatement("select * from member");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                member = new MemberVO();
                member.setId(rs.getString(1));
                member.setPasswd(rs.getString(2));
                member.setName(rs.getString(3));
                member.setMail(rs.getString(4));
                list.add(member);
            }
        } catch (Exception e) {
            System.out.println("오류 발생 : " + e);
        } finally {
            close(conn, pstmt, rs);
        }
        return list;
    }
    
} // 클래스 닫음.
