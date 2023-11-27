package com.project.musicProject;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/UserMusicListGetCon")
public class UserMusicListGetCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserMusicListGetCon() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String listName = request.getParameter("listName");

        UserMusicListDAO userMusicListDao = new UserMusicListDAO();
        List<MusicDTO> musicList = userMusicListDao.getUserMusicList(userId, listName);

        JSONArray musicArray = new JSONArray();
        for (MusicDTO music : musicList) {
            JSONObject musicJson = new JSONObject();
            musicJson.put("id", music.getId());
            musicJson.put("title", music.getTitle());
            musicJson.put("artist", music.getArtist());
            musicJson.put("album", music.getAlbum());
            musicJson.put("url", music.getUrl());
            
            musicArray.put(musicJson);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(musicArray.toString());
    }
}