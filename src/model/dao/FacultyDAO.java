package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import model.bean.Cat;
import model.bean.Khoa;

public class FacultyDAO {
     private ConnectMySQLLibrary connectMySQLLibrary;
     private Connection conn;
     private Statement st;
     private ResultSet rs;
     private PreparedStatement pst;
     
     public FacultyDAO() {
		connectMySQLLibrary = new ConnectMySQLLibrary(); 
	 }
     
    
     
     public ArrayList<Khoa> getList(){
    	 ArrayList<Khoa> listFaculty = new ArrayList<>();
    	 
    	 conn = connectMySQLLibrary.getConnectMySQL();
    	 
    	 String sql = "select * from khoa";
    	 
    	 try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Khoa obj = new Khoa(rs.getInt("idKhoa"), rs.getString("tenKhoa"));
				listFaculty.add(obj);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	 
    	 return listFaculty;
    	 
     }
     
	public Khoa getItem(int idFaculty) {
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql="select * from khoa where idKhoa = ?";
		Khoa obj = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idFaculty);
	        rs = pst.executeQuery();
	        if(rs.next()){
	        	obj = new Khoa(rs.getInt("idKhoa"), rs.getString("tenKhoa"));
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
     
     
}
