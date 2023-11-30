package com.project.musicProject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

@WebServlet("/MemberPasswordChangeCon")
@MultipartConfig
public class MemberPasswordChangeCon extends HttpServlet {
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        
//	        Part newPasswordPart = request.getPart("newPassword");
//	        String newPassword = null;
//	        
//	        if (newPasswordPart != null) {
//	            
//	            InputStream inputStream = newPasswordPart.getInputStream();
//	            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//	            byte[] buffer = new byte[1024];
//	            int bytesRead;
//	            while ((bytesRead = inputStream.read(buffer)) != -1) {
//	                outputStream.write(buffer, 0, bytesRead);
//	            }
//	            newPassword = outputStream.toString(StandardCharsets.UTF_8.name());
//	        }
	        String newPassword= request.getParameter("newPassword");
	        
	        
	        
	        HttpSession session = request.getSession();	       
	        MemberDTO user = (MemberDTO) session.getAttribute("MemberDTO");
	        System.out.print(newPassword);
	        JSONObject jsonResponse = new JSONObject();

	        if (user == null) {
	            jsonResponse.put("status", "fail");
	            jsonResponse.put("message", "User not logged in");
	        } else {
	            try {
	                MemberDAO dao = new MemberDAO();
	                boolean success = dao.changePassword(user.getId(), newPassword);

	                if (success) {
	                    jsonResponse.put("status", "success");
	                    jsonResponse.put("message", "Password changed successfully");
	                } else {
	                    jsonResponse.put("status", "fail");
	                    jsonResponse.put("message", "Password change failed");
	                }
	            } catch (Exception e) {
	                jsonResponse.put("status", "fail");
	                jsonResponse.put("message", "An error occurred: " + e.getMessage());
	            }
	        }

	        response.getWriter().write(jsonResponse.toString());
	 }
 }
