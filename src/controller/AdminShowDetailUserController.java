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
import model.dao.UserDAO;

public class AdminShowDetailUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminShowDetailUserController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckLogin(request, response)==false){
					return;
				}
		UserDAO objDAO = new UserDAO();
		int idUser = Integer.parseInt(request.getParameter("uid"));//idUser tren url
		//==============================
		//phân quyền 
//		HttpSession session = request.getSession();
//	    User userinfo = (User)session.getAttribute("sobjUser");
//	    if("admin".equals(objDAO.getItem( userinfo.getIdUser()).getUsername() ) || (idUser==userinfo.getIdUser()) ){
	    	
		User objUser = objDAO.getObjUser(idUser);
		ArrayList<DeTai> listDeTai = objDAO.getListDeTaiByIdUser(idUser);
		if (objUser != null) {
		request.setAttribute("objUser", objUser);
		request.setAttribute("listDeTai", listDeTai);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/users/show.jsp");
		rd.forward(request, response);
		
	    }else{
	    	response.sendRedirect(request.getContextPath()+"/admin/user/index?msg=4");
	    	return;
	    } 
	}

}
