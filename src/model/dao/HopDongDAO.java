package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import model.bean.HopDong;
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
}
