package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Out;

import library.LibraryAuth;
import model.bean.HopDong;
import model.bean.User;
import model.dao.HopDongDAO;

public class AdminExportHopDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminExportHopDongController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckAdmin(request, response)==false){
					return;
				}
		HopDongDAO objDAO = new HopDongDAO();
		int idHopDong = Integer.parseInt(request.getParameter("uid"));//idUser tren url
    	
		HopDong objHopDong = objDAO.getObjHopDong(idHopDong);
		if (objHopDong != null) {
		request.setAttribute("objHopDong", objHopDong);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/hopdong/exportWord.jsp");
		rd.forward(request, response);
		
	    }else{
	    	response.sendRedirect(request.getContextPath()+"/admin/hopdong/index?msg=4");
	    	return;
	    } 
	    
	    
	}

}
