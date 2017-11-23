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

public class AdminIndexQLDKDTAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminIndexQLDKDTAdmin() {
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
 			ArrayList<DeTai> listDeTaiAdmin = detaiDAO.getListDangKyDeTai(offset);
 			 request.setAttribute("listDeTaiAdmin", listDeTaiAdmin);
 		} else {
 			ArrayList<DeTai> listDeTaiAdmin = detaiDAO.getListDangKyDeTaiSearch(offset, key, khoa);
 			 request.setAttribute("listDeTaiAdmin", listDeTaiAdmin);
 		}
		
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/admin/index_admin.jsp");
         rd.forward(request, response);
         

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
	}

}
