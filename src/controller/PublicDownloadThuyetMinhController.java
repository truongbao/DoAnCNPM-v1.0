package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;


@MultipartConfig
public class PublicDownloadThuyetMinhController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public PublicDownloadThuyetMinhController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    //kiểm tra đã đăng nhập ở public chưa
		if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String filename = "demo11.txt";
		String filepath = "D:\\";
		
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\""+ filename + "\"");
 
		 /*use inline if you want to view the content in browser, helpful for
		 pdf file
		 response.setHeader("Content-Disposition","inline; filename=\"" +
		 filename + "\"");*/
		
		FileInputStream fileInputStream = new FileInputStream(filepath
				+ filename);
 
		int i;
		while ((i = fileInputStream.read()) != -1) {
			out.write(i);
		}
		fileInputStream.close();
		out.close();
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
	}
	
	
	
	

}
