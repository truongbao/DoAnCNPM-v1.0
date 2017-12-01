package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.dao.ThongBaoDAO;

public class PublicIndexThongBaoQLDTController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexThongBaoQLDTController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	     //kiểm tra đã đăng nhập ở public chưa
		 if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		 }

		 ThongBaoDAO thongBaoDAO = new ThongBaoDAO();
		 
	    int idDeTai = Integer.parseInt(request.getParameter("id_detai"));

	    //lây ra danh sach thong bao ứng với idDeTai
	    request.setAttribute("listThongBaoByIdDeTai",  thongBaoDAO.getListThongBaoNewByIdDeTai(idDeTai) );
	    
	    //set lại trường wasRead = 1
	    thongBaoDAO.updateTrangThaiWasRead(idDeTai);
	    
	    //gui idDeTai qua jsp
	    request.setAttribute("idDeTai",  idDeTai );
	    
		RequestDispatcher rd = request.getRequestDispatcher("/thong_bao_qldt.jsp");
	    rd.forward(request, response);
	      
	      
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
       
	}

}
