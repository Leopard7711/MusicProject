package com.project.musicProject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONObject;

@WebServlet("/MemberImageInsertCon")
@MultipartConfig
public class MemberImageInsertCon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MemberImageInsertCon() {
        super();
        
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        MemberDTO user = (MemberDTO) session.getAttribute("MemberDTO");

        JSONObject jsonResponse = new JSONObject();

        if (user == null) {
            jsonResponse.put("uploadStatus", "fail");
            jsonResponse.put("message", "User not logged in");
            response.getWriter().write(jsonResponse.toString());
            return;
        }

        try {
            Part filePart = request.getPart("photo");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            
            if (!fileName.endsWith(".jpg")) {
                jsonResponse.put("uploadStatus", "fail");
                jsonResponse.put("message", "Invalid file type");
            } else {
                String newFileName = user.getId() + ".jpg";
                
               // 웹 애플리케이션의 컨텍스트 루트 경로
                
                String uploadPath = getServletContext().getRealPath("/") ;
               
                uploadPath=uploadPath+ "MainSource"+File.separator+"img"+File.separator+"user_image";
                
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                	uploadDir.mkdir();
                }
                System.out.println(uploadPath + File.separator + newFileName);
                filePart.write(uploadPath + File.separator + newFileName);

                jsonResponse.put("uploadStatus", "success");
                jsonResponse.put("message", "File uploaded successfully");
            }
        } catch (Exception e) {
            jsonResponse.put("uploadStatus", "fail");
            jsonResponse.put("message", "Upload failed: " + e.getMessage());
        }

        response.getWriter().write(jsonResponse.toString());
    
    }

}
