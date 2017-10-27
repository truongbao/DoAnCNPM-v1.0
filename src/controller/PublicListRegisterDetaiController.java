package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import model.bean.DeTai;
import model.dao.DetaiDAO;

public class PublicListRegisterDetaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicListRegisterDetaiController() {
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
		
		DetaiDAO detaiDAO =new DetaiDAO();
		    
		ArrayList<DeTai> listDeTaiDKSave = new ArrayList<DeTai>();
		
		listDeTaiDKSave = detaiDAO.getListDeTaiDK();
		
		request.setAttribute("listDeTaiDK", detaiDAO.getListDeTaiDK() );
		//request.setAttribute("listDeTaiDKSave", listDeTaiDKSave );
		
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/list_register_detai.jsp");
         rd.forward(request, response);       
         
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
        
	}

}
