package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.StringLibrary;
import model.bean.User;
import model.dao.UserDAO;

public class PublicUpdateInfoPersonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PublicUpdateInfoPersonController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    UserDAO userDAO = new UserDAO();
	    
		//lấy thông tin đối tượng sobjUserPublic 
	    User objUser = null;
		HttpSession session = request.getSession();
		
        if(session.getAttribute("sobjUserPublic")!=null){
            objUser = (User)session.getAttribute("sobjUserPublic");
        }
        
		//gui qua danh sach hoc vi
        request.setAttribute("listHocVi", userDAO.getListHocVi());
        
        //gui qua jsp danh sach loai tai khoan
        request.setAttribute("listLoaiTK", userDAO.getListLoaiTK());
        
        //gui qua jsp danh sach khoa
        request.setAttribute("listKhoa", userDAO.getListKhoa());
        
         //request.setAttribute("objUser", objUser);
         request.setAttribute("objUser", userDAO.getObjUser(objUser.getIdUser()));
        
		 RequestDispatcher rd = request.getRequestDispatcher("/update_infor_person.jsp");
         rd.forward(request, response);
	}

	
	//Xu ly cap nhat
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
		/*if(  LibraryAuth.CheckLogin(request, response)==false){
			return;
		}*/
		
		
		HttpSession session = request.getSession();
		
		StringLibrary st = new StringLibrary();//tao doi tuong lop ma hóa
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		UserDAO userDAO = new UserDAO();
		
		int idUser = Integer.parseInt(request.getParameter("uid"));
		
		String fullName = request.getParameter("hoten");
		String matKhau =  request.getParameter("matkhau");
		String email = request.getParameter("email");
		
		int idKhoa = Integer.parseInt(request.getParameter("idKhoa"));
		int idHocVi = Integer.parseInt(request.getParameter("idHocVi"));
		int idLoaiTaiKhoan = Integer.parseInt(request.getParameter("idLoaiTaiKhoan"));
		
		String chucDanhKhoaHoc = request.getParameter("chucdanh_khoahoc");
		String diaChiCoQuan = request.getParameter("diachi_coquan");
		String diaChiNhaRieng = request.getParameter("diachi_nharieng");
		String fax = request.getParameter("fax");
		String dienThoaiCoQuan = request.getParameter("phone_coquan");
		String dienThoaiNhaRieng = request.getParameter("phone_nharieng");
		String namSinh = request.getParameter("ngaysinh");

		
		User objUserById  = userDAO.getObjUser(idUser);
		if("".equals(matKhau)){ //lay lại pass cũ dựa vào id
			matKhau = objUserById.getMatKhau();
		}else{
		    matKhau =  st.MD5(matKhau) ;
		}
		
		User objUser = new User(idUser, fullName, chucDanhKhoaHoc, diaChiCoQuan, dienThoaiCoQuan, 
				                idHocVi, "", namSinh, diaChiNhaRieng, dienThoaiNhaRieng, email,
				                fax, "" , matKhau, idLoaiTaiKhoan, "", idKhoa, "");
		
		  
		
		if(userDAO.editItem(objUser) >  0){
			//sửa thanh công => cập nhật lại session
			session.setAttribute("sobjUserPublic", objUser );//set lại đoi tượng sesion
			response.sendRedirect(request.getContextPath()+"/quanly-taikhoan?msg=2");
			
		}else{
			//sửa that bai
			response.sendRedirect(request.getContextPath()+"/quanly-taikhoan?msg=0");
		}
		
		
	}
	
	
	
	
}
