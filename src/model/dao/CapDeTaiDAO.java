package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import library.LibraryConstant;
import model.bean.CapDeTai;
import model.bean.User;

public class CapDeTaiDAO {
     private ConnectMySQLLibrary connectMySQLLibrary;
     private Connection conn;
     private Statement st;
     private ResultSet rs;
     private PreparedStatement pst;
     
     public CapDeTaiDAO() {
		connectMySQLLibrary = new ConnectMySQLLibrary(); 
	 }
     
     
     public int addItem(CapDeTai objCapDeTai) {
		   int result = 0;
		  
		  conn = connectMySQLLibrary.getConnectMySQL();
		  
		  String sql = "insert into capdetai(tenCapDeTai) values(?) ";
		  
		  try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objCapDeTai.getTenCapDeTai());
			
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

	


	public CapDeTai getItem(int idCapDeTai) {
		 conn = connectMySQLLibrary.getConnectMySQL();
			
			String sql="select * from capdetai where idCapDeTai = ?";
			CapDeTai objCapDeTai = null;
			try {
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, idCapDeTai);
				
		        rs = pst.executeQuery();
		        if(rs.next()){
		        	objCapDeTai = new CapDeTai(rs.getInt("idCapDeTai"), rs.getString("tenCapDeTai"));
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return objCapDeTai;
	}



	public int editItem(CapDeTai objCapDeTai) {
        int result = 0;
		
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql="update capdetai set tenCapDeTai = ?  where idCapDeTai=?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCapDeTai.getTenCapDeTai());
			pst.setInt(2, objCapDeTai.getIdCapDeTai());
           
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



	public int delItem(int idCapDeTai) {
        int result = 0;
		
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "delete from capdetai where idCapDeTai = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCapDeTai);
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

	public int countCapDeTai() {
		
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "SELECT COUNT(*) AS Total FROM capdetai";
		
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



	public ArrayList<CapDeTai> getItemsByPage(int offset) {
		ArrayList<CapDeTai> listCapDeTai = new ArrayList<>();

   	    conn = connectMySQLLibrary.getConnectMySQL();
   	 
   	    String sql = "select * from capdetai ORDER BY idCapDeTai DESC LIMIT "+offset+", "+LibraryConstant.ROW_COUNT;

   	 try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				CapDeTai objCapDeTai = new CapDeTai(rs.getInt("idCapDeTai"), rs.getString("tenCapDeTai"));
				listCapDeTai.add(objCapDeTai);
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
   	 
     	 return listCapDeTai;
	}
	
	public ArrayList<CapDeTai> getItemsByPage() {
		ArrayList<CapDeTai> listCapDeTai = new ArrayList<>();

   	    conn = connectMySQLLibrary.getConnectMySQL();
   	 
   	    String sql = "select * from capdetai ORDER BY idCapDeTai DESC";

   	 try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				CapDeTai objCapDeTai = new CapDeTai(rs.getInt("idCapDeTai"), rs.getString("tenCapDeTai"));
				listCapDeTai.add(objCapDeTai);
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
   	 
     	 return listCapDeTai;
	}
	
	public boolean checkExistCapDeTai(String name) {
		
        conn = connectMySQLLibrary.getConnectMySQL();
        
        String sql = "select * from capdetai where tenCapDeTai = ?";
        try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, name.trim());
			
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
	
     
}
