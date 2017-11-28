package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import library.LibraryConstant;
import model.bean.DeTai;
import model.bean.ThanhVien;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class AdminDetailDuyetNghiemThuNhanVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDetailDuyetNghiemThuNhanVien() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
      //kiểm tra đã đăng nhập chưa
      	if(  LibraryAuth.CheckNhanVienTruong(request, response)==false){
      		return;
      	}
      	
        DetaiDAO detaiDAO = new DetaiDAO();
		  
	    int idDeTai = Integer.parseInt(request.getParameter("did"));
	    
	    //tu idDeTai => danh sach thanh vien lam de tai nay (o bang thanh vien)
	    //send qua jsp
	    
	    	    
		DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
		request.setAttribute("objDeTai", objDeTai);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldetai/nhanvien/detail_duyet_nt_nv.jsp");
         rd.forward(request, response);
         

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
       //kiểm tra đã đăng nhập chưa
       	if(  LibraryAuth.CheckNhanVienTruong(request, response)==false){
       		return;
       	}
       	
         DetaiDAO detaiDAO = new DetaiDAO();
         int idDeTai = Integer.parseInt(request.getParameter("did"));
 		if(request.getParameter("update") != null){
 			
 			float score = Float.parseFloat(request.getParameter("score"));
 	 	    String content = request.getParameter("content");
 	 	    String xepLoai = LibraryConstant.getXepLoai(score);
 	 	   
 	 		if (detaiDAO.updateKQNghiemThu(idDeTai, content, score, xepLoai)>0){
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_nv?type=load&msg=1");
 	 		}else{
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_nv?type=load&msg=0");
 	 		}
 		}else if(request.getParameter("confirm") != null){
 			if (detaiDAO.updateToTrangThai(LibraryConstant.DaHoanThanh, idDeTai) > 0){
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_ad?type=load&msg=1");
 	 		}else{
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_ad?type=load&msg=0");
 	 		}
 		}else if(request.getParameter("cancel") != null){
 			if (detaiDAO.updateToTrangThai(LibraryConstant.DangChoXetNghiemThu, idDeTai) > 0){
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_ad?type=load&msg=1");
 	 		}else{
 	 			response.sendRedirect(request.getContextPath()+"/admin/qldetai/duyet_nghiem_thu_ad?type=load&msg=0");
 	 		}
 		}
 	   
 	    
          
	}

}
