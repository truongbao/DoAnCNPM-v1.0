package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import library.LibraryConstant;
import model.bean.DeTai;
import model.bean.QuaTrinhThucHien;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.QuaTrinhThucHienDAO;
import model.dao.UserDAO;

public class PublicDangKyNghiemThuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicDangKyNghiemThuController() {
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
	    
	    int idDeTai = Integer.parseInt(request.getParameter("did"));
	    
        DeTai objDeTaiByIdDeTaiDK = detaiDAO.getObjectDeTaiByIdDeTaiDK(idDeTai);
	    
	    request.setAttribute("objDeTaiByIdDeTaiDK", objDeTaiByIdDeTaiDK);
	    
	    //lay danh sach linh vuc nghien cuu
	    request.setAttribute("listLinhVucNC", detaiDAO.getListLinhVucNC());
	    
	    request.setAttribute("listCapDeTai", detaiDAO.getListCapDeTai());
	    
	    
	    //lấy thông tin đối tượng sobjUserPublic 
	    User objUser = null;
		HttpSession session = request.getSession();
		
        if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        }
        
        request.setAttribute("objUser", objUser);
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/dangky_nghiemthu.jsp");
         rd.forward(request, response);
         
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    //kiểm tra đã đăng nhập ở public chưa
		if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		}
		
		 DetaiDAO detaiDAO = new DetaiDAO();
		 QuaTrinhThucHienDAO thucHienDAO = new QuaTrinhThucHienDAO();
		
		    //Cap nhat doi tuong de tai ung vs idDeTai
			
			User objUser = null;
			HttpSession session = request.getSession();
			if(session.getAttribute("sobjUserPublic")!=null){
	            objUser = (User)session.getAttribute("sobjUserPublic");
	        }
			
			int idDeTai = Integer.parseInt(request.getParameter("did")); //luu 
			
			String noiDung = request.getParameter("noiDung");
			String chuDe = "Đăng ký nghiệm thu";
			
			int idGiangVien = objUser.getIdUser(); //luu vào idGiangVien trong bảng quatrinhthuchien
			
			
			QuaTrinhThucHien objQTTH = new QuaTrinhThucHien(0, idDeTai, LibraryConstant.DangChoXetNghiemThu, null, chuDe, noiDung);
			

			if(thucHienDAO.addItem(objQTTH) >  0){
			   //dang ky thanh cong
				
			   detaiDAO.updateToTrangThai(LibraryConstant.DangChoXetNghiemThu, idDeTai);
				
			   response.sendRedirect(request.getContextPath()+"/quanly-detai?msg=1");
			   return;
			}else{
        		//dang ky that bai		 
			   response.sendRedirect(request.getContextPath()+"/quanly-detai?msg=0");
			   return;
			}

		
        
	}
	
	
	

}
