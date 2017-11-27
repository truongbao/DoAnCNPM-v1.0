package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.LibraryAuth;
import library.LibraryConstant;
import library.StringLibrary;
import model.bean.DeTai;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class PublicRegisterDeTaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicRegisterDeTaiController() {
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
		
		UserDAO userDAO = new UserDAO();
	    DetaiDAO detaiDAO =new DetaiDAO();
	    
	    request.setAttribute("listLinhVucNC", detaiDAO.getListLinhVucNC());
	    
	    request.setAttribute("listCapDeTai", detaiDAO.getListCapDeTai());
        
	    
		//lấy thông tin đối tượng sobjUserPublic 
	    User objUser = null;
		HttpSession session = request.getSession();
		
        if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        }
        
        request.setAttribute("objUser", objUser);
		
		 RequestDispatcher rd = request.getRequestDispatcher("/register_detai.jsp");
         rd.forward(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
		
		//kiểm tra đã đăng nhập ở public chưa
		if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		}
		
		DetaiDAO detaiDAO = new DetaiDAO();
		
		User objUser = null;
		HttpSession session = request.getSession();
		if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        }
		
		String tenDeTai = request.getParameter("tenDeTai");
		int idLinhVucNghienCuu = Integer.parseInt(request.getParameter("idLinhVucNghienCuu"));
		int idCapDeTai = Integer.parseInt(request.getParameter("idCapDeTai"));
		String tinhCapThiet = request.getParameter("tinhCapThiet");
		String mucTieu = request.getParameter("mucTieu");
		String noiDung = request.getParameter("noiDungChinh");
		String sanPham = request.getParameter("sanPham");
		String hieuQua = request.getParameter("hieuQuaDukien");
		int kinhPhiThucHien = Integer.parseInt(request.getParameter("kinhPhiThucHien"));
		int idUser = objUser.getIdUser();
		
		DeTai objDeTai = new DeTai( 0, tenDeTai, "", idLinhVucNghienCuu,
									"", 0, "", null, null, "", idUser, "", "", "",
									tinhCapThiet, mucTieu, "", "",
									noiDung, sanPham, hieuQua, kinhPhiThucHien, 
									LibraryConstant.DangChoXetDeTai, idCapDeTai,"", null, objUser.getIdKhoa(), "");
        
		 if(detaiDAO.addDeTaiPublic(objDeTai) >  0){
				response.sendRedirect(request.getContextPath()+"/list-register-detai?msg=1");
				return;
		 }else{
				response.sendRedirect(request.getContextPath()+"/list-register-detai?msg=0");
				return;
		 }
		
		
		
		
	}
	
	
	

}
