package controller;

import java.io.File;
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
public class PublicDownloadTMToEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public PublicDownloadTMToEditController() {
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
		
	    //file 
        final String filepath = request.getServletContext().getRealPath("files_thuyetminh"); //duong dan den thu muc chua hinhanh
      
        File dirFile = new File(filepath);
        if(!dirFile.exists()){
      	  dirFile.mkdir(); //neu file chua ton tai thi tao file
        }
        
        System.out.println("đường dẫn thu mục gốc : "+filepath);
        
		String filename = "Bieu_mau_tao_thuyet_minh.docx";
		
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename=\""+ filename + "\"");
 
		FileInputStream fileInputStream = new FileInputStream(filepath +File.separator+ filename);
		
		System.out.println("ket qua : "+filepath +File.separator+ filename);
 
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
