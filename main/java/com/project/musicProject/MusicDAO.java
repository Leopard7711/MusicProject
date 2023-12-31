package com.project.musicProject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.JDBCUtil;


public class MusicDAO {
	private Connection conn; // 데이터베이스 연결 객체

   

    public List<MusicDTO> getAllMusic() {
    	conn=JDBCUtil.getConnection();
    	
        List<MusicDTO> musicList = new ArrayList<>();
        String sql = "SELECT * FROM music_data"; // 데이터베이스에 맞는 SQL 쿼리

        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        return musicList;
    }
    

    public MusicDTO getMusicById(int id) {
    	conn=JDBCUtil.getConnection();
        String sql = "SELECT * FROM music_data WHERE id = ?";
        MusicDTO music = null;

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
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
    
    public List<MusicDTO> searchMusic(String searchQuery, String searchType) {
    	conn=JDBCUtil.getConnection();
        List<MusicDTO> musicList = new ArrayList<>();
        String sql = "";

        switch (searchType) {
            case "title":
                sql = "SELECT * FROM music_data WHERE title LIKE ?";
                break;
            case "album":
                sql = "SELECT * FROM music_data WHERE album LIKE ?";
                break;
            case "artist":
                sql = "SELECT * FROM music_data WHERE artist LIKE ?";
                break;
            default:
                // 오류 처리 또는 기본 쿼리 설정
                return musicList;
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + searchQuery + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
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
    	conn=JDBCUtil.getConnection();
        List<MusicDTO> musicList = new ArrayList<>();
        if (ids == null || ids.isEmpty()) {
            return musicList;
        }

        String inSql = String.join(",", Collections.nCopies(ids.size(), "?"));
        String sql = "SELECT * FROM music_data WHERE id IN (" + inSql + ")";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (Integer id : ids) {
                pstmt.setInt(index++, id);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
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
