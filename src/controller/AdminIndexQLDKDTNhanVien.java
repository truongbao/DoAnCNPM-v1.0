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
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class AdminIndexQLDKDTNhanVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexQLDKDTNhanVien() {
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
 		 int sumDeTai = detaiDAO.countListDKDeTai();
 		if (request.getParameter("search") != null){
 			sumDeTai = detaiDAO.countListDKDeTaiSearchWith(key, khoa);
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
 			ArrayList<DeTai> listDeTaiNhanVien = detaiDAO.getListDangKyDeTai(offset);
 			 request.setAttribute("listDeTaiNhanVien", listDeTaiNhanVien);
 		} else {
 			ArrayList<DeTai> listDeTaiNhanVien = detaiDAO.getListDangKyDeTaiSearch(offset, key, khoa);
 			 request.setAttribute("listDeTaiNhanVien", listDeTaiNhanVien);
 		}
		 
		
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/nhanvien/index_nhanvien.jsp");
         rd.forward(request, response);
         

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
	}

}
