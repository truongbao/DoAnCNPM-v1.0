package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

public class AdminDuyetDXNhanVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDuyetDXNhanVien() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
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
		 int capdt = 0;
		 String key = "";
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
 			System.out.println("Searh....1");
 			sumDeTai = detaiDAO.countListDeTaiSearchWith(key, khoa, LibraryConstant.DangChoXetCapTruong, capdt);
 		} else {
 			sumDeTai = detaiDAO.countListDeTaiWith(LibraryConstant.DangChoXetCapTruong);
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
 			
 			ArrayList<DeTai> listDeTaiNhanVien = detaiDAO.getListDeTaiWith(LibraryConstant.DangChoXetCapTruong, offset);
 	  
 			 request.setAttribute("listDeTaiNhanVien", listDeTaiNhanVien);
 		} else {
 			System.out.println("Searh....2");
 			ArrayList<DeTai> listDeTaiNhanVien = detaiDAO.getListDeTaiSearchWith(offset, key, khoa, LibraryConstant.DangChoXetCapTruong, capdt);
 
 			 request.setAttribute("listDeTaiNhanVien", listDeTaiNhanVien);
 		}
 		 
  		System.out.println("LOAD LAI TRANG");
 		RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv.jsp");
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
      	 		QuaTrinhThucHien qtth = new QuaTrinhThucHien(0,idDeTai, LibraryConstant.DangChoDuyetCapTruong, null, "", noidung);
      	 		qtthDAO.addItem(qtth);
      	 	}
 	 		DetaiDAO detaiDAO = new DetaiDAO();
 	 		if (request.getParameter("huy") != null) {
 	 			if (detaiDAO.updateToTrangThai(LibraryConstant.ChoHuy, idDeTai) != 0) {
 	 	 			System.out.println("Xet cho huy!");
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv?msg=1");
 	 				return; 
 	 	 		} else {
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv?msg=0");
 	 				return; 
 	 	 		}
 	 		} else if (request.getParameter("duyet") != null) {
 	 			if (detaiDAO.updateToTrangThai(LibraryConstant.DangChoDuyetCapTruong, idDeTai) != 0) {
 	 	 			System.out.println("Xet cho duyet de xuat!");
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv?msg=1");
 	 				return; 
 	 	 		} else {
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv?msg=0");
 	 				return; 
 	 	 		}
 	 		} else if (request.getParameter("chinhsua") != null) {
 	 			if (detaiDAO.updateToTrangThai(LibraryConstant.ChoDeNghiChinhSuaDeXuat, idDeTai) != 0) {
 	 	 			System.out.println("De nghi chinh sua de xuat!");
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv?msg=1");
 	 				return; 
 	 	 		} else {
 	 	 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/nhanvien/duyet_de_xuat_nv?msg=0");
 	 				return; 
 	 	 		}
 	 		}
 	 		
		}
	}

}
