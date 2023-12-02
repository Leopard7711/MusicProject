package com.project.musicProject;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

@WebServlet("/UserMusicListSearchCon")
public class UserMusicListSearchCon extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserMusicListSearchCon() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        
        UserMusicListDAO userMusicListDao = new UserMusicListDAO();
        List<String> listNames = userMusicListDao.getUserListNames(userId);

        JSONArray jsonArray = new JSONArray();
        for (String listName : listNames) {
            jsonArray.put(listName);
            
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonArray.toString());
    }
}