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

public class AdminDuyetDTKhoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDuyetDTKhoa() {
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
       	if(  LibraryAuth.CheckQuanLyKhoa(request, response)==false){
       		return;
       	}
       		
         HttpSession session = request.getSession();
         DetaiDAO detaiDAO = new DetaiDAO();
 		User objUserAdmin = null;
 		if(session.getAttribute("quanLyNCKHKhoa")!=null){
 			 objUserAdmin = (User)session.getAttribute("quanLyNCKHKhoa");
 		}
 		int idFaculty = objUserAdmin.getIdKhoa();
 		if ("load".equals(request.getParameter("type"))) { 
 			int DT_sum = detaiDAO.getSumListDeTaiByStatus(idFaculty, LibraryConstant.DangChoDuyetCapKhoa,"");
 			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
 			int current_page = 1;
 			if (request.getParameter("page") != null) {
 				current_page = Integer.parseInt(request.getParameter("page"));
 			}
 			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
 			request.setAttribute("current_page", current_page);
 			request.setAttribute("page_sum", page_sum);
 			 ArrayList<DeTai> listDuyetDeTaiByIdKhoa = detaiDAO.getListDeTaiByStatus(0,LibraryConstant.DangChoDuyetCapKhoa,"",offset, LibraryConstant.ROW_COUNT);
 			 request.setAttribute("listDuyetDeTaiByIdKhoa", listDuyetDeTaiByIdKhoa);
 			 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/khoa/duyet_de_xuat_khoa.jsp");
 			 rd.forward(request, response);
 		}else if ("search".equals(request.getParameter("type"))){
 			System.out.println("ok");
 			String keyword = request.getParameter("keyword");
 			int DT_sum = detaiDAO.getSumListDeTaiByStatus(idFaculty, LibraryConstant.DangChoDuyetCapKhoa,keyword);
 			int page_sum = (int) Math.ceil(((float) DT_sum / LibraryConstant.ROW_COUNT));
 			int current_page = 1;
 			if (request.getParameter("page") != null) {
 				current_page = Integer.parseInt(request.getParameter("page"));
 			}
 			int offset = (current_page - 1) * LibraryConstant.ROW_COUNT;
 			request.setAttribute("current_page", current_page);
 			request.setAttribute("page_sum", page_sum);
 			 ArrayList<DeTai> listDuyetDeTaiByIdKhoa = detaiDAO.getListDeTaiByStatus(0,LibraryConstant.DangChoDuyetCapKhoa,keyword,offset, LibraryConstant.ROW_COUNT);
 			 request.setAttribute("listDuyetDeTaiByIdKhoa", listDuyetDeTaiByIdKhoa);
 			 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/khoa/duyet_de_xuat_khoa.jsp");
 			 rd.forward(request, response);
 		}
	}

}
