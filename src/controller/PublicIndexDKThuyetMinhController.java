package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import library.ConvertStringLibrary;
import library.LibraryAuth;
import library.LibraryConstant;
import library.RenameFileLibrary;
import model.bean.BieuMau;
import model.bean.DeTai;
import model.bean.User;
import model.dao.BieuMauDAO;
import model.dao.DetaiDAO;
import model.dao.ThoiGianDKDAO;
import model.dao.UserDAO;


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
		
		UserDAO userDAO = new UserDAO();
	    DetaiDAO detaiDAO =new DetaiDAO();
		
		//kiem tra có trong thoi gian tạo thuyết minh hay không
		ThoiGianDKDAO thoiGianDKDAO = new ThoiGianDKDAO();
		Date thoiGianKetThucThuyetMinh = thoiGianDKDAO.getItem(2).getThoiGianKetThuc();
		Date now = new Date();
		if (now.after(thoiGianKetThucThuyetMinh)) {
			
			 //detaiDAO.changeTrangThaiDangChoDuyetThuyetMinh();
			
			 //lấy thông tin đối tượng sobjUserPublic 
		     User objUser = null;
			 HttpSession session = request.getSession();
			
	         if(session.getAttribute("sobjUserPublic")!=null){
	            objUser = (User)session.getAttribute("sobjUserPublic");
	         }
	        
	        //phân trang
	         int current_page = 1;		
			 int row_count = LibraryConstant.ROW_COUNT; //5 tin trên 1 page
			
			 
			 //tong so danh muc
			 int sumDeTaiDK = detaiDAO.countDeTaiDKPublic(objUser.getIdUser());
			 
			 //tong so trang
			int sumPage = (int) Math.ceil((float)sumDeTaiDK / row_count) ;
			request.setAttribute("sumPage", sumPage);
			
			//lấy số trang hiện tại
			if (request.getParameter("page") != null){
				current_page = Integer.parseInt(request.getParameter("page"));
			}
			 
			// tính offset
			int offset = (current_page - 1) * row_count;
			request.setAttribute("current_page", current_page);
			

			 //lấy danh sach đề tai do user đang login đăng ký
			 request.setAttribute("listDeTaiDK", detaiDAO.getListDeTaiDK( objUser.getIdUser(), offset,row_count ) );
			
			 request.setAttribute("tb2", "Hiện không phải thời gian tạo thuyết minh !");
			
			 RequestDispatcher rd = request.getRequestDispatcher("/list_register_detai.jsp");
	         rd.forward(request, response);   
	         
	         
		}else{
			
			 idDeTai = Integer.parseInt(request.getParameter("did"));
			    
			 DeTai objDeTaiByIdDeTaiDK = detaiDAO.getObjectDeTaiByIdDeTaiDK(idDeTai);
			    
			 request.setAttribute("objDeTaiByIdDeTaiDK", objDeTaiByIdDeTaiDK);
			    
			    
			    
		     DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
				
			 request.setAttribute("objDeTai", objDeTai);
				
			 RequestDispatcher rd = request.getRequestDispatcher("/dangky_thuyetminh.jsp");
		     rd.forward(request, response);
			
		}
		
		  
         
	}

	 //Xử lý upload file tạo thuyết minh đề tài
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
			  request.setCharacterEncoding("UTF-8");
			  response.setCharacterEncoding("UTF-8");
			  response.setContentType("text/html");
		
			  DetaiDAO detaiDAO = new DetaiDAO();
			  BieuMauDAO bieuMauDAO =new BieuMauDAO();
			  
			  int idDeTai = Integer.parseInt(request.getParameter("did"));
			  
			  //kiểm tra xem có bị trùng file thuyết minh khi lỡ up nhiều lần ko ?
			  //lấy ra đối tượng ứng vs idDeTai này và maBieuMau = BM_TM , Nếu có -> ko up nữa...còn nếu ko thì mới up
			  BieuMau objBieuMau = bieuMauDAO.getObjectBieuMau(LibraryConstant.BieuMauThuyetMinh,idDeTai);
			  
			  if(objBieuMau == null){
			  
			  //xu ly upload file
		      String linkUpload = ""; //ko up load
		        
		      //file 
		      final String path = request.getServletContext().getRealPath("files_thuyetminh"); //duong dan den thu muc chua hinhanh
		      
		      File dirFile = new File(path);
		      if(!dirFile.exists()){
		      	dirFile.mkdir(); //neu file chua ton tai thi tao file
		      }
		      
		      
		      System.out.println(path);

		      final Part filePart = request.getPart("upfiles");
		      final String fileName = RenameFileLibrary.getName(filePart);//lay ra ten anh trong đường dẫn
		      String SluglinkUpload = null;

		      if(!"".equals(fileName)){ // có chọn file
		      	OutputStream out = null;
		      	InputStream filecontent = null;
		      	linkUpload = RenameFileLibrary.renameFile(fileName) ;
		      	
		      	//slug linkUpload : convert tieng viet ko dau
		         SluglinkUpload = ConvertStringLibrary.SlugFileUpload(linkUpload);
		         
		      	try {
		      		out = new FileOutputStream(new File(path + File.separator + SluglinkUpload));
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
		      }else{
		    	  
		    	  response.sendRedirect(request.getContextPath()+"/dangky-thuyetminh?msg=3&did="+this.idDeTai);
				  return;
		    	  
		      }
				
		        BieuMau objBM  =new BieuMau(0, idDeTai, LibraryConstant.BieuMauThuyetMinh, SluglinkUpload);
		        
			   if(bieuMauDAO.UploadFileThuyetMinh(objBM) > 0){
					//up thanh cong
				   //set lại trạng thái 
				    detaiDAO.updateToTrangThai(LibraryConstant.DangChoXetThuyetMinh, idDeTai);
				   
					response.sendRedirect(request.getContextPath()+"/list-register-detai?msg=3&did="+this.idDeTai);
					return;
			   }else{
					//that bai
				    response.sendRedirect(request.getContextPath()+"/list-register-detai?msg=4&did="+this.idDeTai);
				    return;
					
			   }	
			   
			   
			  }else{
				  response.sendRedirect(request.getContextPath()+"/dangky-thuyetminh?msg=2&did="+this.idDeTai);
				  return;
			  }
			   
			   
			   
			   
			   
				
				
	}
	
	
	
	

}
