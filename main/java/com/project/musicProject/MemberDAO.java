package com.project.musicProject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.JDBCUtil;



public class MemberDAO {
	
    public boolean login(String id, String password) {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginCon = false;
        try {
			conn = JDBCUtil.getConnection();
            String strQuery = "select id, password from user_account"
            		+ " where id = ? and password = ?";

            pstmt = conn.prepareStatement(strQuery);
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            loginCon = rs.next();
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        } finally {
        	JDBCUtil.close(rs, pstmt, conn);
        }
        return loginCon;
    }	
	
	
    public String memberInsert(MemberDTO mDTO) {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
        	conn = JDBCUtil.getConnection();
        	
        	
        	String strQuery = "select email from user_account"
            		+ " where email = ?";
        	pstmt = conn.prepareStatement(strQuery);
        	pstmt.setString(1, mDTO.getEmail());
        	rs = pstmt.executeQuery();
        	
        
        	if (rs.next()) {
                return "email_exists";
            }
            
 
        	pstmt.close();
        	rs.close();
         	
        	strQuery = "select id from user_account"
            		+ " where id = ?";
        	pstmt = conn.prepareStatement(strQuery);
         	pstmt.setString(1, mDTO.getId());
         	rs = pstmt.executeQuery();
         	

         	if (rs.next()) {
                return "id_exists";
            }
         		
         	pstmt.close();
         	rs.close();
         	
            strQuery = "insert into user_account values(?,?,?,?)";
            pstmt = conn.prepareStatement(strQuery);
            
            pstmt.setString(1, mDTO.getEmail());
        	pstmt.setString(2, mDTO.getId());
            pstmt.setString(3, mDTO.getPassword());
            
            java.util.Date currentDate = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDate.getTime());
            pstmt.setTimestamp(4, timestamp);
            
            
            pstmt.executeUpdate();
            System.out.println(mDTO.getEmail()+":"+mDTO.getId()+":"+mDTO.getPassword());
            
            
            pstmt.close();
            rs.close();
            return "success";
            

        } catch (Exception ex) {
            System.out.println("Exception" + ex);
            return "fail";
        } finally {
        	JDBCUtil.close(rs, pstmt, conn);
        }
        
    }		
    public MemberDTO memberFind(String id) {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean loginCon = false;
        try {
			conn = JDBCUtil.getConnection();
            String strQuery = "select * from user_account where id = ?";
            		

            pstmt = conn.prepareStatement(strQuery);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            loginCon = rs.next();
            
            MemberDTO dto = new MemberDTO();
            dto.setEmail( rs.getString("email"));
            dto.setId( rs.getString("id"));
            dto.setPassword( rs.getString("password"));
            dto.setDatetime(rs.getTimestamp("datetime"));
            
            
            return dto;
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
            return null;
        } finally {
        	JDBCUtil.close(rs, pstmt, conn);
        }
        
    }	
    public boolean changePassword(String id, String newPassword) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            conn = JDBCUtil.getConnection();
            String updateQuery = "UPDATE user_account SET password = ? WHERE id = ?";
            System.out.print(updateQuery);
            pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1, newPassword);
            pstmt.setString(2, id);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        } finally {
            JDBCUtil.close(null, pstmt, conn);
        }
    }
    
    public boolean deleteMember(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.getConnection();
            String deleteQuery = "DELETE FROM user_account WHERE id = ?";
            
            pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setString(1, id);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        } finally {
            JDBCUtil.close(null, pstmt, conn);
        }
    }

}
