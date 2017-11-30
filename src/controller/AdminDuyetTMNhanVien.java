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
import model.bean.QuaTrinhThucHien;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.QuaTrinhThucHienDAO;
import model.dao.UserDAO;

public class AdminDuyetTMNhanVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDuyetTMNhanVien() {
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
      	HttpSession session = request.getSession();
        DetaiDAO detaiDAO = new DetaiDAO();
		User objUserAdmin = null;
		 if(session.getAttribute("nhanVienQLNCKHTruong")!=null){
			 objUserAdmin = (User)session.getAttribute("nhanVienQLNCKHTruong");
		 }
		 
		 int khoa = 0;
		 String key = "";
		 int capdt = 0;
		 if (request.getParameter("cancel") == null) {
			 if (request.getParameter("key") != null) {
				 key = request.getParameter("key");
			 }
			 if (request.getParameter("khoa") != null) {
				 khoa = Integer.parseInt(request.getParameter("khoa"));
			 }
			 if (request.getParameter("capdetai") != null) {
				 capdt = Integer.parseInt(request.getParameter("capdetai"));
				 System.out.println("CAP DE TAI: " + capdt);
			 }
		 }
		 //Xu ly chia trang
		 int row_count = LibraryConstant.ROW_COUNT;
 		 int current_page = 1;
 		 
 		//tong so de tai
 		 int sumDeTai = 0;
 		if (request.getParameter("search") != null){
 			sumDeTai = detaiDAO.countListDeTaiSearchWith(key, khoa, LibraryConstant.DangChoXetThuyetMinh, capdt);
 		} else {
 			sumDeTai = detaiDAO.countListDeTaiWith(LibraryConstant.DangChoXetThuyetMinh);
 		}
 		// tong so trang
 		 int sumPage = (int)Math.ceil((float)sumDeTai/row_count);
 		 request.setAttribute("sumPage", sumPage);
 		
 		//lấy số trang hiện tại
 		if (request.getParameter("page") != null){
 			current_page = Integer.parseInt(request.getParameter("page"));
 		}
  		 
 		// tính offset
 		int offset = (current_page - 1) * row_count;
 		request.setAttribute("current_page", current_page);
 		if (request.getParameter("search") == null){
 			ArrayList<DeTai> listDeTaiNhanVien = detaiDAO.getListDeTaiWith(LibraryConstant.DangChoXetThuyetMinh, offset);
 			 request.setAttribute("listDeTaiNhanVien", listDeTaiNhanVien);
 		} else {
 			ArrayList<DeTai> listDeTaiNhanVien = detaiDAO.getListDeTaiSearchWith(offset, key, khoa, LibraryConstant.DangChoXetThuyetMinh, capdt);
 			 request.setAttribute("listDeTaiNhanVien", listDeTaiNhanVien);
 		}
		 
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv.jsp");
         rd.forward(request, response);
         

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
         if (request.getParameter("did") != null) {
 	 		int idDeTai = Integer.parseInt(request.getParameter("did"));
 	 		if (request.getParameter("noidung") != null) {
   	 		String noidung = request.getParameter("noidung");
   	 		QuaTrinhThucHienDAO qtthDAO = new QuaTrinhThucHienDAO();
   	 		QuaTrinhThucHien qtth = new QuaTrinhThucHien(0,idDeTai, LibraryConstant.DangChoDuyetThuyetMinh, null, "", noidung);
   	 		qtthDAO.addItem(qtth);
   	 	}
	 		DetaiDAO detaiDAO = new DetaiDAO();
	 		
 	 		if (request.getParameter("huy") != null) {
 	 			if (detaiDAO.updateToTrangThai(LibraryConstant.ChoHuyThuyetMinh, idDeTai) != 0) {
 	 	 			System.out.println("Xet cho huy TM!");
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv?msg=1");
 	 				return; 
 	 	 		} else {
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv?msg=0");
 	 				return; 
 	 	 		}
 	 		} else if (request.getParameter("duyet") != null) {
 	 			if (detaiDAO.updateToTrangThai(LibraryConstant.DangChoDuyetThuyetMinh, idDeTai) != 0) {
 	 	 			System.out.println("Xet cho duyet TMt!");
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv?msg=1");
 	 				return; 
 	 	 		} else {
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv?msg=0");
 	 				return; 
 	 	 		}
 	 		} else if (request.getParameter("chinhsua") != null) {
 	 			if (detaiDAO.updateToTrangThai(LibraryConstant.ChoDeNghiChinhSuaThuyetMinh, idDeTai) != 0) {
 	 	 			System.out.println("De nghi chinh sua TM!");
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv?msg=1");
 	 				return; 
 	 	 		} else {
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_thuyet_minh_nv?msg=0");
 	 				return; 
 	 	 		}
 	 		}
		}
	}

}
