package com.project.musicProject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import common.JDBCUtil;

public class UserMusicListDAO {
    private Connection conn;

    // 사용자 음악 목록에 음악 추가
    public boolean insertUserMusicList(String userId,String listName, int musicId) {
        conn = JDBCUtil.getConnection();
        
        if(serchUserMusicList(userId,listName,musicId)) {
        	return false;
        }
        
        String sql = "INSERT INTO user_music_list (userId, listName, musicId) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
        conn = JDBCUtil.getConnection();
        List<Integer> musicIds = new ArrayList<>();
        
        String sql = "SELECT musicId FROM user_music_list WHERE userId = ? and listName = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, listName);
            
            try (ResultSet rs = pstmt.executeQuery()) {
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
    
    public boolean serchUserMusicList(String userId,String listName,int musicId) {
        conn = JDBCUtil.getConnection();
        
        String sql = "SELECT musicId FROM user_music_list WHERE userId = ? and listName = ? and musicId = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userId);
            pstmt.setString(2, listName);
            pstmt.setInt(3, musicId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()) {
                	return true;
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public List<String> getUserListNames(String userId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<String> listNames = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT DISTINCT listName FROM user_music_list WHERE userId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId); // MemberDTO에서 userId 가져오기

            rs = pstmt.executeQuery();
            while (rs.next()) {
                listNames.add(rs.getString("listName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(rs, pstmt, conn);
        }

        return listNames;
    }
    public boolean deleteUserMusicFromList(String userId, String listName, int musicId) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM user_music_list WHERE userId = ? AND listName = ? AND musicId = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, listName);
            pstmt.setInt(3, musicId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // 삭제된 행이 있으면 true 반환
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.close(null, pstmt, conn);
        }
    }
}