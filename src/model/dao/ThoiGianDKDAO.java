package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import library.ConnectMySQLLibrary;
import model.bean.ThoiGianDK;

public class ThoiGianDKDAO {
     private ConnectMySQLLibrary connectMySQLLibrary;
     private Connection conn;
     private Statement st;
     private ResultSet rs;
     private PreparedStatement pst;
     
     public ThoiGianDKDAO() {
		connectMySQLLibrary = new ConnectMySQLLibrary(); 
	 }
     
     
     public int changeThoiGianDangKy(Date thoiGianBatDau, Date thoiGianKetThuc) {
		   int result = 0;
		  
		  conn = connectMySQLLibrary.getConnectMySQL();
		  if (!checkExistThoiGian(1)) {
			  String sql = "UPDATE thoigiandk SET thoiGianBatDau = ?, thoiGianKetThuc = ? where idThoiGianDK = 1 ";
			  
			  try {
				pst = conn.prepareStatement(sql);
				
				pst.setDate(1, thoiGianBatDau);
				pst.setDate(2, thoiGianKetThuc);
				
				result = pst.executeUpdate();
			  } catch (SQLException e) {
					e.printStackTrace();
			  } finally{
					try {
						pst.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		  } else {
			addItem(1, new ThoiGianDK(1, thoiGianBatDau, thoiGianKetThuc));
		  }	
		return result;
	}

	
     public int changeThoiGianThuyetMinh(Date thoiGianBatDau, Date thoiGianKetThuc) {
		   int result = 0;
			  
		  conn = connectMySQLLibrary.getConnectMySQL();
		  if (checkExistThoiGian(2)) {
			  String sql = "UPDATE thoigiandk SET thoiGianBatDau = ?, thoiGianKetThuc = ? where idThoiGianDK = 2 ";
			  
			  try {
				pst = conn.prepareStatement(sql);
				
				pst.setDate(1, thoiGianBatDau);
				pst.setDate(2, thoiGianKetThuc);
				
				result = pst.executeUpdate();
			  } catch (SQLException e) {
					e.printStackTrace();
			  } finally{
					try {
						pst.close();
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
		  } else {
			addItem(2, new ThoiGianDK(1, thoiGianBatDau, thoiGianKetThuc));
		  }	
		return result;
	}
     
     public boolean checkExistThoiGian(int idThoiGianDK) {
    	 String sql = "select * from thoigiandk where idThoiGianDK = ? ";
         try {
 			pst = conn.prepareStatement(sql);
 			
 			pst.setInt(1, idThoiGianDK);
 			
 			rs = pst.executeQuery();
 			if(rs.next()){
 			  return false;
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
 		
 		return true;
     }

	public ThoiGianDK getItem(int idThoiGianDK) {
		 conn = connectMySQLLibrary.getConnectMySQL();
			
			String sql="select * from thoigiandk where idThoiGianDK = ?";
			ThoiGianDK objThoiGianDK = null;
			try {
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, idThoiGianDK);
				
		        rs = pst.executeQuery();
		        if(rs.next()){
		        	objThoiGianDK = new ThoiGianDK(rs.getInt("idThoiGianDK"), rs.getDate("thoiGianBatDau"), rs.getDate("thoiGianKetThuc"));
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return objThoiGianDK;
	}

 

	public int addItem(int idThoGianDK, ThoiGianDK tgdk) {
		int result = 0;
		  
		  conn = connectMySQLLibrary.getConnectMySQL();
		  
		  String sql = "insert into thoigiandk(idThoiGianDK,thoiGianBatDau,thoiGianKetThuc) values(?,?,?) ";
		  
		  try {
			pst = conn.prepareStatement(sql);
			
			pst.setInt(1, tgdk.getIdThoiGianDK());
			pst.setDate(2, tgdk.getThoiGianBatDau());
			pst.setDate(3, tgdk.getThoiGianKetThuc());
			
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



	public int delItem(int idThoGianDK) {
        int result = 0;
		
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "delete from thoigiandk where idThoGianDK = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idThoGianDK);
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
