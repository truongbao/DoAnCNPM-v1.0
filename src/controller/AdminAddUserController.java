package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.LibraryAuth;
import library.StringLibrary;
import model.bean.User;
import model.dao.UserDAO;


public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminAddUserController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckLogin(request, response)==false){
					return;
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//kiểm tra đã đăng nhập chưa
				if(  LibraryAuth.CheckAdmin(request, response)==false){
					return;
				}
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html");
	    
	    StringLibrary st = new StringLibrary();//tao doi tuong lop ma hóa
	    
	    UserDAO objDAO = new UserDAO();
	    
		String fullName = request.getParameter("fullname");
		String chucDanhKhoaHoc =  request.getParameter("chuc_danh_khoa_hoc");
		String diaChiCoQuan = request.getParameter("dia_chi_co_quan");
		String dienThoaiCoQuan = request.getParameter("dien_thoai_co_quan");
		int idHocViHocHam = Integer.parseInt(request.getParameter("hoc_vi_hoc_ham"));
		String namSinh =  request.getParameter("nam_sinh");
		String diaChiNhaRieng = request.getParameter("dia_chi_nha_rieng");
		String dienThoaiNhaRieng = request.getParameter("dien_thoai_nha_rieng");
		String email = request.getParameter("email");
		String fax =  request.getParameter("fax");
		String username = request.getParameter("username");
		String matKhau = st.MD5(request.getParameter("mat_khau"));
		int idLoaiTaiKhoan = Integer.parseInt(request.getParameter("loai_tai_khoan"));
		int idKhoa =  Integer.parseInt(request.getParameter("khoa")) ;
		
		User objUser = new  User(0, fullName,
				diaChiCoQuan, dienThoaiCoQuan, idHocViHocHam,
				"", namSinh, diaChiNhaRieng,
				dienThoaiNhaRieng, email, fax,
				username, matKhau, idLoaiTaiKhoan,
				"", idKhoa, "");
		if(!objDAO.checkExistUserName(username)){ //username đã ton tai user 
			
			response.sendRedirect(request.getContextPath() + "/admin/user/form-add?msg=3");
			return;
			
		} else if(!objDAO.checkExistEmail(email)){ // email đã ton tai user 
			
			response.sendRedirect(request.getContextPath() + "/admin/user/form-add?msg=2");
			return;
		} else { //chưa ton tại thi thêm
			if( objDAO.addItem(objUser) > 0){
				//them thanh cong
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=1");
				return; 
			}else{
				//them that bai
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0");
				return; 
			}
			
		}
		
		
		
		
		
		
	}

}
