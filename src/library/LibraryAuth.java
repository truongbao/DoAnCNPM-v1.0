package library;

import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.dao.DetaiDAO;
import model.dao.ThoiGianDKDAO;
public class LibraryAuth {

	public static boolean CheckLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// kiểm tra đã đăng nhập chưa
		// true : login roi
		// fail : chua
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null && session.getAttribute("quanLyNCKHKhoa") == null
				&& session.getAttribute("nhanVienQLNCKHTruong") == null) {// chưa
																			// đăng
																			// nhâp
			// chuyen huong
			try {
				response.sendRedirect(request.getContextPath() + "/auth/admin/login");
			} catch (IOException e) {
				System.out.println(e);
				e.printStackTrace();
			}
			return false;
		}

		return true;
	}
	
	public static void CheckThoiGianChuyenTrangThaiDeTai() {
		ThoiGianDKDAO thoiGianDKDAO = new ThoiGianDKDAO();
		Date thoiGianKetThucDKDeXuat = thoiGianDKDAO.getItem(1).getThoiGianKetThuc();
		
		Date thoiGianKetThucThuyetMinh = thoiGianDKDAO.getItem(2).getThoiGianKetThuc();
		Date now = new Date();
		if (now.after(thoiGianKetThucDKDeXuat)) {
			DetaiDAO detaiDAO = new DetaiDAO();
			detaiDAO.changeTrangThaiDangChoDuyetDeXuat();
		}
		if (now.after(thoiGianKetThucThuyetMinh)) {
			DetaiDAO detaiDAO = new DetaiDAO();
			detaiDAO.changeTrangThaiDangChoDuyetThuyetMinh();
		}
		return;
	}

	public static boolean CheckLoginPublic(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// kiểm tra đã đăng nhập chưa
		// true : login roi
		// fail : chua
		HttpSession session = request.getSession();
		if (session.getAttribute("sobjUserPublic") == null) {// chưa đăng nhâp
			// chuyen huong
			response.sendRedirect(request.getContextPath() + "/auth/public/login");
			return false;
		}

		return true;
	}

	public static boolean CheckAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Kiểm tra đã đăng nhập chưa
		// true : login roi
		// fail : chua
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null) {// chưa đăng nhâp
			// chuyen huong
			response.sendRedirect(request.getContextPath() + "/auth/admin/login");
			return false;
		}
		return true;
	}

	public static boolean CheckQuanLyKhoa(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// kiểm tra đã đăng nhập chưa
		// true : login roi
		// fail : chua
		HttpSession session = request.getSession();
		if (session.getAttribute("quanLyNCKHKhoa") == null) {// chưa đăng nhâp
			// chuyen huong
			response.sendRedirect(request.getContextPath() + "/auth/admin/login");
			return false;
		}

		return true;
	}

	public static boolean CheckNhanVienTruong(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		// kiểm tra đã đăng nhập chưa
		// true : login roi
		// fail : chua
		HttpSession session = request.getSession();
		if (session.getAttribute("nhanVienQLNCKHTruong") == null && session.getAttribute("admin") == null) {// chưa đăng
																	// nhâp
			// chuyen huong
			response.sendRedirect(request.getContextPath() + "/auth/admin/login");
			return false;
		}

		return true;
	}

}
