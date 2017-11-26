package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import library.LibraryConstant;
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
				+ " order by dt.idDeTai DESC";
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));

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
	
	
	// lay ra danh sach de tai ko phan trang (public)
		public ArrayList<DeTai> getListDeTaiDK() {
			ArrayList<DeTai> listDeTai = new ArrayList<>();
			conn = connectMySQLLibrary.getConnectMySQL();

			String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu FROM detai AS dt "
					+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
					+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
					+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
					+ "  WHERE  dt.maSoDeTai = 'no' ";
			
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
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));

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
		
		
		//Lay ra chi tiet de tai ứng vs idDeTai (public)
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
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));

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
		
		
		
		//Lay ra lay ra trangThai mới cập nhật ứng vs idDeTai vừa dk (public)
		public DeTai getTrangThaiUpdateUpdByIdDeTaiDK(int idDeTai) {
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
							rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));

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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload") );

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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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

	public ArrayList<DeTai> getListDeTaiByIdKhoa(int idKhoa) {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.idKhoa = ? ORDER BY dt.idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idKhoa);
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
	
	public ArrayList<DeTai> getListDuyetDeTaiByIdKhoa(int idKhoa) {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.idKhoa = ? and dt.trangThai = " + LibraryConstant.DangChoDuyetCapKhoa + " ORDER BY dt.idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idKhoa);
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
	
	public ArrayList<DeTai> getListDuyetDeTaiNhanVien() {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangChoXetCapTruong + " ORDER BY dt.idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
	
	public ArrayList<DeTai> getListDuyetThuyetMinhNhanVien() {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai ,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangChoXetThuyetMinh + " ORDER BY dt.idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
	
	public ArrayList<DeTai> getListDangKyDeTai() {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangChoXetCapTruong 
				+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetCapTruong
				+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSua
				+ " or dt.trangThai = " + LibraryConstant.DangChoXetThuyetMinh
				+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetThuyetMinh
				+ " or dt.trangThai = " + LibraryConstant.TruongDeXuatChinhSuaThuyetMinh + " ORDER BY dt.idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
	
	public ArrayList<DeTai> getListDeTaiDangTH() {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangThucHien 
				+ " or dt.trangThai = " + LibraryConstant.DangChoXetNghiemThu
				+ " or dt.trangThai = " + LibraryConstant.DangChoDuyetNghiemThu
				+ " or dt.trangThai = " + LibraryConstant.DaHoanThanh
				+ " or dt.trangThai = " + LibraryConstant.KhongHoanThanh
			    + " ORDER BY dt.idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
	
	public ArrayList<DeTai> getListDuyetNghiemThuNhanVien() {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangChoXetNghiemThu + " ORDER BY dt.idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
	
	public ArrayList<DeTai> getListDuyetNghiemThuAdmin() {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.trangThai = " + LibraryConstant.DangChoDuyetNghiemThu + " ORDER BY dt.idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
						rs.getTimestamp("thoiGianDangKy"), idKhoa, rs.getString("linkUpload"));
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
		System.out.println(sql);
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
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
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
						rs.getTimestamp("thoiGianDangKy"), idFaculty, rs.getString("linkUpload"));
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
				+ " mucTieu,noiDung,sanPham,hieuQua,kinhPhiThucHien,trangThai,idKhoa ) values(?,?,?,?,?,?,?,?,?,?,?)";
		
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
			pst.setInt(11, objDeTai.getIdKhoa());
			
			
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
				+ " mucTieu = ?,noiDung = ?,sanPham = ? ,hieuQua = ?,kinhPhiThucHien = ? ,idKhoa = ? "
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
			pst.setInt(11, objDeTai.getIdDeTai());
			
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
	
	
	
	public int UploadFileThuyetMinh(DeTai objDeTai) {
        
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql="UPDATE detai SET linkUpload = ?  "
				+ " where idDeTai = ? ";

		try {
            pst = conn.prepareStatement(sql);
			
			pst.setString(1, objDeTai.getLinkUpload());
			
			pst.setInt(2, objDeTai.getIdDeTai());
			
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
