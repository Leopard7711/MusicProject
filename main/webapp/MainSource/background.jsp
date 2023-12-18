<%@ page import="java.io.File" %>
<%@ page contentType="text/plain" %>
<%
    String directoryPath = application.getRealPath("/MainSource/img/background/");
	System.out.println(directoryPath);
    File directory = new File(directoryPath);
    File[] files = directory.listFiles();

    for (File file : files) {
        if (file.isFile()) {
            out.println(file.getName());
        }
    }
%>