package com.project.musicProject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import common.JDBCUtil;

public class FriendshipDAO {

   
    public boolean addFriendRequest(String userId, String friendId) throws SQLException {
    	if (isFriendshipExists( userId, friendId) || isFriendshipExists(friendId, userId)) {
            return false; 
        }
    	
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO friendship (userId, friendId, requestDate) VALUES (?, ?, ?)");) {
            pstmt.setString(1, userId);
            pstmt.setString(2, friendId);
            pstmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));

            return pstmt.executeUpdate() > 0;
            
        }
    }

    
    public boolean removeFriendRequest(String userId, String friendId) throws SQLException {
    	
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "DELETE FROM friendship WHERE userId = ? and friendId = ?");) {
            pstmt.setString(1, userId);
            pstmt.setString(2, friendId);
            pstmt.executeUpdate();
            
            pstmt.setString(1, friendId);
            pstmt.setString(2, userId);
            pstmt.executeUpdate();
        }
        
        return true;
    }

    
    public List<FriendshipDTO> getFriendRequests(String id) throws SQLException {
        List<FriendshipDTO> requests = new ArrayList<>();
        try (Connection conn = JDBCUtil.getConnection();
        		PreparedStatement pstmt= conn.prepareStatement(
                        "SELECT * FROM friendship WHERE userId = ?");  ) {
        	
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FriendshipDTO dto = new FriendshipDTO();
                    dto.setUserId(rs.getString("userId"));
                    dto.setFriendId(rs.getString("friendId"));
                    dto.setDatetime(rs.getTimestamp("requestDate"));
                    requests.add(dto);
                }
           
            }
        }
        return requests;
    }
    
    public List<FriendshipDTO> searchFriendAndRequests(String id) throws SQLException {
        List<FriendshipDTO> requests = new ArrayList<>();
        try (Connection conn = JDBCUtil.getConnection();
        		PreparedStatement pstmt= conn.prepareStatement(
                        "SELECT * FROM friendship WHERE userId = ?");

        		) {
        
        	
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FriendshipDTO dto = new FriendshipDTO();
                    dto.setUserId(rs.getString("userId"));
                    dto.setFriendId(rs.getString("friendId"));
                    dto.setDatetime(rs.getTimestamp("requestDate"));
                    requests.add(dto);
                }
            }
            
        }
        try (Connection conn = JDBCUtil.getConnection();
        		PreparedStatement pstmt= conn.prepareStatement(
                        "SELECT * FROM friendship WHERE friendId = ?");) {  
        	pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    FriendshipDTO dto = new FriendshipDTO();
                    dto.setUserId(rs.getString("userId"));
                    dto.setFriendId(rs.getString("friendId"));
                    dto.setDatetime(rs.getTimestamp("requestDate"));
                    requests.add(dto);
                }
                
            }
        	
        } 
            
        return requests;
    }
    
    
    public boolean applyFriendship(String userId, String friendId) throws SQLException {

        if (isFriendshipExists( userId, friendId) && isFriendshipExists(friendId, userId)) {
            return false; 
        }
        
        
        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM friendship WHERE userId = ? and friendId = ?")) {
               pstmt.setString(1, userId);
               pstmt.setString(2, friendId);
               pstmt.executeUpdate();
               
               pstmt.setString(1, friendId);
               pstmt.setString(2, userId);
               pstmt.executeUpdate();
           }

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO friendship (userId, friendId, requestDate) VALUES (?, ?, NULL)")) {
            pstmt.setString(1, userId);
            pstmt.setString(2, friendId);
            int rowsAffected1 = pstmt.executeUpdate();

            pstmt.setString(1, friendId);
            pstmt.setString(2, userId);
            int rowsAffected2 = pstmt.executeUpdate();
            
            return rowsAffected1 > 0 && rowsAffected2 > 0;
        }
        
       
    }

    public boolean removeFriendship(String userId, String friendId) throws SQLException {

        try (Connection conn = JDBCUtil.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                    "DELETE FROM friendship WHERE userId = ? and friendId = ?")) {
               pstmt.setString(1, userId);
               pstmt.setString(2, friendId);
               pstmt.executeUpdate();
               
               pstmt.setString(1, friendId);
               pstmt.setString(2, userId);
               pstmt.executeUpdate();
  
               return true;
           }

        
       
    }
    
    
    private boolean isFriendshipExists(String userId, String friendId) throws SQLException {
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "SELECT COUNT(*) FROM friendship WHERE userId = ? AND friendId = ?")) {
            pstmt.setString(1, userId);
            pstmt.setString(2, friendId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
                
            }
        }
        return false;
    }
}