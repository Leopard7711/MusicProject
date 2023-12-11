package com.project.musicProject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import org.json.*;

@WebServlet("/MusicSearchCon")
public class MusicSearchCon extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        try (BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
        }

        JSONObject jsonObj = new JSONObject(jsonBuilder.toString());
        String category = jsonObj.getString("category");
        String keyword = jsonObj.getString("keyword");       
        
        MusicDAO musicDao = new MusicDAO();
        List<MusicDTO> searchResults = musicDao.searchMusic(keyword, category);

        JSONArray jsonArray = new JSONArray();
        for (MusicDTO music : searchResults) {
            JSONObject json = new JSONObject();
            json.put("id", music.getId());
            json.put("title", music.getTitle());
            json.put("artist", music.getArtist());
            json.put("genre", music.getGenre());
            json.put("album", music.getAlbum());
            json.put("url", music.getUrl());
            jsonArray.put(json);
        }
        
       

        response.getWriter().write(jsonArray.toString());
    }
}