package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import model.bean.DeTai;
import model.bean.HocVi;
import model.bean.Khoa;
import model.bean.LoaiTaiKhoan;
import model.bean.ThanhVien;
import model.bean.User;
import constant.define;

public class DetaiDAO {
	private ConnectMySQLLibrary connectMySQLLibrary;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public DetaiDAO() {
		  connectMySQLLibrary = new ConnectMySQLLibrary();
	}
  

    //lay ra danh sách đề tài ko phan trang (public)
	public ArrayList<DeTai> getListDeTai(){
		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select dt.*,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " order by dt.idDeTai DESC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				DeTai objDeTai = new DeTai(rs.getInt("idDeTai"),rs.getString("tenDeTai"),rs.getString("maSoDeTai"),rs.getInt("idLinhVucNghienCuu"),rs.getString("tenLinhVucNghienCuu"),
						                   rs.getInt("idLoaiHinhNghienCuu"),rs.getString("tenLoaiHinhNghienCuu"),rs.getTimestamp("thoiGianBatDau"),rs.getTimestamp("thoiGianKetThuc"), 
						                   rs.getString("donViChuTri"),rs.getInt("idUser"),rs.getString("fullName"),rs.getString("donViPhoiHopChinh"),
						                   rs.getString("tongQuan"),rs.getString("tinhCapThiet"),rs.getString("mucTieu") ,rs.getString("phamViNghienCuu"),
						                   rs.getString("phuongPhapNghienCuu"),rs.getString("noiDung"),rs.getString("sanPham"),rs.getString("hieuQua"),
						                   rs.getInt("kinhPhiThucHien"),rs.getString("trangThai"),rs.getString("capDeTai"),rs.getTimestamp("thoiGianDangKy") );
				
				listDeTai.add(objDeTai);
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
		return listDeTai;
		
	}
	
	
	public ArrayList<ThanhVien> getListThanhVienByDeTai(int idDeTai){
		ArrayList<ThanhVien> listTVByDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select * from thanhvien where idDeTai = "+idDeTai;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				
				ThanhVien objTV = new ThanhVien(rs.getInt("idThanhVien"),rs.getString("tenThanhVien"),rs.getString("donVi"),
						                        rs.getString("noiDungNghienCuu"),rs.getInt("idDeTai") );
				
				
				listTVByDeTai.add(objTV);
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
		return listTVByDeTai;
		
	}
	
	
	public DeTai getObjDeTai(int iDeTai) {
		
	       conn = connectMySQLLibrary.getConnectMySQL();
	       
	       String sql = "select dt.*,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
	       		+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
	       		+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
	       		+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
	       		+ " where dt.idDeTai = "+iDeTai;
	       	
	       
	       
	       DeTai objDeTai = null;
	       try {
				pst = conn.prepareStatement(sql);
				
				rs = pst.executeQuery();
				if(rs.next()){
					 objDeTai = new DeTai(rs.getInt("idDeTai"),rs.getString("tenDeTai"),rs.getString("maSoDeTai"),rs.getInt("idLinhVucNghienCuu"),rs.getString("tenLinhVucNghienCuu"),
			                   rs.getInt("idLoaiHinhNghienCuu"),rs.getString("tenLoaiHinhNghienCuu"),rs.getTimestamp("thoiGianBatDau"),rs.getTimestamp("thoiGianKetThuc"), 
			                   rs.getString("donViChuTri"),rs.getInt("idUser"),rs.getString("fullName"),rs.getString("donViPhoiHopChinh"),
			                   rs.getString("tongQuan"),rs.getString("tinhCapThiet"),rs.getString("mucTieu") ,rs.getString("phamViNghienCuu"),
			                   rs.getString("phuongPhapNghienCuu"),rs.getString("noiDung"),rs.getString("sanPham"),rs.getString("hieuQua"),
			                   rs.getInt("kinhPhiThucHien"),rs.getString("trangThai"),rs.getString("capDeTai"),rs.getTimestamp("thoiGianDangKy") );
				}
				
				
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
			
			return objDeTai;
		}
	
	/*//lay ra danh sách hoc vi ko phan trang (public)
	public ArrayList<HocVi> getListHocVi(){
		ArrayList<HocVi> listHocVi = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select * FROM hocvi ORDER BY idHocVi ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   HocVi objHocVi = new HocVi(rs.getInt("idHocVi") ,rs.getString("tenHocVi") );
			   listHocVi.add(objHocVi);
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
		return listHocVi;
		
	}
		
		
	//lay ra danh sách Khoa ko phan trang (public)
	public ArrayList<Khoa> getListKhoa(){
		ArrayList<Khoa> listKhoa = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select * FROM khoa ORDER BY idKhoa ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   Khoa objKhoa = new Khoa(rs.getInt("idKhoa") ,rs.getString("tenKhoa") );
			   listKhoa.add(objKhoa);
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
		return listKhoa;
		
	}
	
	
	 public User getObjUser(int idUser) {
			
	       conn = connectMySQLLibrary.getConnectMySQL();
	       
	       String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocVi from user AS u "
	       		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
	       		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
	       		+ " INNER JOIN  hocvi AS hv ON hv.idHocVi = u.idHocVi where u.idUser = "+idUser ;
	       	
	       
	       User objUser = null;
	       try {
				pst = conn.prepareStatement(sql);
				
				rs = pst.executeQuery();
				if(rs.next()){
				  objUser = new User(rs.getInt("idUser"),rs.getString("fullName"),rs.getString("chucDanhKhoaHoc") ,rs.getString("diaChiCoQuan") ,
						             rs.getString("dienThoaiCoQuan"),rs.getInt("idHocVi") ,rs.getString("tenHocVi"),rs.getString("namSinh") ,rs.getString("diaChiNhaRieng") , 
						             rs.getString("dienThoaiNhaRieng") ,rs.getString("email") ,rs.getString("fax"),rs.getString("userName") , 
						             rs.getString("matKhau") ,rs.getInt("idLoaiTaiKhoan"),rs.getString("tenLoaiTaiKhoan") ,rs.getInt("idKhoa"), rs.getString("tenKhoa") );
				}
				
				
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
			
			return objUser;
		}
	
	
	public int editItem(User objUser) {
		 int result = 0;
        
        conn = connectMySQLLibrary.getConnectMySQL();
        
        String sql = "UPDATE user SET fullname = ?, matKhau = ?, email = ?, idKhoa = ?,idHocVi = ?, "
        		+ " idLoaiTaiKhoan = ?,chucDanhKhoaHoc = ?, diaChiCoQuan = ?,diaChiNhaRieng = ?,fax = ?, "
        		+ " dienThoaiCoQuan = ?,dienThoaiNhaRieng = ?,namSinh = ? "
        		+ " WHERE idUser = ?";
        
        try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getFullName());
			pst.setString(2, objUser.getMatKhau());
			pst.setString(3, objUser.getEmail());
			pst.setInt(4, objUser.getIdKhoa());
			pst.setInt(5, objUser.getIdHocVi());
			pst.setInt(6, objUser.getIdLoaiTaiKhoan());
			pst.setString(7, objUser.getChucDanhKhoaHoc() );
			pst.setString(8, objUser.getDiaChiCoQuan());
			pst.setString(9, objUser.getDiaChiNhaRieng());
			pst.setString(10, objUser.getFax());
			pst.setString(11, objUser.getDienThoaiCoQuan());
			pst.setString(12, objUser.getDienThoaiNhaRieng());
			pst.setString(13, objUser.getNamSinh());
			pst.setInt(14, objUser.getIdUser());
			
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

	*/

	

	
	
	
	
}
