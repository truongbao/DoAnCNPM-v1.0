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
import model.bean.DeTai;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.UserDAO;

public class PublicUpdateDetaiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicUpdateDetaiController() {
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
	    
	    int idDeTai = Integer.parseInt(request.getParameter("did"));
	    
        DeTai objDeTaiByIdDeTaiDK = detaiDAO.getObjectDeTaiByIdDeTaiDK(idDeTai);
	    
	    request.setAttribute("objDeTaiByIdDeTaiDK", objDeTaiByIdDeTaiDK);
	    
	    //lay danh sach linh vuc nghien cuu
	    request.setAttribute("listLinhVucNC", detaiDAO.getListLinhVucNC());
	    
	    
	    //lấy thông tin đối tượng sobjUserPublic 
	    User objUser = null;
		HttpSession session = request.getSession();
		
        if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        }
        
        request.setAttribute("objUser", objUser);
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/update_detai.jsp");
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
		
		    //Cap nhat doi tuong de tai ung vs idDeTai
			
			User objUser = null;
			HttpSession session = request.getSession();
			if(session.getAttribute("sobjUserPublic")!=null){
	            objUser = (User)session.getAttribute("sobjUserPublic");
	        }
			
			int idDeTai = Integer.parseInt(request.getParameter("did"));
			
			String tenDeTai = request.getParameter("tenDeTai");
			int idLinhVucNghienCuu = Integer.parseInt(request.getParameter("idLinhVucNghienCuu"));
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
										"", "", null, objUser.getIdKhoa(), "");
			
			objDeTai.setIdDeTai(idDeTai); //set lại idDeTai lấy dc

			if(detaiDAO.editDeTaiPublic(objDeTai) >  0){
			   response.sendRedirect(request.getContextPath()+"/list-register-detai?msg=2");
			   return;
			}else{
			   response.sendRedirect(request.getContextPath()+"/list-register-detai?msg=0");
			   return;
			}

		
        
	}
	
	
	

}
