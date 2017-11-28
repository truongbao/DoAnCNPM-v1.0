package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
import model.bean.ThongBao;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.QuaTrinhThucHienDAO;
import model.dao.ThongBaoDAO;

public class AdminDanhGiaDXKhoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDanhGiaDXKhoa() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
      //kiểm tra đã đăng nhập chưa
      	if(  LibraryAuth.CheckQuanLyKhoa(request, response)==false){
      		return;
      	}
      	
        DetaiDAO detaiDAO = new DetaiDAO();
		  
	    int idDeTai = Integer.parseInt(request.getParameter("did"));
	    
	    //tu idDeTai => danh sach thanh vien lam de tai nay (o bang thanh vien)
	    //send qua jsp
	    
	    	    
		DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
		
		request.setAttribute("objDeTai", objDeTai);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/khoa/danh_gia_dx_khoa.jsp");
         rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
       //kiểm tra đã đăng nhập chưa
       	if(  LibraryAuth.CheckQuanLyKhoa(request, response)==false){
       		return;
       	}
       	
         DetaiDAO detaiDAO = new DetaiDAO();
         QuaTrinhThucHienDAO qtthDAO = new QuaTrinhThucHienDAO();
         ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
         HttpSession session = request.getSession();
       	User objUser = new User();
       	if(session.getAttribute("quanLyNCKHKhoa") != null){
 			objUser = (User) session.getAttribute("quanLyNCKHKhoa");
 		}
         
         
 	    int idDeTai = Integer.parseInt(request.getParameter("did"));
 	    DeTai objDT = detaiDAO.getObjDeTai(idDeTai);
 	    if( request.getParameter("noidung") != ""){
 	    	String noidung = request.getParameter("noidung");
 	    	int result = detaiDAO.updateToTrangThai(LibraryConstant.KhoaDeXuatChinhSua, idDeTai);
 	    	if(result > 0){
 	    		QuaTrinhThucHien objQTTH = new QuaTrinhThucHien(0, objDT.getIdDeTai(), objDT.getTrangThai(), new Timestamp(new Date().getTime()), "Sửa", noidung);
 				qtthDAO.addItem(objQTTH);
 				ThongBao objTB = new ThongBao(0, objUser.getIdUser(), objDT.getIdUser(), noidung, new Timestamp(new Date().getTime()), objDT.getIdDeTai(), objDT.getTenDeTai(), objDT.getFullName(), objUser.getFullName(), 0);
 				thongBaoDAO.addItem(objTB);
 				response.sendRedirect(request.getContextPath()+"/admin/qldangkydetai/duyet-de-xuat-khoa?type=load&msg=1");
 	    	}else{
 	    		response.sendRedirect(request.getContextPath()+"/admin/qldangkydetai/duyet-de-xuat-khoa?type=load&msg=0");
 	    	}
 	    }
 	    
	}

}
