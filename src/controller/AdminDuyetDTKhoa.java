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
		 System.out.println(objUserAdmin.getFullName());
		 ArrayList<DeTai> listDuyetDeTaiByIdKhoa = detaiDAO.getListDuyetDeTaiByIdKhoa(objUserAdmin.getIdKhoa());
		 request.setAttribute("listDuyetDeTaiByIdKhoa", listDuyetDeTaiByIdKhoa);
		System.out.println(listDuyetDeTaiByIdKhoa.size());
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/khoa/duyet_de_xuat_khoa.jsp");
         rd.forward(request, response);
         

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
	}

}
