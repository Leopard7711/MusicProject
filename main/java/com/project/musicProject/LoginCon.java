package com.project.musicProject;

import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.json.JSONObject;

import common.JDBCUtil;


@WebServlet("/LoginCon")
public class LoginCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LoginCon() {
        super();
        
    }

	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // JSON 데이터 읽기
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }
        
        // JSON 파싱
        String json = jsonBuffer.toString();
        JSONObject jsonObj = new JSONObject(json);
        String id = jsonObj.getString("id");
        String password = jsonObj.getString("password");

        
        
        
        boolean result;
        try {
        	MemberDAO dao = new MemberDAO();
        	
            result = dao.login(id, password);
            jsonObj=new JSONObject(json);
            if (result) {
            	jsonObj.put("status","success");
            	MemberDTO dto= dao.memberFind(id);
            	HttpSession session = request.getSession();
                session.setAttribute("MemberDTO",dto);
            	
            } else {
            	jsonObj.put("status", "fail");
            }
           
            
           
            
            PrintWriter out = response.getWriter();
            out.print(jsonObj);
            out.flush();
            out.close();
            
        } catch (Exception e) {
           
            e.printStackTrace();
        } finally {
            
        }
    }
	
	
	
}
