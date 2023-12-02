package com.project.musicProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import common.JDBCUtil;

public class UserMusicListDAO {
   

    
    public boolean insertUserMusicList(String userId,String listName, int musicId) {
        
        
        if(searchUserMusicList(userId,listName,musicId)) {
        	return false;
        }
        
        String sql = "INSERT INTO user_music_list (userId, listName, musicId) VALUES (?, ?, ?)";

        try (
        		Connection conn = JDBCUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);
        		) {
            pstmt.setString(1, userId);
            pstmt.setString(2, listName);
            pstmt.setInt(3, musicId);
            pstmt.executeUpdate();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public List<MusicDTO> getUserMusicList(String userId,String listName) {
        
        List<Integer> musicIds = new ArrayList<>();
        
        String sql = "SELECT musicId FROM user_music_list WHERE userId = ? and listName = ?";

        try (
        		Connection conn = JDBCUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);
        		) {
            pstmt.setString(1, userId);
            pstmt.setString(2, listName);
            
            try(ResultSet rs = pstmt.executeQuery()){
            	while (rs.next()) {
                musicIds.add(rs.getInt("musicId"));
            	}
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        MusicDAO musicDao = new MusicDAO();
        return musicDao.getMusicByIds(musicIds);
    }
    
    public boolean searchUserMusicList(String userId,String listName,int musicId) {
        
        
        String sql = "SELECT musicId FROM user_music_list WHERE userId = ? and listName = ? and musicId = ?";
        
        
        try (
        		Connection conn = JDBCUtil.getConnection();        		
        		PreparedStatement pstmt = conn.prepareStatement(sql);
        		 
        		) {
            pstmt.setString(1, userId);
            pstmt.setString(2, listName);
            pstmt.setInt(3, musicId);
           
            try(ResultSet rs=pstmt.executeQuery(); ){
            	if(rs.next()) {
            	
            	return true;
	            }else {
	            	
	                return false;
	            }
            
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<String> getUserListNames(String userId) {
        
        List<String> listNames = new ArrayList<>();
        String sql = "SELECT DISTINCT listName FROM user_music_list WHERE userId = ? order by listName DESC";
        try (
        		Connection conn = JDBCUtil.getConnection();
		        PreparedStatement pstmt = conn.prepareStatement(sql);
		              		
        		){
        	pstmt.setString(1, userId); 
            try(ResultSet rs = pstmt.executeQuery(); ){
            	

	            while (rs.next()) {
	                listNames.add(rs.getString("listName"));
	            }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } 

        return listNames;
    }
    public boolean deleteUserMusicFromList(String userId, String listName, int musicId) {
        
        String sql = "DELETE FROM user_music_list WHERE userId = ? AND listName = ? AND musicId = ?";
        try(
        		Connection conn = JDBCUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);    		
        		) {
            

            pstmt.setString(1, userId);
            pstmt.setString(2, listName);
            pstmt.setInt(3, musicId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }
    
    public boolean clearUserMusicList(String userId) {
       
    
    	String sql = "DELETE FROM user_music_list WHERE userId = ?";
        try(Connection conn= JDBCUtil.getConnection(); 
        		PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, userId);
    

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }
}