package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import model.bean.HopDong;
import model.bean.User;
import constant.define;

public class HopDongDAO {
	private ConnectMySQLLibrary connectMySQLLibrary;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public HopDongDAO() {
		  connectMySQLLibrary = new ConnectMySQLLibrary();
	}
	
   public HopDong getObjHopDong(int idHopDong) {
		
       conn = connectMySQLLibrary.getConnectMySQL();
       
       String sql = "select * from hopdong where idHopDong = "+idHopDong ;
       	
       
       HopDong objHopDong = null;
       try {
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			if(rs.next()){
			  objHopDong = new HopDong(rs.getInt("idHopDong"),
					  				   rs.getString("tenKhachHang"),
					  				   rs.getString("chucVuKH") ,
					  				   rs.getString("diaChiKH"),
					  				   rs.getString("emailKH") ,
					  				   rs.getInt("idNguoiDaiDien"),
					  				   rs.getInt("idGiangVien") ,
					  				   rs.getInt("idDeTai") , 
					  				   rs.getTimestamp("thoiGianBatDau") ,
					  				   rs.getTimestamp("thoiGianKetThuc") ,
					  				   rs.getInt("kinhPhi"),
					  				   rs.getTimestamp("thoiGianKyHopDong") , 
					  				   rs.getString("dienThoaiKH"),
					  				   rs.getString("trangThaiHopDong"));
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
		
		return objHopDong;
	}

   
	
	public int countHopDong() {
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select count(*) AS total from hopdong ";
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
	
	public ArrayList<HopDong> getItemsByPage(int offset) {
		ArrayList<HopDong> listHopDong = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select * from hopdong";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				HopDong objHopDong = new HopDong(rs.getInt("idHopDong"),
		  				   rs.getString("tenKhachHang"),
		  				   rs.getString("chucVuKH") ,
		  				   rs.getString("diaChiKH"),
		  				   rs.getString("emailKH") ,
		  				   rs.getInt("idNguoiDaiDien"),
		  				   rs.getInt("idGiangVien") ,
		  				   rs.getInt("idDeTai") , 
		  				   rs.getTimestamp("thoiGianBatDau") ,
		  				   rs.getTimestamp("thoiGianKetThuc") ,
		  				   rs.getInt("kinhPhi"),
		  				   rs.getTimestamp("thoiGianKyHopDong") , 
		  				   rs.getString("dienThoaiKH"),
		  				   rs.getString("trangThaiHopDong"));
				listHopDong.add(objHopDong);
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
		return listHopDong;
	}
	
	public ArrayList<HopDong> getItems() {

		ArrayList<HopDong> listHopDong = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select * form hopdong";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				HopDong objHopDong = new HopDong(rs.getInt("idHopDong"),
		  				   rs.getString("tenKhachHang"),
		  				   rs.getString("chucVuKH") ,
		  				   rs.getString("diaChiKH"),
		  				   rs.getString("emailKH") ,
		  				   rs.getInt("idNguoiDaiDien"),
		  				   rs.getInt("idGiangVien") ,
		  				   rs.getInt("idDeTai") , 
		  				   rs.getTimestamp("thoiGianBatDau") ,
		  				   rs.getTimestamp("thoiGianKetThuc") ,
		  				   rs.getInt("kinhPhi"),
		  				   rs.getTimestamp("thoiGianKyHopDong") , 
		  				   rs.getString("dienThoaiKH"),
		  				   rs.getString("trangThaiHopDong"));
               listHopDong.add(objHopDong);
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
		return listHopDong;
	}
	
	public int addItem(HopDong objHopDong) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql="insert  hopdong ("
				+ "tenKhachHang,"
				+ "chucVuKH,"
				+ "diaChiKH,"
				+ "emailKH,"
				+ "idNguoiDaiDien,"
				+ "idGiangVien,"
				+ "idDeTai,"
				+ "thoiGianBatDau,"
				+ "thoiGianKetThuc,"
				+ "kinhPhi,"
				+ "thoiGianKyHopDong,"
				+ "dienThoaiKH,"
				+ "trangThaiHopDong) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objHopDong.getTenKhachHang());
			pst.setString(2, objHopDong.getChucVuKH());
			pst.setString(3, objHopDong.getDiaChiKH());
			pst.setString(4, objHopDong.getEmailKH());
			pst.setInt(5, objHopDong.getIdNguoiDaiDien());
			pst.setInt(6, objHopDong.getIdGiangVien());
			pst.setInt(7, objHopDong.getIdDeTai());
			pst.setTimestamp(8, objHopDong.getThoiGianBatDau());
			pst.setTimestamp(9, objHopDong.getThoiGianKetThuc());
			pst.setInt(10, objHopDong.getKinhPhi());
			pst.setTimestamp(11, objHopDong.getThoiGianKyHopDong());
			pst.setString(12, objHopDong.getDienThoaiKH());
			pst.setString(13, objHopDong.getTrangThaiHopDong());
			
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
	
	public int editItem(HopDong objHopDong) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
       
       String sql = "UPDATE hopdong SET tenKhachHang = ?, chucVuKH = ?, diaChiKH = ?, emailKH = ?,idNguoiDaiDien = ?, "
       		+ " idGiangVien = ?,idDeTai = ?, thoiGianBatDau = ?,thoiGianKetThuc = ?,kinhPhi = ?, "
       		+ " thoiGianKyHopDong = ?,dienThoaiKH = ?,trangThaiHopDong = ? "
       		+ " WHERE idHopDong = ?";
       
       try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objHopDong.getTenKhachHang());
			pst.setString(2, objHopDong.getChucVuKH());
			pst.setString(3, objHopDong.getDiaChiKH());
			pst.setString(4, objHopDong.getEmailKH());
			pst.setInt(5, objHopDong.getIdNguoiDaiDien());
			pst.setInt(6, objHopDong.getIdGiangVien());
			pst.setInt(7, objHopDong.getIdDeTai());
			pst.setTimestamp(8, objHopDong.getThoiGianBatDau());
			pst.setTimestamp(9, objHopDong.getThoiGianKetThuc());
			pst.setInt(10, objHopDong.getKinhPhi());
			pst.setTimestamp(11, objHopDong.getThoiGianKyHopDong());
			pst.setString(12, objHopDong.getDienThoaiKH());
			pst.setString(13, objHopDong.getTrangThaiHopDong());
			pst.setInt(14, objHopDong.getIdHopDong());
			
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
	
	public int delItem(int idHopDong) {
		int result = 0;
        conn = connectMySQLLibrary.getConnectMySQL();
        String sql = "delete from hopdong where idHopDong = ? ";
        
        try {
			pst  = conn.prepareStatement(sql);
			pst.setInt(1, idHopDong);
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
	
}
