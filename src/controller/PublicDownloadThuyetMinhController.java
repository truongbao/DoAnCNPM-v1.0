package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;


@MultipartConfig
public class PublicDownloadThuyetMinhController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private int idDeTai;
	
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
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
			  request.setCharacterEncoding("UTF-8");
			  response.setCharacterEncoding("UTF-8");
			  response.setContentType("text/html");
				
				
	}
	
	
	
	

}
