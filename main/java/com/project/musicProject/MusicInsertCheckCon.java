package com.project.musicProject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;


@WebServlet("/MusicInsertCheckCon")
public class MusicInsertCheckCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MusicInsertCheckCon() {
        super();
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject jsonResponse = new JSONObject();

        try {
        	
            String url = request.getParameter("url"); // 클라이언트로부터 URL 받기
            MusicDAO musicDao = new MusicDAO();
            List<MusicDTO> searchResults = musicDao.searchMusic(url, "url");
            boolean urlExists;
            if(searchResults.size()!=0) {
            	urlExists= true; 
            }
            else {
            	urlExists= false; 
            }
            
            
            jsonResponse.put("urlExists", urlExists);
        } catch (Exception e) {
            jsonResponse.put("error", "서버 오류 발생: " + e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    }

}
