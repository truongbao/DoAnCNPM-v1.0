package controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.ConvertStringLibrary;
import library.LibraryAuth;
import library.LibraryConstant;
import model.bean.BieuMau;
import model.dao.BieuMauDAO;
import model.dao.DetaiDAO;


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
		
		//lay idDeTai 
		DetaiDAO detaiDAO =new DetaiDAO();
		BieuMauDAO bieuMauDAO = new BieuMauDAO();
		
		int idDeTai = Integer.parseInt(request.getParameter("did"));
		
		//kết hợp maBieuMau vs idDeTai lấy ra linkBieuMau của de tai đó 
		//viet method truyen vao 2 tham số maBieuMau vs idDeTai và ham này sẽ trả về Kiêu String chinh la linkBieuMau
		
		BieuMau objBieuMau = bieuMauDAO.getObjectBieuMau(LibraryConstant.BieuMauThuyetMinh,idDeTai);

		String filenameDB = "";
		if(objBieuMau != null){
			filenameDB = objBieuMau.getLinkBieuMau(); //filename trên DB
		}
	   
	    final String filepath = request.getServletContext().getRealPath("files_thuyetminh"); //duong dan den thu muc chua file
	   
	    File dirFile = new File(filepath);
        if(!dirFile.exists()){
      	  dirFile.mkdir(); //neu file chua ton tai thi tao file
        }
	    
	    File[] children = dirFile.listFiles();
	    String nameFileInForder = null;
	    
        for (File file : children) {
        	nameFileInForder = file.getName();
        }
		   
		if(!"".equalsIgnoreCase(filenameDB) && filenameDB.equals(nameFileInForder) ){ 
			
		BufferedInputStream buf=null;
		ServletOutputStream myOut=null;	
			
		try{

		     myOut = response.getOutputStream();
		     File myfile = new File(filepath+ File.separator+ filenameDB);
		     
		     response.setHeader("Content-Disposition", "attachment; filename=\""+ filenameDB + "\"");

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
