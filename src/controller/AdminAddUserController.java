package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Library;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import library.LibraryAuth;
import library.StringLibrary;
import model.bean.User;
import model.dao.UserDAO;

public class AdminAddUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminAddUserController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// kiểm tra đã đăng nhập chưa
		if (LibraryAuth.CheckLogin(request, response) == false) {
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
	    int idHocViHocHam=0, idLoaiTaiKhoan=0, idKhoa=0;
	    String name = null, fullName = null, diaChiCoQuan = null, 
	    		dienThoaiCoQuan = null, namSinh = null,avt=null,
	    		diaChiNhaRieng = null, dienThoaiNhaRieng = null, 
	    		email = null, fax = null, username = null, matKhau = null;
	    if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory())
						.parseRequest(new ServletRequestContext(request));
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {

						String nameImg = new File(item.getName()).getName();
						if(!nameImg.equals("")) {
							avt = nameImg +"_"+ System.currentTimeMillis();
							File folder = new File(request.getServletContext().getRealPath("") +  "/WebContent/templates/admin/img/users");
							folder.mkdirs();
							// tao folder
							item.write(new File(request.getServletContext().getRealPath("") +  "/WebContent/templates/admin/img/users/" + avt));
						}
					}else {
						String fN = item.getFieldName();
						if (fN.equals("fullname")) {
							fullName = item.getString();
						} else if (fN.equals("dia_chi_co_quan")) {
							diaChiCoQuan = item.getString();
						} else if (fN.equals("dien_thoai_co_quan")) {
							dienThoaiCoQuan = item.getString();
						} else if (fN.equals("nam_sinh")) {
							namSinh = item.getString();
						} else if (fN.equals("dia_chi_nha_rieng")) {
							diaChiNhaRieng = item.getString();
						} else if (fN.equals("dien_thoai_nha_rieng")) {
							dienThoaiNhaRieng = item.getString();
						} else if (fN.equals("email")) {
							email = item.getString();
						} else if (fN.equals("fax")) {
							fax = item.getString();
						} else if (fN.equals("mat_khau")) {
							if (!item.getString().equals(""))
								matKhau = st.MD5(item.getString());
						} else if (fN.equals("username")) {
							username = item.getString();
						} else if (fN.equals("hoc_vi_hoc_ham")) {
							idHocViHocHam =  Integer.parseInt(item.getString()) ;
						} else if (fN.equals("loai_tai_khoan")) {
							idLoaiTaiKhoan =  Integer.parseInt(item.getString()) ;
						} else if (fN.equals("khoa")) {
							idKhoa =  Integer.parseInt(item.getString()) ;
						}
					}
					System.out.println(item.getString());
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			User objUser = new  User(0, fullName,
				diaChiCoQuan, dienThoaiCoQuan, idHocViHocHam,
				"", namSinh, diaChiNhaRieng,
				dienThoaiNhaRieng, email, fax,
				username, matKhau, idLoaiTaiKhoan,
				"", idKhoa, "", avt);
			System.out.println(objUser.toString());
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
}
