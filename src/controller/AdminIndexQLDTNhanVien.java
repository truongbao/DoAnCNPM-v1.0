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

public class AdminIndexQLDTNhanVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DetaiDAO detaiDAO = new DetaiDAO();
	String keyword = "";
	
    public AdminIndexQLDTNhanVien() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
 		response.setCharacterEncoding("UTF-8");
 		response.setContentType("text/html");
         
 		//kiểm tra đã đăng nhập chưa
       	if(  LibraryAuth.CheckNhanVienTruong(request, response)==false){
       		return;
       	}
       		
         HttpSession session = request.getSession();
         
 		User objUser = null;
 		if(session.getAttribute("quanLyNCKHKhoa")!=null){
 			objUser = (User)session.getAttribute("quanLyNCKHKhoa");
 		}
 		if ("load".equals(request.getParameter("type"))) { 
 			int DT_sum = detaiDAO.getSumListDeTaiByStatus(0, LibraryConstant.DangThucHien, "", 0);
 			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
 			int current_page = 1;
 			if (request.getParameter("page") != null) {
 				current_page = Integer.parseInt(request.getParameter("page"));
 			}
 			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
 			request.setAttribute("current_page", current_page);
 			request.setAttribute("page_sum", page_sum);
 			ArrayList<DeTai> listDeTaiByIdKhoa = detaiDAO.getListDeTaiByStatus(0, LibraryConstant.DangThucHien, "", 0, offset, LibraryConstant.ROW_COUNT);
 			request.setAttribute("listDeTaiNhanVien", listDeTaiByIdKhoa);

 			RequestDispatcher rd = request.getRequestDispatcher("/admin/qldetai/nhanvien/index_nhanvien.jsp");
 			rd.forward(request, response);
 		}else if("search".equals(request.getParameter("type"))){
 			
 	        if (request.getParameter("keyword") != null) {
 				keyword = request.getParameter("keyword");
 			}
 	        int DT_sum = detaiDAO.getSumListDeTaiByStatus(0, LibraryConstant.DangThucHien, keyword, 0);
 			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
 			int current_page = 1;
 			if (request.getParameter("page") != null) {
 				current_page = Integer.parseInt(request.getParameter("page"));
 			}
 			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
 			request.setAttribute("current_page", current_page);
 			request.setAttribute("page_sum", page_sum);
 			ArrayList<DeTai> listDeTaiByIdKhoa = detaiDAO.getListDeTaiByStatus(0, LibraryConstant.DangThucHien, keyword, 0, offset, LibraryConstant.ROW_COUNT);
 			request.setAttribute("listDeTaiNhanVien", listDeTaiByIdKhoa);
 			RequestDispatcher rd = request.getRequestDispatcher("/admin/qldetai/nhanvien/index_nhanvien.jsp");
 			rd.forward(request, response);
 		}
	}

}
