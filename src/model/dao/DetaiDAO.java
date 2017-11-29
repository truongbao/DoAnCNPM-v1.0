package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import library.LibraryConstant;
import model.bean.BieuMau;
import model.bean.CapDeTai;
import model.bean.DeTai;
import model.bean.LinhVucNC;
import model.bean.ThanhVien;

public class DetaiDAO {
	private ConnectMySQLLibrary connectMySQLLibrary;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;

	public DetaiDAO() {
		connectMySQLLibrary = new ConnectMySQLLibrary();
	}

	// lay ra danh sach de tai ko phan trang (public)
	public ArrayList<DeTai> getListDeTai() {
		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
	            + "  WHERE  dt.maSoDeTai != 'no' "
				+ "  order by dt.idDeTai DESC";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				DeTai objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));

				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;

	}
	
	
	
	
	
	
	// lay ra danh sach de tai phan trang (public) ứng vs user đang login
		public ArrayList<DeTai> getListDeTaiDK(int idUserLogin, int offset, int row_count) {
			ArrayList<DeTai> listDeTai = new ArrayList<>();
			conn = connectMySQLLibrary.getConnectMySQL();

			String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu FROM detai AS dt "
					+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
					+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
					+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
					+ "  WHERE  dt.maSoDeTai = 'no' and u.idUser = "+idUserLogin
			        + " ORDER BY dt.idDeTai ASC  LIMIT "+offset+", "+row_count;
			
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);

				while (rs.next()) {
					DeTai objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
							rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
							rs.getInt("idLoaiHinhNghienCuu"), "",
							rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
							rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
							rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
							rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
							rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
							rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa") );

					listDeTai.add(objDeTai);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return listDeTai;

		}
		
		
		//Lay ra chi tiet de tai ứng vs idDeTai đăng ký (public)
		public DeTai getObjectDeTaiByIdDeTaiDK(int idDeTai) {
			conn = connectMySQLLibrary.getConnectMySQL();

			String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu FROM detai AS dt "
					+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
					+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
					+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
					+ "  WHERE dt.trangThai = "+LibraryConstant.DangChoXetDeTai+"  and  dt.idDeTai = "+idDeTai;

			DeTai objDeTai =null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);

				if (rs.next()) {
					 objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
							rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
							rs.getInt("idLoaiHinhNghienCuu"), "",
							rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
							rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
							rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
							rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
							rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
							rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa") );

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return objDeTai;

		}	
		
		
		//Lay ra chi tiet de tai ứng vs idDeTai khi đã duyệt thuyết minh (public) (Quản lý đề tài)
		public DeTai getObjectDeTaiByIdDeTai(int idDeTai) {
			conn = connectMySQLLibrary.getConnectMySQL();

			String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu FROM detai AS dt "
					+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
					+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
					+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
					+ "  WHERE dt.trangThai = "+LibraryConstant.KhoaDeXuatChinhSua+"  and  dt.idDeTai = "+idDeTai;

			DeTai objDeTai =null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);

				if (rs.next()) {
					 objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
							rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
							rs.getInt("idLoaiHinhNghienCuu"), "",
							rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
							rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
							rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
							rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
							rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
							rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa") );

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return objDeTai;

		}	
				
		
		
		
		//Lay ra trangThai mới cập nhật ứng vs idDeTai vừa dk (public)
		public DeTai getTrangThaiUpdateByIdDeTaiDK(int idDeTai) {
			conn = connectMySQLLibrary.getConnectMySQL();

			String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu FROM detai AS dt "
					+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
					+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
					+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
					+ "  WHERE  dt.trangThai IN ('3','7','10','11')  and  dt.idDeTai = "+idDeTai;

			DeTai objDeTai =null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);

				if (rs.next()) {
					 objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
							rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
							rs.getInt("idLoaiHinhNghienCuu"), "",
							rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
							rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
							rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
							rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
							rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
							rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa") );

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return objDeTai;

		}	
		
	

	public ArrayList<DeTai> getListDeTai(int offset, int row_count) {
		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " order by dt.idDeTai DESC LIMIT ?,?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, row_count);
			rs = pst.executeQuery();
			while (rs.next()) {
				DeTai objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"),  rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));

				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;

	}

	public ArrayList<ThanhVien> getListThanhVienByDeTai(int idDeTai) {
		ArrayList<ThanhVien> listTVByDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "select tv.* , dt.idDeTai, dt.maSoDeTai from thanhvien AS tv  "
				+ "  INNER JOIN  detai AS dt ON dt.idDeTai = tv.idDeTai "
				+ "  where tv.idDeTai = " + idDeTai;

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				ThanhVien objTV = new ThanhVien(rs.getInt("idThanhVien"),rs.getString("tenThanhVien"),rs.getString("donVi"),
						                        rs.getString("noiDungNghienCuu"),rs.getInt("idDeTai"),rs.getString("maSoDeTai") );
				
				

				listTVByDeTai.add(objTV);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listTVByDeTai;

	}

	public DeTai getObjDeTai(int iDeTai) {

		conn = connectMySQLLibrary.getConnectMySQL();


		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.idDeTai = " + iDeTai;
		
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();
			if (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return objDeTai;
	}
	
	/* ***** FUNTIONS OF RIN PHAM ****** */

	public ArrayList<DeTai> getListDeTaiDangKy(int idKhoa, String keyword, int offset, int row_count) {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu, cdt.tenCapDeTai  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDeTai "
				+ " where dt.trangThai NOT IN ('13','14','15','16','12')";
		if(idKhoa > 0){
			sql +=" AND dt.idKhoa = " + idKhoa;
		}
		if(!"".equals(keyword)){
			sql+= " AND (u.fullname LIKE '%"+keyword+"%' OR dt.tenDeTai LIKE '%"+keyword+"%')";
		}
		sql +=" ORDER BY dt.idDeTai DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, row_count);
			rs = pst.executeQuery();

			while (rs.next()) {
				DeTai objDeTai  = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"),  rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	public int getSumDeTaiDangKy(int idKhoa, String keyword) {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(idDeTai) AS soluong  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " where dt.trangThai NOT IN ('13','14','15','16','12')";
		if(idKhoa > 0){
			sql +=" AND dt.idKhoa = " + idKhoa;
		}
		if(!"".equals(keyword)){
			sql+= " AND (u.fullname LIKE '%"+keyword+"%' OR dt.tenDeTai LIKE '%"+keyword+"%')";
		}
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sum;
	}
	
	
	
	public ArrayList<DeTai> getListDuyetDeTaiByIdKhoa(int idKhoa, int offset, int row_count) {
		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.idKhoa = ? and dt.trangThai = " + LibraryConstant.DangChoDuyetCapKhoa + " ORDER BY dt.idDeTai DESC LIMIT ?,?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idKhoa);
			pst.setInt(2, offset);
			pst.setInt(3, row_count);
			rs = pst.executeQuery();

			while (rs.next()) {
				DeTai objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"),  rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	public int getSumListDeTaiByStatus(int idFaculty, String status,String keyword, int idCDT) {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(idDeTai) AS soluong FROM detai AS dt"
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDeTai "
				+" WHERE dt.trangThai = ? ";
		if (idFaculty > 0){
			 sql += "AND dt.idKhoa = "+ idFaculty;
		}
		if(!"".equals(keyword)){
			sql+= " AND (u.fullname LIKE '%"+keyword+"%' OR dt.tenDeTai LIKE '%"+keyword+"%')";
		}
		if(idCDT > 0){
			sql += " AND dt.idCapDeTai = "+ idCDT;
		}
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, status);
			rs = pst.executeQuery();
			while (rs.next()) {
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {		
				pst.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sum;
	}
	
	//Đếm tổng số đề tại trong 1 trạng thái nào đó 
	public int countListDeTaiWith(String trangthai) {
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select count(*) AS total FROM detai WHERE trangthai = " + trangthai; 
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}
	//Đếm tổng số đề tài trong 1 trạng thái nào đó với hành động tìm kiếm 
	public int countListDeTaiSearchWith(String key, int khoa, String trangthai) {
		if ((key == "") && (khoa == 0)) return this.countListDeTaiWith(trangthai);
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select count(*) AS total FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ "WHERE dt.trangthai = " + trangthai; 
		if (key != "") {
			sql += " and ((tenDeTai like '%" 
				+ key 
				+ "%') or (fullName like '%" 
				+ key 
				+ "%'))";
		}
		
		if (khoa != 0){
			//String pre = key != ""  ? " and" : " and ";
			sql += " and " + " u.idKhoa = " + khoa + " ";
		}
		System.out.println("Search: "+sql);
        try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			 try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return total;
	}
	
	//Get mảng đề tài với trạng thái nào đó 
		public ArrayList<DeTai> getListDeTaiWith(String trangthai) {
			ArrayList<DeTai> listDeTai = new ArrayList<>();
			conn = connectMySQLLibrary.getConnectMySQL();
			String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
					+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
					+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
					+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
					+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
					+ " where dt.trangThai = " + trangthai + " ORDER BY dt.idDeTai ASC ";

			DeTai objDeTai = null;
			try {
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();

				while (rs.next()) {
					objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
							rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
							rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
							rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
							rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
							rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
							rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
							rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
							rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"),rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"),  rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
					listDeTai.add(objDeTai);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return listDeTai;
		}
		
	//Get mảng đề tài với trạng thái nào đó 
	public ArrayList<DeTai> getListDeTaiWith(String trangthai, int offset) {
		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + trangthai + " ORDER BY dt.idDeTai ASC LIMIT "+offset+","+LibraryConstant.ROW_COUNT;

		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"),rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	public ArrayList<DeTai> getListDeTaiSearchWith(int offset, String key, int khoa, String trangthai) {
		if ((key == "") && (khoa == 0)) return this.getListDeTaiWith(trangthai, offset);
		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + trangthai ;

		if (key != "") {
			sql += " and ((tenDeTai like '%" 
				+ key 
				+ "%') or (fullName like '%" 
				+ key 
				+ "%'))";
		}
		
		if (khoa != 0){
			//String pre = key != ""  ? " and" : " ";
			sql += " and " + " u.idKhoa = " + khoa + " ";
		}
		sql += " ORDER BY dt.idDeTai ASC LIMIT "+offset+","+LibraryConstant.ROW_COUNT;
		System.out.println("Search DT: "+sql);
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	//ADMIN GET LIST DE TAI O INDEX
	//Đếm tổng số đề tại trong 1 trạng thái nào đó 
		public int countListDeTaiAdmin(String trangthai1,String trangthai2, String trangthai3) {
			int total = 0;
			conn = connectMySQLLibrary.getConnectMySQL();
			String sql = "select count(*) AS total FROM detai WHERE trangthai = " + trangthai1
					+ " or trangthai = " + trangthai2 + " or trangthai = " + trangthai3;
			
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				if (rs.next()) {
					total = rs.getInt("total");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return total;
		}
		//Đếm tổng số đề tài trong 1 trạng thái nào đó với hành động tìm kiếm 
		public int countListDeTaiSearchAdmin(String key, int khoa, String trangthai1,String trangthai2, String trangthai3) {
			if ((key == "") && (khoa == 0)) return this.countListDeTaiAdmin(trangthai1, trangthai2, trangthai3);
			int total = 0;
			conn = connectMySQLLibrary.getConnectMySQL();
			String sql = "select count(*) AS total FROM detai AS dt "
					+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
					+ "WHERE (dt.trangthai = " + trangthai1 + " or trangthai = " + trangthai2 + " or trangthai = " + trangthai3 + " )"; 
			if (key != "") {
				sql += " and ((tenDeTai like '%" 
					+ key 
					+ "%') or (fullName like '%" 
					+ key 
					+ "%'))";
			}
			
			if (khoa != 0){
				//String pre = key != ""  ? " and" : " and ";
				sql += " and " + " u.idKhoa = " + khoa + " ";
			}
			System.out.println("Search: "+sql);
	        try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				if(rs.next()){
					total = rs.getInt("total");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				 try {
					rs.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return total;
		}
		
			
		//Get mảng đề tài với trạng thái nào đó 
		public ArrayList<DeTai> getListDeTaiAdmin(String trangthai1,String trangthai2, String trangthai3, int offset) {
			ArrayList<DeTai> listDeTai = new ArrayList<>();
			conn = connectMySQLLibrary.getConnectMySQL();
			String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
					+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
					+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
					+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
					+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
					+ " where dt.trangThai = " + trangthai1 + " or trangthai = " + trangthai2 + " or trangthai = " + trangthai3
					+ " ORDER BY dt.idDeTai ASC LIMIT "+offset+","+LibraryConstant.ROW_COUNT;

			// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
			// ASC";
			System.out.println(sql);
			DeTai objDeTai = null;
			try {
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();

				while (rs.next()) {
					objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
							rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
							rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
							rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
							rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
							rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
							rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
							rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
							rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"),rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
					listDeTai.add(objDeTai);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return listDeTai;
		}
		
		public ArrayList<DeTai> getListDeTaiSearchAdmin(int offset, String key, int khoa, String trangthai1,String trangthai2, String trangthai3) {
			if ((key == "") && (khoa == 0)) return this.getListDeTaiAdmin(trangthai1, trangthai2, trangthai3, offset);
			ArrayList<DeTai> listDeTai = new ArrayList<>();
			conn = connectMySQLLibrary.getConnectMySQL();
			String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
					+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
					+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
					+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
					+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
					+ " where (dt.trangThai = " + trangthai1 + " or trangthai = " + trangthai2 + " or trangthai = " + trangthai3 + " )" ;

			// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
			// ASC";
			if (key != "") {
				sql += " and ((tenDeTai like '%" 
					+ key 
					+ "%') or (fullName like '%" 
					+ key 
					+ "%'))";
			}
			
			if (khoa != 0){
				//String pre = key != ""  ? " and" : " ";
				sql += " and " + " u.idKhoa = " + khoa + " ";
			}
			sql += " ORDER BY dt.idDeTai ASC LIMIT "+offset+","+LibraryConstant.ROW_COUNT;
			System.out.println("Search DT: "+sql);
			DeTai objDeTai = null;
			try {
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();

				while (rs.next()) {
					objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
							rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
							rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
							rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
							rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
							rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
							rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
							rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
							rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
					listDeTai.add(objDeTai);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					pst.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return listDeTai;
		}
	//Đếm tổng số đề tại trong 1 trạng thái nào đó 
	public int countListDKDeTai() {
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select count(*) AS total FROM detai AS dt"
		+ " where dt.trangThai = " + LibraryConstant.DangChoXetCapTruong 
		+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetCapTruong
		+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSua
		+ " or dt.trangThai = " + LibraryConstant.DangChoXetThuyetMinh
		+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetThuyetMinh
		+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSuaThuyetMinh;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
	}
	//Đếm tổng số đề tài trong 1 trạng thái nào đó với hành động tìm kiếm 
	public int countListDKDeTaiSearchWith(String key, int khoa) {
		if ((key == "") && (khoa == 0)) return this.countListDKDeTai();
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select count(*) AS total FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " where (dt.trangThai = " + LibraryConstant.DangChoXetCapTruong 
				+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetCapTruong
				+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSua
				+ " or dt.trangThai = " + LibraryConstant.DangChoXetThuyetMinh
				+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetThuyetMinh
				+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSuaThuyetMinh + ") "; 
		if (key != "") {
			sql += " and ((tenDeTai like '%" 
				+ key 
				+ "%') or (fullName like '%" 
				+ key 
				+ "%'))";
		}
		
		if (khoa != 0){
			//String pre = key != ""  ? " and" : " and ";
			sql += " and (" + " u.idKhoa = " + khoa + ") ";
		}
		System.out.println("Search: "+sql);
        try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if(rs.next()){
				total = rs.getInt("total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			 try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return total;
	}
	public ArrayList<DeTai> getListDangKyDeTai(int offset) {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai ,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangChoXetCapTruong 
				+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetCapTruong
				+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSua
				+ " or dt.trangThai = " + LibraryConstant.DangChoXetThuyetMinh
				+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetThuyetMinh
				+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSuaThuyetMinh + " ORDER BY dt.idDeTai ASC LIMIT "+offset+","+LibraryConstant.ROW_COUNT;

		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	public ArrayList<DeTai> getListDangKyDeTaiSearch(int offset, String key, int khoa) {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where (dt.trangThai = " + LibraryConstant.DangChoXetCapTruong 
				+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetCapTruong
				+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSua
				+ " or dt.trangThai = " + LibraryConstant.DangChoXetThuyetMinh
				+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetThuyetMinh
				+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSuaThuyetMinh + ") ";
		if (key != "") {
			sql += " and ((tenDeTai like '%" 
				+ key 
				+ "%') or (fullName like '%" 
				+ key 
				+ "%'))";
		}
		
		if (khoa != 0){
			//String pre = key != ""  ? " and" : " ";
			sql += " and " + " (u.idKhoa = " + khoa + ") ";
		}
		sql += " ORDER BY dt.idDeTai ASC LIMIT "+offset+","+LibraryConstant.ROW_COUNT;
		System.out.println("Search DKDT: "+sql);
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	public ArrayList<DeTai> getListDuyetDeTaiAdmin() {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangChoDuyetCapTruong + " ORDER BY dt.idDeTai ASC";
		
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	public ArrayList<DeTai> getListDuyetThuyetMinhAdmin() {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangChoDuyetThuyetMinh + " ORDER BY dt.idDeTai ASC";
		
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	public ArrayList<DeTai> getListDeTaiThucHien(int idKhoa, String keyword, int offset, int row_count) {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu, cdt.tenCapDeTai  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDeTai "
				+ " where dt.trangThai IN ('13','14','15','16','12')";
		if(idKhoa > 0){
			sql +=" AND dt.idKhoa = " + idKhoa;
		}
		if(!"".equals(keyword)){
			sql+= " AND (u.fullname LIKE '%"+keyword+"%' OR dt.tenDeTai LIKE '%"+keyword+"%')";
		}
		sql +=" ORDER BY dt.idDeTai DESC LIMIT ?,?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, row_count);
			rs = pst.executeQuery();

			while (rs.next()) {
				DeTai objDeTai  = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"),  rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	public int getSumDeTaiThucHien(int idKhoa, String keyword) {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(idDeTai) AS soluong  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " where dt.trangThai IN ('13','14','15','16','12')";
		if(idKhoa > 0){
			sql +=" AND dt.idKhoa = " + idKhoa;
		}
		if(!"".equals(keyword)){
			sql+= " AND (u.fullname LIKE '%"+keyword+"%' OR dt.tenDeTai LIKE '%"+keyword+"%')";
		}

		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sum;
	}
	
	
	public ArrayList<DeTai> getListDuyetNghiemThuAdmin() {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangChoDuyetNghiemThu + " ORDER BY dt.idDeTai ASC";
		
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	//Cap nhat trang thai cho de tai
	public int updateToTrangThai(String trangthai, int id) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "update detai SET trangThai = " + trangthai + " WHERE idDeTai = " + id ;
		try {
			pst = conn.prepareStatement(sql);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//Cap nhat trang thai cho nhiều de tai
	public int updateToTrangThai(String trangthai, String strs) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "update detai SET trangThai = " + trangthai + " WHERE idDeTai IN ("+strs+")" ;
		try {
			pst = conn.prepareStatement(sql);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int getIdUserWith(int idDeTai) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select idUser FROM detai WHERE idDeTai = " + idDeTai;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				result = rs.getInt("idUser");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/* *****END FUNTIONS OF RIN PHAM ****** */
	
	//lay ra danh sách linh vuc nghien cuu ko phan trang (public)
	public ArrayList<LinhVucNC> getListLinhVucNC(){
		ArrayList<LinhVucNC> listLinhVucNC = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select * FROM linhvucnghiencuu ORDER BY idLinhVucNghienCuu DESC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				LinhVucNC objLVNC = new LinhVucNC(rs.getInt("idLinhVucNghienCuu") ,rs.getString("tenLinhVucNghienCuu") );
			   listLinhVucNC.add(objLVNC);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listLinhVucNC;
		
	}
	
	
	//lay ra danh sách cap de tai ko phan trang (public)
	public ArrayList<CapDeTai> getListCapDeTai(){
			ArrayList<CapDeTai> listCapDeTai = new ArrayList<>();
			conn = connectMySQLLibrary.getConnectMySQL();
			
			String sql = "select * FROM capdetai ORDER BY idCapDeTai DESC";
			
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				
				while(rs.next()){
					CapDeTai objCapDeTai = new CapDeTai(rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"));
					listCapDeTai.add(objCapDeTai);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					st.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return listCapDeTai;
			
		}
	
	
	

	/*
	 * //lay ra danh sÃ¡ch hoc vi ko phan trang (public) public ArrayList<HocVi>
	 * getListHocVi(){ ArrayList<HocVi> listHocVi = new ArrayList<>(); conn =
	 * connectMySQLLibrary.getConnectMySQL();
	 * 
	 * String sql = "select * FROM hocvi ORDER BY idHocVi ASC";
	 * 
	 * try { st = conn.createStatement(); rs = st.executeQuery(sql);
	 * 
	 * while(rs.next()){ HocVi objHocVi = new HocVi(rs.getInt("idHocVi")
	 * ,rs.getString("tenHocVi") ); listHocVi.add(objHocVi); } } catch
	 * (SQLException e) { e.printStackTrace(); }finally{ try { st.close();
	 * conn.close(); } catch (SQLException e) { e.printStackTrace(); } } return
	 * listHocVi;
	 * 
	 * }
	 * 
	 * 
	 * //lay ra danh sÃ¡ch Khoa ko phan trang (public) public ArrayList<Khoa>
	 * getListKhoa(){ ArrayList<Khoa> listKhoa = new ArrayList<>(); conn =
	 * connectMySQLLibrary.getConnectMySQL();
	 * 
	 * String sql = "select * FROM khoa ORDER BY idKhoa ASC";
	 * 
	 * try { st = conn.createStatement(); rs = st.executeQuery(sql);
	 * 
	 * while(rs.next()){ Khoa objKhoa = new Khoa(rs.getInt("idKhoa")
	 * ,rs.getString("tenKhoa") ); listKhoa.add(objKhoa); } } catch
	 * (SQLException e) { e.printStackTrace(); }finally{ try { st.close();
	 * conn.close(); } catch (SQLException e) { e.printStackTrace(); } } return
	 * listKhoa;
	 * 
	 * }
	 * 
	 * 
	 * public User getObjUser(int idUser) {
	 * 
	 * conn = connectMySQLLibrary.getConnectMySQL();
	 * 
	 * String sql =
	 * "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocVi from user AS u "
	 * +
	 * " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
	 * + " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa " +
	 * " INNER JOIN  hocvi AS hv ON hv.idHocVi = u.idHocVi where u.idUser = "
	 * +idUser ;
	 * 
	 * 
	 * User objUser = null; try { pst = conn.prepareStatement(sql);
	 * 
	 * rs = pst.executeQuery(); if(rs.next()){ objUser = new
	 * User(rs.getInt("idUser"),rs.getString("fullName"),rs.getString(
	 * "chucDanhKhoaHoc") ,rs.getString("diaChiCoQuan") ,
	 * rs.getString("dienThoaiCoQuan"),rs.getInt("idHocVi")
	 * ,rs.getString("tenHocVi"),rs.getString("namSinh")
	 * ,rs.getString("diaChiNhaRieng") , rs.getString("dienThoaiNhaRieng")
	 * ,rs.getString("email") ,rs.getString("fax"),rs.getString("userName") ,
	 * rs.getString("matKhau")
	 * ,rs.getInt("idLoaiTaiKhoan"),rs.getString("tenLoaiTaiKhoan")
	 * ,rs.getInt("idKhoa"), rs.getString("tenKhoa") ); }
	 * 
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }finally{ try {
	 * pst.close(); conn.close(); } catch (SQLException e) {
	 * e.printStackTrace(); } }
	 * 
	 * return objUser; }
	 * 
	 * 
	 * public int editItem(User objUser) { int result = 0;
	 * 
	 * conn = connectMySQLLibrary.getConnectMySQL();
	 * 
	 * String sql =
	 * "UPDATE user SET fullname = ?, matKhau = ?, email = ?, idKhoa = ?,idHocVi = ?, "
	 * +
	 * " idLoaiTaiKhoan = ?,chucDanhKhoaHoc = ?, diaChiCoQuan = ?,diaChiNhaRieng = ?,fax = ?, "
	 * + " dienThoaiCoQuan = ?,dienThoaiNhaRieng = ?,namSinh = ? " +
	 * " WHERE idUser = ?";
	 * 
	 * try { pst = conn.prepareStatement(sql); pst.setString(1,
	 * objUser.getFullName()); pst.setString(2, objUser.getMatKhau());
	 * pst.setString(3, objUser.getEmail()); pst.setInt(4, objUser.getIdKhoa());
	 * pst.setInt(5, objUser.getIdHocVi()); pst.setInt(6,
	 * objUser.getIdLoaiTaiKhoan()); pst.setString(7,
	 * objUser.getChucDanhKhoaHoc() ); pst.setString(8,
	 * objUser.getDiaChiCoQuan()); pst.setString(9,
	 * objUser.getDiaChiNhaRieng()); pst.setString(10, objUser.getFax());
	 * pst.setString(11, objUser.getDienThoaiCoQuan()); pst.setString(12,
	 * objUser.getDienThoaiNhaRieng()); pst.setString(13, objUser.getNamSinh());
	 * pst.setInt(14, objUser.getIdUser());
	 * 
	 * result = pst.executeUpdate(); } catch (SQLException e) {
	 * e.printStackTrace(); }finally{ try { pst.close(); conn.close(); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 * 
	 * return result; }
	 * 
	 */

	/*
	 * Author: Quoc Create date: 22/10/2017
	 */

	// get list DeTai of Faculty (idFaculty)
	public ArrayList<DeTai> getListByFaculty(int idKhoa, int offset, int row_count) {
		ArrayList<DeTai> listObj = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "SELECT *,cdt.tenCapDeTai FROM detai AS dt "
				+ " JOIN linhvucnghiencuu AS lvnc ON dt.idLinhVucNghienCuu = lvnc.idLinhVucNghienCuu "
				+ " JOIN loaihinhnghiencuu AS lhnc ON dt.idLoaiHinhNghienCuu = lhnc.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " JOIN user ON dt.idUser = user.idUser WHERE dt.idKhoa = ? ORDER BY dt.idDeTai DESC LIMIT ?, ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idKhoa);
			pst.setInt(2, offset);
			pst.setInt(3, row_count);
			rs = pst.executeQuery();
			while (rs.next()) {
				DeTai objDetai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), idKhoa, rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listObj.add(objDetai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listObj;
	}
	
	public ArrayList<DeTai> getSearchList(int year, String type_detai, String type_stat, int offset, int rowCount) {
		ArrayList<DeTai> listObj = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "SELECT *,cdt.tenCapDeTai FROM detai AS dt JOIN linhvucnghiencuu AS lvnc ON dt.idLinhVucNghienCuu = lvnc.idLinhVucNghienCuu"
				+ " JOIN loaihinhnghiencuu AS lhnc ON dt.idLoaiHinhNghienCuu = lhnc.idLoaiHinhNghienCuu JOIN user ON dt.idUser"
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " = user.idUser WHERE";
		sql = getSqlWithSearch(sql, year, type_detai, type_stat) + " ORDER BY dt.idDeTai DESC LIMIT ?, ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, rowCount);
			rs = pst.executeQuery();
			while (rs.next()) {
				DeTai objDetai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listObj.add(objDetai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listObj;
	}

	
	// get the sum of DeTai
	public int getSum() {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(idDeTai) AS soluong FROM detai";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sum;
	}
	
	// get list DeTai of Faculty with search
	public ArrayList<DeTai> getSearchListByFaculty(int idFaculty, int year, String type_detai, String type_stat,
			int offset, int rowCount) {
		ArrayList<DeTai> listObj = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "SELECT *,cdt.tenCapDeTai FROM detai AS dt JOIN linhvucnghiencuu AS lvnc ON dt.idLinhVucNghienCuu = lvnc.idLinhVucNghienCuu"
				+ " JOIN loaihinhnghiencuu AS lhnc ON dt.idLoaiHinhNghienCuu = lhnc.idLoaiHinhNghienCuu JOIN user ON dt.idUser"
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " = user.idUser WHERE dt.idKhoa = ?  AND";
		sql = getSqlWithSearch(sql, year, type_detai, type_stat) + " ORDER BY dt.idDeTai DESC LIMIT ?, ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idFaculty);
			pst.setInt(2, offset);
			pst.setInt(3, rowCount);
			rs = pst.executeQuery();
			while (rs.next()) {
				DeTai objDetai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), idFaculty, rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listObj.add(objDetai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listObj;
	}

	// get the sum of DeTai of faculty (idFaculty)
	public int getSumWithIdFaculty(int idFaculty) {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(idDeTai) AS soluong FROM detai WHERE idKhoa = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idFaculty);
			rs = pst.executeQuery();
			while (rs.next()) {
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sum;
	}

	// get the sum of DeTai of Faculty with keyword search
	public int getSumWithIdFacultyAndSearch(int idFaculty, int year, String type_detai, String type_stat) {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(idDeTai) AS soluong FROM detai WHERE idKhoa = ? AND";
		sql = getSqlWithSearch(sql, year, type_detai, type_stat);
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idFaculty);
			rs = pst.executeQuery();
			while (rs.next()) {
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sum;

	}

	// get the sum of DeTai with keyword search
	public int getSumWithSearch(int year, String type_detai, String type_stat) {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(idDeTai) AS soluong FROM detai WHERE ";
		sql = getSqlWithSearch(sql, year, type_detai, type_stat);
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sum;

	}

	// get sql command with keyword search
	public String getSqlWithSearch(String sql, int year, String type_detai, String type_stat) {
		if (year == 0 && "0".equals(type_stat) && "0".equals(type_detai)) { // have not search keyword
			if(sql.endsWith("AND"))
				return sql.substring(0, sql.length()-3);
//			}else if(sql.endsWith("WHERE")){
//				return sql.substring(0, sql.length()-4);
//			}
			return sql;
		} else if (year == 0 && "0".equals(type_detai)) { // search by trangThai
			return sql + " trangThai = '" + type_stat + "'";
		} else if (year == 0 && "0".equals(type_stat)) { // search by capDeTai
			return sql + " capDeTai = '" + type_detai + "'";
		} else if ("0".equals(type_stat) && "0".equals(type_detai)) { // search
																		// by
																		// thoiGianDangKy
			return sql + " YEAR(thoiGianDangKy) = " + year + "";
		} else if ("0".equals(type_detai)) {
			return sql + " YEAR(thoiGianDangKy) = " + year + " AND trangThai = '" + type_stat + "'";
		} else if ("0".equals(type_stat)) {
			return sql + " YEAR(thoiGianDangKy) = " + year + " AND capDeTai = '" + type_detai + "'";
		} else if (year == 0) {
			return sql + " trangThai = '" + type_stat + "' AND capDeTai = '" + type_detai + "'";
		}
		return sql + " YEAR(thoiGianDangKy) = " + year + " AND trangThai = '" + type_stat + "' AND capDeTai = '"
				+ type_detai + "'";
	}
	
	
	public int addDeTaiPublic(DeTai objDeTai) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
		
		String sql="insert into detai (tenDeTai,idLinhVucNghienCuu,idUser,tinhCapThiet,"
				+ " mucTieu,noiDung,sanPham,hieuQua,kinhPhiThucHien,trangThai,idCapDeTai,idKhoa ) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objDeTai.getTenDeTai());
			pst.setInt(2, objDeTai.getIdLinhVucNghienCuu());
			pst.setInt(3, objDeTai.getIdUser());
			pst.setString(4, objDeTai.getTinhCapThiet());
			pst.setString(5, objDeTai.getMucTieu());
			pst.setString(6, objDeTai.getNoiDung());
			pst.setString(7, objDeTai.getSanPham());
			pst.setString(8, objDeTai.getHieuQua());
			pst.setInt(9, objDeTai.getKinhPhiThucHien());
			pst.setString(10, objDeTai.getTrangThai());
			pst.setInt(11, objDeTai.getIdCapDeTai());
			pst.setInt(12, objDeTai.getIdKhoa());
			
			
			result = pst.executeUpdate();
			
	 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	//sửa đề tài trong thời gian đăng ký
	public int editDeTaiPublic(DeTai objDeTai) {
         
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql="UPDATE detai SET tenDeTai = ? ,idLinhVucNghienCuu = ?,idUser = ?,tinhCapThiet = ?, "
				+ " mucTieu = ?,noiDung = ?,sanPham = ? ,hieuQua = ?,kinhPhiThucHien = ? ,idKhoa = ? , idCapDeTai = ? "
				+ " where idDeTai = ? ";

		try {
            pst = conn.prepareStatement(sql);
			
			pst.setString(1, objDeTai.getTenDeTai());
			pst.setInt(2, objDeTai.getIdLinhVucNghienCuu());
			pst.setInt(3, objDeTai.getIdUser());
			pst.setString(4, objDeTai.getTinhCapThiet());
			pst.setString(5, objDeTai.getMucTieu());
			pst.setString(6, objDeTai.getNoiDung());
			pst.setString(7, objDeTai.getSanPham());
			pst.setString(8, objDeTai.getHieuQua());
			pst.setInt(9, objDeTai.getKinhPhiThucHien());
			pst.setInt(10, objDeTai.getIdKhoa());
			pst.setInt(11, objDeTai.getIdCapDeTai());
			pst.setInt(12, objDeTai.getIdDeTai());
			
			
			result = pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	
	
	

	//get list detai by trangThai
	public ArrayList<DeTai> getListDeTaiByStatus(int idKhoa, String status, String keyword,int idCDT, int offset, int row_count){
		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu, cdt.tenCapDeTai  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDeTai "
				+ " where dt.trangThai = ? ";
		if (idKhoa != 0){
			sql += " AND dt.idKhoa = "+ idKhoa;
		}
		if(!"".equals(keyword)){
			sql+= " AND (u.fullname LIKE '%"+keyword+"%' OR dt.tenDeTai LIKE '%"+keyword+"%')";
		}
		if(idCDT > 0){
			sql += " AND dt.idCapDeTai = "+ idCDT;
		}
		sql += " ORDER BY dt.idDeTai DESC LIMIT ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, status);
			pst.setInt(2, offset);
			pst.setInt(3, row_count);
			rs = pst.executeQuery();

			while (rs.next()) {
				DeTai objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("danhGiaNghiemThu"),rs.getFloat("diem"),rs.getString("xepLoai"));
				listDeTai.add(objDeTai);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDeTai;
	}
	
	
	//đếm số đề tài đã đăng ký (nghĩa là maSoDeTai = 'no' )
     public int countDeTaiDKPublic(int idUserLogin) {
		
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "SELECT COUNT(*) AS Total FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " where dt.maSoDeTai = 'no' and u.idUser = "+idUserLogin;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				total = rs.getInt("Total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
		
	}
     
     
   //đếm số đề tài đã co ma so (nghĩa là da duyet thuyet minh )
     public int countDeTaiCoMaSoPublic(int idUserLogin) {
		
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "SELECT COUNT(*) AS Total FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " where dt.maSoDeTai != 'no' and u.idUser = "+idUserLogin;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()){
				total = rs.getInt("Total");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return total;
		
	}
      
     
     
  // lay ra danh sach de tai co phan trang (public) khi da co ma so
 	public ArrayList<DeTai> getListDeTaiPagingPublic(int idUserLogin, int offset,int row_count ) {
 		ArrayList<DeTai> listDeTai = new ArrayList<>();
 		conn = connectMySQLLibrary.getConnectMySQL();

 		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu FROM detai AS dt "
 				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
 				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
 				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
 				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
 	            + "  WHERE  dt.maSoDeTai != 'no' and u.idUser = "+idUserLogin
 				+ "  order by dt.idDeTai DESC  LIMIT "+offset+", "+row_count;
 		try {
 			st = conn.createStatement();
 			rs = st.executeQuery(sql);

 			while (rs.next()) {
 				DeTai objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
 						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
 						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
 						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
 						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
 						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
 						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
 						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
 						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getInt("idCapDeTai"),rs.getString("tenCapDeTai"),
 						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa") );

 				listDeTai.add(objDeTai);
 			}
 		} catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			try {
 				st.close();
 				conn.close();
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		}
 		return listDeTai;

 	}

	public int updateKQNghiemThu(int idDeTai, String content, float score, String xepLoai) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "UPDATE detai SET danhGiaNghiemThu = ?, diem = ?, xepLoai = ?, trangThai = ? WHERE idDeTai = ?";
		System.out.println(sql);
		try {
 			pst = conn.prepareStatement(sql);
 			pst.setString(1, content);
 			pst.setFloat(2, score);
 			pst.setString(3, xepLoai);
 			pst.setString(4, LibraryConstant.DangChoDuyetNghiemThu);
 			pst.setInt(5, idDeTai);
 			result = pst.executeUpdate();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		} finally {
 			try {
 				pst.close();
 				conn.close();
 			} catch (SQLException e) {
 				e.printStackTrace();
 			}
 		}
		return result;
	}
	
	

	
}
