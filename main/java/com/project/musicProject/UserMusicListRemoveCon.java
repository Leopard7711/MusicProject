package com.project.musicProject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/UserMusicListRemoveCon")
public class UserMusicListRemoveCon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String listName = request.getParameter("listName");
        int musicId = Integer.parseInt(request.getParameter("musicId"));

        
        UserMusicListDAO userMusicListDAO = new UserMusicListDAO();
        boolean result = userMusicListDAO.deleteUserMusicFromList(userId, listName, musicId);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if (result) {
            response.getWriter().write("{\"result\":\"success\"}");
        } else {
            response.getWriter().write("{\"result\":\"failure\"}");
        }
    }

   
}
