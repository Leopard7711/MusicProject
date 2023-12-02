package com.project.musicProject;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import javax.servlet.*;
import javax.servlet.http.*;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

@WebServlet("/MusicStreamingCon")
public class MusicStreamingCon extends HttpServlet {
   
    
	private static final long serialVersionUID = 1L;

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	        int musicId = Integer.parseInt(request.getParameter("id"));
		 	
	        MusicDAO musicDao = new MusicDAO();
	       
	        MusicDTO music = musicDao.getMusicById(musicId);

	        if (music != null) {
	        	System.out.print(request.getContextPath());
	           
	            String streamingUrl = "music/mp3/" + music.getUrl() + ".mp3";
	            
	            
	            JSONObject musicJson = new JSONObject();
	            musicJson.put("id", music.getId());
	            musicJson.put("title", music.getTitle());
	            musicJson.put("artist", music.getArtist());
	            musicJson.put("album", music.getAlbum());
	            musicJson.put("genre", music.getGenre());
	            musicJson.put("url", music.getUrl());
	            
	            musicJson.put("streamingUrl", streamingUrl); 

	            
	            response.setContentType("application/json");
	            response.setCharacterEncoding("UTF-8");
	            response.getWriter().write(musicJson.toString());
	        } else {
	            
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            response.getWriter().write("음악을 찾을 수 없습니다.");
	        }
	    }
}