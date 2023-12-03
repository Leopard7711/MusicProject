package com.project.musicProject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.JDBCUtil;


public class MusicDAO {


   

    public List<MusicDTO> getAllMusic() {
    	
        List<MusicDTO> musicList = new ArrayList<>();
        String sql = "SELECT * FROM music_data"; // 데이터베이스에 맞는 SQL 쿼리

        try (
        		Connection conn=JDBCUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);
             ) {
        	try(ResultSet rs = pstmt.executeQuery();){
        		while (rs.next()) {
                MusicDTO music = new MusicDTO();
                music.setId(rs.getInt("id"));
                music.setTitle(rs.getString("title"));
                music.setArtist(rs.getString("artist"));
                music.setAlbum(rs.getString("album"));
                music.setGenre(rs.getString("genre"));
                music.setUrl(rs.getString("url"));

                musicList.add(music);
                
        		}
        	}
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return musicList;
    }
    

    public MusicDTO getMusicById(int id) {
    	
        String sql = "SELECT * FROM music_data WHERE id = ?";
        MusicDTO music = null;

        try (
        		Connection conn=JDBCUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);
        				) {
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    music = new MusicDTO();
                    music.setId(rs.getInt("id"));
                    music.setTitle(rs.getString("title"));
                    music.setArtist(rs.getString("artist"));
                    music.setAlbum(rs.getString("album"));
                    music.setGenre(rs.getString("genre"));
                    music.setUrl(rs.getString("url"));
                    
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return music;
    }
    
    public List<MusicDTO> searchMusic(String searchName, String searchType) {
    	
        List<MusicDTO> musicList = new ArrayList<>();
        

        String sql = "SELECT * FROM music_data WHERE " + searchType+ " LIKE ?";

        try (
        		Connection conn=JDBCUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setString(1, "%" + searchName + "%");

            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    MusicDTO music = new MusicDTO();
                    music.setId(rs.getInt("id"));
                    music.setTitle(rs.getString("title"));
                    music.setArtist(rs.getString("artist"));
                    music.setAlbum(rs.getString("album"));
                    music.setGenre(rs.getString("genre"));
                    music.setUrl(rs.getString("url"));

                    musicList.add(music);
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return musicList;
    }
    
    
    
    public List<MusicDTO> getMusicByIds(List<Integer> ids) {
    	
        List<MusicDTO> musicList = new ArrayList<>();
        if (ids == null || ids.isEmpty()) {
            return musicList;
        }

        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = "SELECT * FROM music_data WHERE id IN (" + inSql + ")";

        try ( Connection conn=JDBCUtil.getConnection();
        		PreparedStatement pstmt = conn.prepareStatement(sql);) {
            int index = 1;
            for (Integer id : ids) {
                pstmt.setInt(index++, id);
            }

            try (ResultSet rs = pstmt.executeQuery();) {
                while (rs.next()) {
                    MusicDTO music = new MusicDTO();
                    music.setId(rs.getInt("id"));
                    music.setTitle(rs.getString("title"));
                    music.setArtist(rs.getString("artist"));
                    music.setAlbum(rs.getString("album"));
                    music.setGenre(rs.getString("genre"));
                    music.setUrl(rs.getString("url"));
                    
                    musicList.add(music);
                    
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return musicList;
    }
    
}
