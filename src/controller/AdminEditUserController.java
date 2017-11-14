package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import library.LibraryAuth;
import library.StringLibrary;
import model.bean.User;
import model.dao.UserDAO;

public class AdminEditUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminEditUserController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// kiểm tra đã đăng nhập chưa
		if (LibraryAuth.CheckAdmin(request, response) == false) {
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// kiểm tra đã đăng nhập chưa
		if (LibraryAuth.CheckLogin(request, response) == false) {
			return;
		}
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		UserDAO objDAO = new UserDAO();

		int idUser = Integer.parseInt(request.getParameter("uid"));
		StringLibrary st = new StringLibrary();// tao doi tuong lop ma hóa
		User oldUser = objDAO.getObjUser(idUser);
		int idHocViHocHam = oldUser.getIdHocViHocHam(), idLoaiTaiKhoan = oldUser.getIdLoaiTaiKhoan(), idKhoa = oldUser.getIdKhoa();
		String fullName = oldUser.getFullName(), diaChiCoQuan = oldUser.getDiaChiCoQuan(),
				dienThoaiCoQuan = oldUser.getDienThoaiCoQuan(), namSinh = oldUser.getNamSinh(), avt = oldUser.getAvt(),
				diaChiNhaRieng = oldUser.getDiaChiNhaRieng(), dienThoaiNhaRieng = oldUser.getDienThoaiNhaRieng(),
				email = oldUser.getEmail(), fax = oldUser.getFax(), username = oldUser.getUserName(),
				matKhau = objDAO.getObjUser(idUser).getMatKhau();
		System.out.println(matKhau);
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
							item.write(new File(request.getServletContext().getRealPath("") +  "/WebContent/templates/admin/img/users/" + avt));
						}
					} else {
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
								matKhau = st.MD5(item.getString());// truong hop k thay doi mat khau
						} else if (fN.equals("username")) {
							username = item.getString();
						} else if (fN.equals("hoc_vi_hoc_ham")) {
							idHocViHocHam = Integer.parseInt(item.getString());
						} else if (fN.equals("loai_tai_khoan")) {
							if (!item.getString().equals(""))// truong hop disable truong loai tai khoan
								idLoaiTaiKhoan = Integer.parseInt(item.getString());
						} else if (fN.equals("khoa")) {
							idKhoa = Integer.parseInt(item.getString());
						}
					}
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}

			User objUser = new User(idUser, fullName, diaChiCoQuan, dienThoaiCoQuan, idHocViHocHam, "", namSinh,
					diaChiNhaRieng, dienThoaiNhaRieng, email, fax, username, matKhau, idLoaiTaiKhoan, "", idKhoa, "",
					avt);
			if (objDAO.editItem(objUser) > 0) {
				// them thanh cong
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=1");
				return;
			} else {
				// them that bai
				response.sendRedirect(request.getContextPath() + "/admin/user/index?msg=0");
				return;
			}

		}
	}

}
