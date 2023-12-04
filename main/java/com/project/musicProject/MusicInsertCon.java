package com.project.musicProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;


@WebServlet("/MusicInsertCon")
@MultipartConfig
public class MusicInsertCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

      
        JSONObject jsonResponse = new JSONObject();


        try {
    	  String title = readValue(request.getPart("title"));
          String artist = readValue(request.getPart("artist"));
          String album = readValue(request.getPart("album"));
          String genre = readValue(request.getPart("genre"));
          String url = readValue(request.getPart("url")); // URL 파라미터 가져오기
            //System.out.println(title+artist+album+genre+url);
            MusicDTO music = new MusicDTO();
            music.setTitle(title);
            music.setArtist(artist);
            music.setAlbum(album);
            music.setGenre(genre);
            music.setUrl(url);
            
            MusicDAO musicDao = new MusicDAO();
            if (musicDao.insertMusic(music)) {
            	
            } else {
                jsonResponse.put("uploadStatus", "fail");
                jsonResponse.put("message", "Failed to save in database");
            }
            
            // 음악 파일 처리
            Part musicFilePart = request.getPart("music");
            String musicFilePath = getServletContext().getRealPath("/") + "MainSource" + File.separator + "music" + File.separator + "mp3" + File.separator + url + ".mp3";
            musicFilePart.write(musicFilePath);
            
            // 사진 파일 처리
            Part photoFilePart = request.getPart("photo");
            String photoFileName = Paths.get(photoFilePart.getSubmittedFileName()).getFileName().toString();
            if (!photoFileName.endsWith(".jpg")) {
                jsonResponse.put("uploadStatus", "fail");
                jsonResponse.put("message", "Invalid file type for photo");
            } else {
                String photoFilePath = getServletContext().getRealPath("/") + "MainSource" + File.separator + "music" + File.separator + "image" + File.separator + url + ".jpg";
                photoFilePart.write(photoFilePath);

            }
            
            jsonResponse.put("uploadStatus", "success");
            jsonResponse.put("message", "success");
            
        } catch (Exception e) {
            jsonResponse.put("uploadStatus", "fail");
            jsonResponse.put("message", "Upload failed: " + e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    }
    
	private String readValue(Part part) throws IOException {
	    return new BufferedReader(new InputStreamReader(part.getInputStream(), StandardCharsets.UTF_8))
	            .lines().collect(Collectors.joining("\n"));
	}

}
