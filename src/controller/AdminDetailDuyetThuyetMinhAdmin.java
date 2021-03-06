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
import model.bean.ThanhVien;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.QuaTrinhThucHienDAO;
import model.dao.UserDAO;

public class AdminDetailDuyetThuyetMinhAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDetailDuyetThuyetMinhAdmin() {
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
		
		QuaTrinhThucHienDAO qtthDAO = new QuaTrinhThucHienDAO();
		QuaTrinhThucHien qtth = qtthDAO.getQuaTrinhThucHienWith(idDeTai, LibraryConstant.DangChoDuyetThuyetMinh);
		System.out.println("QTTH: " + qtth);
		request.setAttribute("qtThucHien", qtth);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/admin/detail_duyet_tm_ad.jsp");
         rd.forward(request, response);
         

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
	}

}
