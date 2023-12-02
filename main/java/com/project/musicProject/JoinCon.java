package com.project.musicProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

@WebServlet("/JoinCon")
public class JoinCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public JoinCon() {
        super();
        
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

     
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }
        
        
        String json = jsonBuffer.toString();
        JSONObject jsonObj = new JSONObject(json);
        String email = jsonObj.getString("email");
        String id = jsonObj.getString("id");
        String password = jsonObj.getString("password");
        MemberDTO dto = new MemberDTO();
        dto.setEmail(email);
        dto.setId(id);
        dto.setPassword(password);
        
        String result;
        try {
        	MemberDAO dao = new MemberDAO();
        	
            result = dao.memberInsert(dto);
            jsonObj=new JSONObject(json);
            
            switch(result){
            	case "success":
            		jsonObj.put("status","success");
            		break;
            	case "email_exists":
            		jsonObj.put("status","email_exists");
            		break;
            	case "id_exists":
            		jsonObj.put("status","id_exists");
            		break;
            	default:
            		jsonObj.put("status", "fail");
            		break;
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
