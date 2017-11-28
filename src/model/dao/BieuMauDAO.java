package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import library.ConnectMySQLLibrary;
import model.bean.BieuMau;

public class BieuMauDAO {
	private ConnectMySQLLibrary connectMySQLLibrary;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;

	public BieuMauDAO() {
		connectMySQLLibrary = new ConnectMySQLLibrary();
	}
	
	
	public BieuMau getObjectBieuMau(String maBieuMau,int idDeTai) {

		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "select * from uploadbieumau where idDeTai = ? and maBieuMau = ? ";
		
		BieuMau objBieuMau = null;
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, idDeTai);
			pst.setString(2, maBieuMau);

			rs = pst.executeQuery();
			if (rs.next()) {
				objBieuMau = new BieuMau(rs.getInt("idBieuMau") ,rs.getInt("idDeTai") , 
						                 rs.getString("maBieuMau") ,rs.getString("linkBieuMau"));
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

		return objBieuMau;
	}
	
	
	
     public int UploadFileThuyetMinh(BieuMau objBM) {
        
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql="insert into uploadbieumau(idDeTai,maBieuMau,linkBieuMau ) values(?,?,?) ";
				

		try {
            pst = conn.prepareStatement(sql);
			
			pst.setInt(1, objBM.getIdDeTai());
			pst.setString(2, objBM.getMaBieuMau());
			pst.setString(3, objBM.getLinkBieuMau());
			
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
     
     
     
     public int UploadFileThuyetMinhDaChinhSua(BieuMau objBM) {
         
 		int result = 0;
 		conn = connectMySQLLibrary.getConnectMySQL();
 		String sql=" UPDATE uploadbieumau SET linkBieuMau = ?  WHERE idDeTai = ? and maBieuMau = ?  ";
 		try {
             pst = conn.prepareStatement(sql);
 			 pst.setString(1, objBM.getLinkBieuMau());
 			 pst.setInt(2, objBM.getIdDeTai());
 			 pst.setString(3, objBM.getMaBieuMau());
 			
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
	
	
	

/*	// lay ra danh sach de tai ko phan trang (public)
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
	
	
	

	public DeTai getObjDeTai(int iDeTai) {

		conn = connectMySQLLibrary.getConnectMySQL();


		String sql = "select dt.*,cdt.tenCapDeTai,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " INNER JOIN capdetai AS cdt ON cdt.idCapDeTai = dt.idCapDetai "
				+ " where dt.idDeTai = " + iDeTai;
		System.out.println(sql);
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
	
	
	
	public int UploadFileThuyetMinh(BieuMau objBM) {
        
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();

		
		
		String sql="insert into uploadbieumau(idDeTai,maBieuMau,linkBieuMau ) values(?,?,?) ";
				

		try {
            pst = conn.prepareStatement(sql);
			
			pst.setInt(1, objBM.getIdDeTai());
			pst.setString(2, objBM.getMaBieuMau());
			pst.setString(3, objBM.getLinkBieuMau());
			
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
     
     
  */
	

	
}
