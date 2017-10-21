package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Cat;
import model.bean.DeTai;
import model.bean.ThanhVien;
import model.dao.DetaiDAO;

public class PublicIndexDetailDeTaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicIndexDetailDeTaiController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DetaiDAO detaiDAO = new DetaiDAO();
		  
	    int idDeTai = Integer.parseInt(request.getParameter("did"));
	    
	    //tu idDeTai => danh sach thanh vien lam de tai nay (o bang thanh vien)
	    //send qua jsp
	    
	    ArrayList<ThanhVien> listTVByIdDeTai = detaiDAO.getListThanhVienByDeTai(idDeTai);
	    
	    request.setAttribute("listTVByIdDeTai", listTVByIdDeTai);
	    
		DeTai objDeTai = detaiDAO.getObjDeTai(idDeTai);
		
		request.setAttribute("objDeTai", objDeTai);
		
		  RequestDispatcher rd = request.getRequestDispatcher("/detail_detai.jsp");
	      rd.forward(request, response);
	         
	      
	      
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         
       
	}
	
	
	

}
