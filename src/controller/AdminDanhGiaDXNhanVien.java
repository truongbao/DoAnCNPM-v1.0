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
import model.bean.ThanhVien;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class AdminDanhGiaDXNhanVien extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDanhGiaDXNhanVien() {
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
		  
        if (request.getParameter("did") != null) {
        	int idDeTai = Integer.parseInt(request.getParameter("did"));
    		DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
    		System.out.println(objDeTai.getTenDeTai());
    		request.setAttribute("objDeTai", objDeTai);
    		
    		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/nhanvien/danh_gia_dx_nv.jsp");
             rd.forward(request, response);
        } 
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
	}

}
