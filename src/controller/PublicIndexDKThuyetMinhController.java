package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.LibraryAuth;
import library.RenameFileLibrary;
import model.bean.BieuMau;
import model.bean.DeTai;
import model.dao.DetaiDAO;


@MultipartConfig
public class PublicIndexDKThuyetMinhController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private int idDeTai;
	
    public PublicIndexDKThuyetMinhController() {
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
		
		
		DetaiDAO detaiDAO = new DetaiDAO();
		  
	    idDeTai = Integer.parseInt(request.getParameter("did"));
	    
	    DeTai objDeTaiByIdDeTaiDK = detaiDAO.getObjectDeTaiByIdDeTaiDK(idDeTai);
	    
	    request.setAttribute("objDeTaiByIdDeTaiDK", objDeTaiByIdDeTaiDK);
	    
	    
	    
		DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
		
		request.setAttribute("objDeTai", objDeTai);
		
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/dangky_thuyetminh.jsp");
         rd.forward(request, response);
	}

	 //Xử lý upload file tạo thuyết minh đề tài
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
			  request.setCharacterEncoding("UTF-8");
			  response.setCharacterEncoding("UTF-8");
			  response.setContentType("text/html");
		
			  DetaiDAO detaiDAO = new DetaiDAO();
			  
			  int idDeTai = Integer.parseInt(request.getParameter("did"));
        
			  //xu ly hinh anh
		      String linkUpload = ""; //ko up load
		        
		      //file 
		      final String path = request.getServletContext().getRealPath("files_thuyetminh"); //duong dan den thu muc chua hinhanh
		      System.out.println("cukiko : "+path+"--het--");
		      
		      //D:\workspacejava12EE\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\DoAnCNPM_SE03\files_thuyetminh
		      
		      File dirFile = new File(path);
		      if(!dirFile.exists()){
		      	dirFile.mkdir(); //neu file chua ton tai thi tao file
		      }

		      final Part filePart = request.getPart("upfiles");
		      final String fileName = RenameFileLibrary.getName(filePart);//lay ra ten anh trong đường dẫn

		      if(!"".equals(fileName)){ // có chọn ảnh
		      	OutputStream out = null;
		      	InputStream filecontent = null;
		      	linkUpload = RenameFileLibrary.renameFile(fileName) ;
		      	
		      	System.out.println("ten file : "+linkUpload);
		      	
		      	try {
		      		out = new FileOutputStream(new File(path + File.separator + linkUpload));
		      		filecontent = filePart.getInputStream();

		      		int read = 0;
		      		final byte[] bytes = new byte[1024];

		      		while ((read = filecontent.read(bytes)) != -1) {
		      			out.write(bytes, 0, read);
		      		}
		      	} catch (FileNotFoundException fne) {
		      		fne.printStackTrace();
		      	} finally {
		      		if (out != null) {
		      			out.close();
		      		}
		      		if (filecontent != null) {
		      			filecontent.close();
		      		}
		      	}
		      }
				
		        /*DeTai objDeTai = new DeTai(idDeTai, "", "", 0,
							        		"", 0, "", 
							        		null, null, "", 
							        		0, "", "", 
							        		"", "", "", "",
							        		"", "", "", "", 
							        		0, "", 0,"",
							        		null, 0, linkUpload);*/
		        
		        
		        BieuMau objBM  =new BieuMau(0, idDeTai, 0, "", linkUpload);
		        
		        
		        
				 if(detaiDAO.UploadFileThuyetMinh(objBM) > 0){
					//up thanh cong
					response.sendRedirect(request.getContextPath()+"/dangky-thuyetminh?msg=1&did="+this.idDeTai);
					 return;
				}else{
					//that bai
				    response.sendRedirect(request.getContextPath()+"/dangky-thuyetminh?msg=0&did="+this.idDeTai);
				    return;
					
				}	
				
				
	}
	
	
	
	

}
