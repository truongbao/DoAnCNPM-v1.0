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
import model.bean.ThongBao;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.QuaTrinhThucHienDAO;
import model.dao.ThongBaoDAO;
import model.dao.UserDAO;

public class AdminDuyetTMAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDuyetTMAdmin() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
      //kiểm tra đã đăng nhập chưa
      	if(  LibraryAuth.CheckAdmin(request, response)==false){
      		return;
      	}
      	HttpSession session = request.getSession();
        DetaiDAO detaiDAO = new DetaiDAO();
		User objUserAdmin = null;
		 if(session.getAttribute("admin")!=null){
			 objUserAdmin = (User)session.getAttribute("admin");
		 }
		 
		 int khoa = 0;
		 String key = "";
		 if (request.getParameter("cancel") == null) {
			 if (request.getParameter("key") != null) {
				 key = request.getParameter("key");
			 }
			 if (request.getParameter("khoa") != null) {
				 khoa = Integer.parseInt(request.getParameter("khoa"));
			 }
		 }
		 //Xu ly chia trang
		 int row_count = LibraryConstant.ROW_COUNT;
 		 int current_page = 1;
 		 
 		//tong so de tai
 		 int sumDeTai = detaiDAO.countListDeTaiWith(LibraryConstant.DangChoDuyetThuyetMinh);
 		if (request.getParameter("search") != null){
 			sumDeTai = detaiDAO.countListDeTaiSearchWith(key, khoa, LibraryConstant.DangChoDuyetThuyetMinh);
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
 			ArrayList<DeTai> listDeTaiAdmin = detaiDAO.getListDeTaiWith(LibraryConstant.DangChoDuyetThuyetMinh, offset);
 			 request.setAttribute("listDeTaiAdmin", listDeTaiAdmin);
 		} else {
 			ArrayList<DeTai> listDeTaiAdmin = detaiDAO.getListDeTaiSearchWith(offset, key, khoa, LibraryConstant.DangChoDuyetThuyetMinh);
 			 request.setAttribute("listDeTaiAdmin", listDeTaiAdmin);
 		}

		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/admin/duyet_thuyet_minh_ad.jsp");
         rd.forward(request, response);
         

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
         HttpSession session = request.getSession();
         User objUserAdmin = null;
		 if(session.getAttribute("admin")!=null){
			 objUserAdmin = (User)session.getAttribute("admin");
		 }
		 
         if (request.getParameter("did") != null) {
        	 int idDeTai = Integer.parseInt(request.getParameter("did"));
        	 DetaiDAO detaiDAO = new DetaiDAO();
	 	 	if (request.getParameter("qtthid") != null) {
	 	 		//Cap nhat lai quatrinhthuchien
	   	 		int idqtth = Integer.parseInt(request.getParameter("qtthid"));
	   	 		
	   	 		String noiDung = request.getParameter("noidung");
	   	 		System.out.println("NOI DUNG : " + noiDung);
	   	 		QuaTrinhThucHienDAO qtthDAO = new QuaTrinhThucHienDAO();
	   	 		QuaTrinhThucHien qtth = new QuaTrinhThucHien(idqtth, 0, LibraryConstant.TruongDeXuatChinhSuaThuyetMinh, null, "", noiDung);
	   	 		if (qtthDAO.addItem(qtth) >0) {
	   	 			System.out.println("Add new QTTH OK");
	   	 		}
	   	 		
	   	 		//Tao moi thong bao cho giang vien
	   	 		ThongBaoDAO tbDAO = new ThongBaoDAO();
	   	 		ThongBao tb = new ThongBao(0, objUserAdmin.getIdUser(), detaiDAO.getIdUserWith(idDeTai), noiDung, null, idDeTai, null, null, null, 0);
	   	 		tbDAO.addItem(tb);
	 	 	}
	 		
	 		if (request.getParameter("duyet") != null) {
	 			if (detaiDAO.updateToTrangThai(LibraryConstant.TruongDeXuatChinhSuaThuyetMinh, idDeTai) != 0) {
		 			System.out.println("Update Success!");
		 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/admin/duyet_thuyet_minh_ad?msg=1");
					return; 
		 		} else {
		 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/admin/duyet_thuyet_minh_ad?msg=0");
					return; 
		 		}
	 		} else if (request.getParameter("huy") != null) {
	 			if (detaiDAO.updateToTrangThai(LibraryConstant.Huy, idDeTai) != 0) {
		 			System.out.println("Update Success!");
		 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/admin/duyet_thuyet_minh_ad?msg=1");
					return; 
		 		} else {
		 			response.sendRedirect(request.getContextPath() + "/admin/qldangkydetai/admin/duyet_thuyet_minh_ad?msg=0");
					return; 
		 		}
	 		}
	 		
	 		
         }
	}

}
