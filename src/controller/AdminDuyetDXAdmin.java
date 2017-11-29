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
import library.LibraryConstant;
import model.bean.DeTai;
import model.bean.QuaTrinhThucHien;
import model.bean.ThongBao;
import model.bean.User;
import model.dao.DetaiDAO;
import model.dao.QuaTrinhThucHienDAO;
import model.dao.ThongBaoDAO;
import model.dao.UserDAO;

public class AdminDuyetDXAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDuyetDXAdmin() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        
      //kiểm tra đã đăng nhập chưa
		if (LibraryAuth.CheckAdmin(request, response) == false) {
			return;
		}
		HttpSession session = request.getSession();
		DetaiDAO detaiDAO = new DetaiDAO();
		User objUserAdmin = null;
		if (session.getAttribute("admin") != null) {
			objUserAdmin = (User) session.getAttribute("admin");
		}

		int khoa = 0;
		String key = "";
		if (request.getParameter("cancel") == null) {
			if (request.getParameter("key") != null) {
				key = request.getParameter("key");
			}
			if (request.getParameter("khoa") != null) {
				khoa = Integer.parseInt(request.getParameter("khoa"));
			}
		}
		// Xu ly chia trang
		int row_count = LibraryConstant.ROW_COUNT;
		int current_page = 1;

		// tong so de tai
		int sumDeTai = detaiDAO.countListDeTaiAdmin(LibraryConstant.ChoHuy, LibraryConstant.ChoDeNghiChinhSuaDeXuat, LibraryConstant.DangChoDuyetCapTruong);
		if (request.getParameter("search") != null) {
			sumDeTai = detaiDAO.countListDeTaiSearchAdmin(key, khoa,LibraryConstant.ChoHuy, LibraryConstant.ChoDeNghiChinhSuaDeXuat, LibraryConstant.DangChoDuyetCapTruong);
		}
		// tong so trang
		int sumPage = (int) Math.ceil((float) sumDeTai / row_count);
		request.setAttribute("sumPage", sumPage);

		// lấy số trang hiện tại
		if (request.getParameter("page") != null) {
			current_page = Integer.parseInt(request.getParameter("page"));
		}

		// tính offset
		int offset = (current_page - 1) * row_count;
		request.setAttribute("current_page", current_page);
		if (request.getParameter("search") == null) {
			ArrayList<DeTai> listDeTaiAdmin = detaiDAO.getListDeTaiAdmin(LibraryConstant.ChoHuy, LibraryConstant.ChoDeNghiChinhSuaDeXuat,LibraryConstant.DangChoDuyetCapTruong, offset);
			request.setAttribute("listDeTaiAdmin", listDeTaiAdmin);
		} else {
			ArrayList<DeTai> listDeTaiAdmin = detaiDAO.getListDeTaiSearchAdmin(offset, key, khoa,LibraryConstant.ChoHuy, LibraryConstant.ChoDeNghiChinhSuaDeXuat,
					LibraryConstant.DangChoDuyetCapTruong);
			request.setAttribute("listDeTaiAdmin", listDeTaiAdmin);
		}

		
		doPost(request, response);
    	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
         response.setCharacterEncoding("UTF-8");
         response.setContentType("text/html");
         
         HttpSession session = request.getSession();
         User objUserAdmin = null;
		 if(session.getAttribute("admin")!=null){
			 objUserAdmin = (User)session.getAttribute("admin");
		 }
		 
		if (request.getParameter("did") != null) {
			int idDeTai = Integer.parseInt(request.getParameter("did"));
			DetaiDAO detaiDAO = new DetaiDAO();
			if (request.getParameter("noidung") != null) {
				String noiDung = request.getParameter("noidung");
				System.out.println("NOI DUNG : " + noiDung);
				QuaTrinhThucHienDAO qtthDAO = new QuaTrinhThucHienDAO();
				QuaTrinhThucHien qtth = new QuaTrinhThucHien(0, 0, LibraryConstant.DangChoDuyetCapTruong, null, "",
						noiDung);
				if (qtthDAO.addItem(qtth) > 0) {
					System.out.println("Add new QTTH OK");
				}

				// Tao moi thong bao cho giang vien
				ThongBaoDAO tbDAO = new ThongBaoDAO();
				ThongBao tb = new ThongBao(0, objUserAdmin.getIdUser(), detaiDAO.getIdUserWith(idDeTai), noiDung, null,
						idDeTai, null, null, null, 0);
				tbDAO.addItem(tb);
			} 
			else {
				QuaTrinhThucHienDAO qtthDAO = new QuaTrinhThucHienDAO();
				QuaTrinhThucHien qtth = qtthDAO.getQuaTrinhThucHienWith(idDeTai, LibraryConstant.DangChoDuyetCapTruong);
				// Tao moi thong bao cho giang vien
				ThongBaoDAO tbDAO = new ThongBaoDAO();
				ThongBao tb = new ThongBao(0, objUserAdmin.getIdUser(), detaiDAO.getIdUserWith(idDeTai), qtth.getNoiDung(), null,
						idDeTai, null, null, null, 0);
				tbDAO.addItem(tb);
			}

			if (request.getParameter("duyet") != null) {
				System.out.println("DUYET KET QUA CUA NV!");
				DeTai detai = detaiDAO.getObjDeTai(idDeTai);
				String newTrangthai = LibraryConstant.DangChoXetThuyetMinh;
				if (detai.getTrangThai().equals(LibraryConstant.ChoHuy)) {
					newTrangthai = LibraryConstant.Huy;
				} else if (detai.getTrangThai().equals(LibraryConstant.ChoDeNghiChinhSuaDeXuat)) {
					newTrangthai = LibraryConstant.TruongDeXuatChinhSua;
				} else if (detai.getTrangThai().equals(LibraryConstant.DangChoDuyetCapTruong)) {
					newTrangthai = LibraryConstant.DangChoXetThuyetMinh;
				}
				
				if (detaiDAO.updateToTrangThai(newTrangthai, idDeTai) != 0) {
					System.out.println("Update Success!");
					response.sendRedirect(
							request.getContextPath() + "/admin/qldangkydetai/admin/duyet_de_xuat_ad?msg=1");
					return;
				} else {
					response.sendRedirect(
							request.getContextPath() + "/admin/qldangkydetai/admin/duyet_de_xuat_ad?msg=0");
					return;
				}
			} else if (request.getParameter("huy") != null) {
				System.out.println("HUY KET QUA CUA NV!");
				if (detaiDAO.updateToTrangThai(LibraryConstant.DangChoXetCapTruong, idDeTai) != 0) {
					System.out.println("Update Success!");
					response.sendRedirect(
							request.getContextPath() + "/admin/qldangkydetai/admin/duyet_de_xuat_ad?msg=1");
					return;
				} else {
					response.sendRedirect(
							request.getContextPath() + "/admin/qldangkydetai/admin/duyet_de_xuat_ad?msg=0");
					return;
				}
			}

		}
		if (request.getParameter("type") != null) {
			if (request.getParameter("type").equals("action")) {
				System.out.println("DUYET NHIEU DE XUAT");
				if (request.getParameter("idDeTai") != null) {
					String[] checkedId_str = request.getParameterValues("idDeTai");
					System.out.println("DUYET NHIEU ID DE TAI");
					int action = Integer.parseInt(request.getParameter("action"));
					DetaiDAO detaiDAO = new DetaiDAO();
					if (action == 1) {
						System.out.println("DUYET KET QUA CUA NV!");
						int result = 0;
						for (String string : checkedId_str) {
							System.out.println(string);
							int idDeTai = Integer.parseInt(string);
							DeTai detai = detaiDAO.getObjDeTai(idDeTai);
							String newTrangthai = LibraryConstant.DangChoXetThuyetMinh;
							if (detai.getTrangThai().equals(LibraryConstant.ChoHuy)) {
								newTrangthai = LibraryConstant.Huy;
							} else if (detai.getTrangThai().equals(LibraryConstant.ChoDeNghiChinhSuaDeXuat)) {
								newTrangthai = LibraryConstant.TruongDeXuatChinhSua;
							} else if (detai.getTrangThai().equals(LibraryConstant.DangChoDuyetCapTruong)) {
								newTrangthai = LibraryConstant.DangChoXetThuyetMinh;
							}
							result = detaiDAO.updateToTrangThai(newTrangthai, idDeTai);
							
						}
						if (result != 0) {
							System.out.println("Update Success!");
							response.sendRedirect(
									request.getContextPath() + "/admin/qldangkydetai/admin/duyet_de_xuat_ad?msg=1");
							return;
						} else {
							response.sendRedirect(
									request.getContextPath() + "/admin/qldangkydetai/admin/duyet_de_xuat_ad?msg=0");
							return;
						}
					} else {
						System.out.println("HUY KET QUA CUA NV!");
						int result = 0;
						for (String string : checkedId_str) {
							System.out.println(string);
							int idDeTai = Integer.parseInt(string);
							result = detaiDAO.updateToTrangThai(LibraryConstant.DangChoXetCapTruong, idDeTai);
							
						}
						if (result != 0) {
							System.out.println("Update Success!");
							response.sendRedirect(
									request.getContextPath() + "/admin/qldangkydetai/admin/duyet_de_xuat_ad?msg=1");
							return;
						} else {
							response.sendRedirect(
									request.getContextPath() + "/admin/qldangkydetai/admin/duyet_de_xuat_ad?msg=0");
							return;
						}
					}
					
				} else {
					response.sendRedirect(
							request.getContextPath() + "/admin/qldangkydetai/admin/duyet_de_xuat_ad?msg=3");
					return;
				}
				
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/qldangkydetai/admin/duyet_de_xuat_ad.jsp");
		rd.forward(request, response);
	}

}
