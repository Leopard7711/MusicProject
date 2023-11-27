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

@WebServlet("/UserMusicListInsertCon")
public class UserMusicListInsertCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserMusicListInsertCon() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String listName = request.getParameter("listName");
        int musicId = Integer.parseInt(request.getParameter("musicId"));

        UserMusicListDAO userMusicListDAO = new UserMusicListDAO();
      
        boolean success = userMusicListDAO.insertUserMusicList(userId, listName, musicId);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"success\": " + success + "}");
        
    }

   
}