package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;


@MultipartConfig
public class PublicDownloadTMController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public PublicDownloadTMController() {
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
		
		
		String filename = "Bieu_mau_tao_thuyet_minh.docx";
		   
	    final String filepath = request.getServletContext().getRealPath("files_bieumau"); //duong dan den thu muc chua file
	   
	    File dirFile = new File(filepath);
        if(!dirFile.exists()){
      	  dirFile.mkdir(); //neu file chua ton tai thi tao file
        }

	    
	    File[] children = dirFile.listFiles();
	    String nameFileInForder = null;
	    
        for (File file : children) {
        	nameFileInForder = file.getName(); //lấy tên file trong thư mục 
        }
		   
		   
		if( filename.equals(nameFileInForder) ){ 
			
			BufferedInputStream buf=null;
		    ServletOutputStream myOut=null;

		try{

		     myOut = response.getOutputStream();
		     File myfile = new File(filepath+ File.separator+ filename);
		     
		     response.setHeader("Content-Disposition", "attachment; filename=\""+ filename + "\"");

		     response.setContentLength( (int) myfile.length( ) );
		     
		     FileInputStream input = new FileInputStream(myfile);
		     buf = new BufferedInputStream(input);
		     int readBytes = 0;

		     //read from the file; write to the ServletOutputStream
		     while((readBytes = buf.read( )) != -1)
		       myOut.write(readBytes);

		} catch (IOException ioe){
		     throw new ServletException(ioe.getMessage( ));
		} finally {
		   //close the input/output streams
		   if (myOut != null)
		      myOut.close( );
		   if (buf != null)
		      buf.close( );
	    }
		
		
		}else{
			PrintWriter out = response.getWriter();
			out.print("<h1 style='text-align : center;color: red;margin-top : 250px;'>File không tồn tại</h1>");
		}
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
	}
	
	
	
	

}
