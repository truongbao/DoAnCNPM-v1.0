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

public class AdminDetailDuyetNghiemThuAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDetailDuyetNghiemThuAdmin() {
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
      	
        DetaiDAO detaiDAO = new DetaiDAO();
		  
	    int idDeTai = Integer.parseInt(request.getParameter("did"));
	    
	    //tu idDeTai => danh sach thanh vien lam de tai nay (o bang thanh vien)
	    //send qua jsp
	    
	    	    
		DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
		request.setAttribute("objDeTai", objDeTai);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldetai/admin/detail_duyet_nt_ad.jsp");
         rd.forward(request, response);
         

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
	}

}
