package com.project.musicProject;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;



public class MemberDAO {
	
    public boolean login(String id, String password) {
    	
        
        boolean loginCon = false;
        String strQuery = "select id, password from user_account"
        		+ " where id = ? and password = ?";
        try (Connection conn  = JDBCUtil.getConnection();
        PreparedStatement pstmt= conn.prepareStatement(strQuery);){
            pstmt.setString(1, id);
            pstmt.setString(2, password);
            try(ResultSet rs =pstmt.executeQuery();){
            	loginCon = rs.next();
            }
           
            
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
        } finally {
        
        }
        return loginCon;
    }	
	
	
    public String memberInsert(MemberDTO mDTO) {
    	
        
        String strQuery = "select email from user_account"
            		+ " where email = ?";
        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(strQuery);){
                	
                
                	pstmt.setString(1, mDTO.getEmail());
                	try(ResultSet rs = pstmt.executeQuery();){
                		if (rs.next()) {

                        return "email_exists";
                		}
                	}
                }catch(Exception ex) {
                	return "fail";
                }
                
    	strQuery = "select id from user_account"
        		+ " where id = ?";
    	try (Connection conn = JDBCUtil.getConnection();
    	        PreparedStatement pstmt = conn.prepareStatement(strQuery);){
     
         	pstmt.setString(1, mDTO.getId());
        	try(ResultSet rs = pstmt.executeQuery();){
        		if (rs.next()) {
                    return "id_exists";
                }
        	}
    		
      
    	}catch(Exception ex) {
        	return "fail";
        }
    	
    	strQuery = "insert into user_account values(?,?,?,?)";
        try(Connection conn = JDBCUtil.getConnection();
    	        PreparedStatement pstmt = conn.prepareStatement(strQuery);){
        	pstmt.setString(1, mDTO.getEmail());
        	pstmt.setString(2, mDTO.getId());
            pstmt.setString(3, mDTO.getPassword());
            java.util.Date currentDate = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(currentDate.getTime());
            pstmt.setTimestamp(4, timestamp);

            pstmt.executeUpdate();
     
            return "success";
        }catch(Exception ex) {
        	return "fail";
        }
        
        	
        
    }		
    public MemberDTO memberFind(String id) {
    	
    	String strQuery = "select * from user_account where id = ?";
        
        boolean loginCon = false;
        try(Connection conn  = JDBCUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(strQuery);)	 {
           	
            pstmt.setString(1, id);
            
            MemberDTO dto = new MemberDTO();
            try(ResultSet rs = pstmt.executeQuery();){
            	
            	
            	if(rs.next()) {
            		dto.setEmail( rs.getString("email"));
	                dto.setId( rs.getString("id"));
	                dto.setPassword( rs.getString("password"));
	                dto.setDatetime(rs.getTimestamp("datetime"));
            	}
                
            }
            
            
            return dto;
        } catch (Exception ex) {
            System.out.println("Exception" + ex);
            return null;
        }
        
    }	
    
    
    public List<MemberDTO> memberFindList() {
        List<MemberDTO> members = new ArrayList<>();
        String strQuery = "SELECT * FROM user_account";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(strQuery);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                MemberDTO dto = new MemberDTO();
                dto.setId(rs.getString("id"));
                dto.setEmail(rs.getString("email"));
                dto.setPassword(rs.getString("password")); 
                dto.setDatetime(rs.getTimestamp("datetime"));
                members.add(dto);
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex);
            
        }

        return members;
    }
    public boolean changePassword(String id, String newPassword) {
        
        String updateQuery = "UPDATE user_account SET password = ? WHERE id = ?";
        try (Connection conn  = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(updateQuery);
        		){
            
            pstmt.setString(1, newPassword);
            pstmt.setString(2, id);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        } 
    }
    
    public boolean deleteMember(String id) {
        
    	String deleteQuery = "DELETE FROM user_account WHERE id = ?";
        try (Connection conn = JDBCUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(deleteQuery);){
           
            pstmt.setString(1, id);

            int affectedRows = pstmt.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e);
            return false;
        } 
    }

}
