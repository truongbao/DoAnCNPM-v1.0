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
import model.bean.ThanhVien;
import model.bean.User;
import model.dao.UserDAO;

public class PublicEditMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicEditMemberController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
		  response.setCharacterEncoding("UTF-8");
		  response.setContentType("text/html");
		  
		   UserDAO userDAO  = new UserDAO(); 
		    
		 //kiểm tra đã đăng nhập ở public chưa
	     if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		 }
	     
	     
	     int idThanhVien = Integer.parseInt(request.getParameter("tidTV"));
	     int idDeTai = Integer.parseInt(request.getParameter("tidDeTai"));
	     String tenThanhVien = new String( request.getParameter("tnameTV").getBytes("ISO-8859-1"),"UTF-8");
	     
	     //System.out.println("id tv : "+idThanhVien+" ten tv : "+tenThanhVien);
	     
	     //lấy ra đối tượng ứng với idThanhVien
	     ThanhVien objThanhVien = userDAO.getObjThanhVien(idThanhVien, tenThanhVien,idDeTai);
	     
	     
	      /*if(objThanhVien != null){
	         System.out.println(objThanhVien.toString());
	      }*/
	   
	     request.setAttribute("objThanhVien", objThanhVien);

	     
		//gửi danh sách các thanh viên trong trường
	     ArrayList<User> listUserInSchool = new ArrayList<User>();
		
		 if(listUserInSchool != null){
			 listUserInSchool = userDAO.getListUserInSchool();
		 }
		 
		 request.setAttribute("listUserInSchool", listUserInSchool);
		
		 
		 //gui danh sach mã sô đề tài ứng với Giangvien đang login
		 HttpSession session = request.getSession();
	     User objUser = null;
        if(session.getAttribute("sobjUserPublic") != null){ 
       	 objUser =  (User) session.getAttribute("sobjUserPublic");
        }
       	     
	     request.setAttribute("ListMaSoDeTaiByIdUser", userDAO.getListMaSoDeTaiByIdUser(objUser.getIdUser() ) );
		
		
		 RequestDispatcher rd = request.getRequestDispatcher("/edit_member.jsp");
         rd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("text/html");
		
		 UserDAO userDAO  = new UserDAO(); 
        
		 //kiểm tra đã đăng nhập ở public chưa
	     if(  LibraryAuth.CheckLoginPublic(request, response)==false){
			return;
		 }
	     
	     int idThanhVien1 = Integer.parseInt(request.getParameter("tidTV"));
	     int idDeTai1 = Integer.parseInt(request.getParameter("tidDeTai"));
	     String tenThanhVien1 = new String( request.getParameter("tnameTV").getBytes("ISO-8859-1"),"UTF-8");
	     
	     
	     //==========================================
	     int idUser = Integer.parseInt(request.getParameter("idUser")); //thanh vien trong truong (la idThanhVien)
	  	 String nameTV = request.getParameter("nameTV");
	  	 String donVi = request.getParameter("donVi"); 
	  	 String noiDungNghienCuu = request.getParameter("noiDungNghienCuu");
	  	 int idDeTai = Integer.parseInt(request.getParameter("idDeTai"));
	  	 
	  	 
	  	 //lấy ra đối tượng User ứng với idUser
	   	 User objUser = null;
	  	 if(idUser > 0){
	  	     objUser = userDAO.getObjUser(idUser);
	  	 }
	  	 
		ThanhVien objTV = null;
		
		if(idUser > 0 && nameTV.isEmpty()){ //đã chọn thành viên trong trường => ko dc them thanh vien ngoai truong
		    objTV = new ThanhVien(idUser, objUser.getFullName(), donVi, noiDungNghienCuu, idDeTai, "");
		    
		    if(userDAO.editThanhVienPublic(objTV,idThanhVien1,idDeTai1,tenThanhVien1) >  0){
				response.sendRedirect(request.getContextPath()+"/quanly-thanhvien?msg=2");
				return; 
			}else{
				response.sendRedirect(request.getContextPath()+"/quanly-thanhvien?msg=3");
				return; 
			}
		    
		}else if(idUser > 0 && nameTV != null && !nameTV.isEmpty()){
			response.sendRedirect(request.getContextPath()+"/quanly-thanhvien?msg=5");
			return;
		}else{
			objTV = new ThanhVien(0, nameTV, donVi, noiDungNghienCuu, idDeTai, "");
			
			if(userDAO.editThanhVienPublic(objTV,idThanhVien1,idDeTai1,tenThanhVien1) >  0){
				response.sendRedirect(request.getContextPath()+"/quanly-thanhvien?msg=2");
				return; 
			}else{
				response.sendRedirect(request.getContextPath()+"/quanly-thanhvien?msg=3");
				return; 
			}
			
		}
		
        
	}
	
	
	
	
	

}
