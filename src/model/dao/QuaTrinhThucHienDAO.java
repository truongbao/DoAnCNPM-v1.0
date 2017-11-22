package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import library.ConnectMySQLLibrary;
import model.bean.Cat;
import model.bean.QuaTrinhThucHien;

public class QuaTrinhThucHienDAO {
	private ConnectMySQLLibrary connectMySQLLibrary;
    private Connection conn;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    
    public QuaTrinhThucHienDAO() {
    		connectMySQLLibrary = new ConnectMySQLLibrary(); 
    }
    
    public int addItem(QuaTrinhThucHien item) {
		   int result = 0;
		  
		  conn = connectMySQLLibrary.getConnectMySQL();
		  
		  String sql = "insert into quatrinhthuchien(idDeTai,trangThai,chuDe,noiDung) values(?,?,?,?) ";
		  
		  try {
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, item.getIdDeTai());
			pst.setString(2, item.getTrangThai());
			pst.setString(3, item.getChuDe());
			pst.setString(4, item.getNoiDung());
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
    
    public QuaTrinhThucHien getQuaTrinhThucHienWith(int idDeTai, String trangthai) {
    		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select * from quatrinhthuchien where idDeTai = ? and trangThai = ?";
		
		QuaTrinhThucHien item = null;
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, idDeTai);
			pst.setString(2, trangthai);
	        rs = pst.executeQuery();
	        if(rs.next()){
	        	 item = new QuaTrinhThucHien(rs.getInt("idQuaTrinhThucHien"), rs.getInt("idDeTai"), rs.getString("trangThai"), rs.getTimestamp("thoiGian"), rs.getString("chuDe"), rs.getString("noiDung"));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return item;	
    }
    
    public int updateIemWith(QuaTrinhThucHien item) {
    		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "update quatrinhthuchien SET trangThai = ?, noiDung = ? WHERE idQuaTrinhThucHien = ?" ;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1,item.getTrangThai());
			pst.setString(2, item.getNoiDung());
			pst.setInt(3, item.getIdQuaTrinhThucHien());
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
