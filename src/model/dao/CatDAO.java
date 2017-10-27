package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import library.LibraryConstant;
import model.bean.Cat;

public class CatDAO {
     private ConnectMySQLLibrary connectMySQLLibrary;
     private Connection conn;
     private Statement st;
     private ResultSet rs;
     private PreparedStatement pst;
     
     public CatDAO() {
		connectMySQLLibrary = new ConnectMySQLLibrary(); 
	 }
     
    
     
     public ArrayList<Cat> getItemsByParent(){
    	 ArrayList<Cat> listCat = new ArrayList<>();
    	 
    	 conn = connectMySQLLibrary.getConnectMySQL();
    	 
    	 String sql = "select * from danhmuc where parent_id = 0 ORDER BY id_cat DESC";
    	 
    	 try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Cat objCat = new Cat(rs.getInt("id_cat"), rs.getString("name_cat"), rs.getInt("parent_id"), rs.getInt("active_cat"));
				listCat.add(objCat);
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
    	 
    	 return listCat;
    	 
     }
     
     
     public ArrayList<Cat> getItemsByChild(int parent_id){
    	 ArrayList<Cat> listCat = new ArrayList<>();
    	 
    	 conn = connectMySQLLibrary.getConnectMySQL();
    	 
    	 String sql = "select * from danhmuc where parent_id =  "+parent_id+" ORDER BY id_cat DESC ";
    	 
    	 try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Cat objCat = new Cat(rs.getInt("id_cat"), rs.getString("name_cat"), rs.getInt("parent_id"), rs.getInt("active_cat"));
				listCat.add(objCat);
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
    	 
    	 return listCat;
    	 
     }

	public int addItem(Cat objCat) {
		   int result = 0;
		  
		  conn = connectMySQLLibrary.getConnectMySQL();
		  
		  String sql = "insert into danhmuc(name_cat,parent_id,active_cat) values(?,?,?) ";
		  
		  try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objCat.getNameCat());
			pst.setInt(2, objCat.getId_parent());
			pst.setInt(3, objCat.getActive_cat());
			
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

	


	public Cat getItem(int idCat) {
		 conn = connectMySQLLibrary.getConnectMySQL();
			
			String sql="select * from danhmuc where id_cat = ?";
			Cat objCat = null;
			try {
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, idCat);
				
		        rs = pst.executeQuery();
		        if(rs.next()){
		        	 objCat = new Cat(rs.getInt("id_cat"), rs.getString("name_cat"), rs.getInt("parent_id"), rs.getInt("active_cat"));
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return objCat;
	}



	public int editItem(Cat objCat) {
        int result = 0;
		
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql="update danhmuc set name_cat = ?, parent_id = ?, active_cat = ?  where id_cat=?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objCat.getNameCat());
			pst.setInt(2, objCat.getId_parent());
			pst.setInt(3, objCat.getActive_cat());
			pst.setInt(4, objCat.getIdCat());
           
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



	public int delItem(int idCat) {
        int result = 0;
		
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "delete from danhmuc where id_cat = ?";
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idCat);
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

	public int countCat() {
		
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "SELECT COUNT(*) AS Total FROM danhmuc where parent_id = 0 ";
		
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



	public ArrayList<Cat> getItemsByParentPage(int offset) {
		ArrayList<Cat> listCat = new ArrayList<>();
   	 
   	    conn = connectMySQLLibrary.getConnectMySQL();
   	 
   	    String sql = "select * from danhmuc where parent_id = 0 ORDER BY id_cat DESC LIMIT "+offset+", "+LibraryConstant.ROW_COUNT_CAT;

   	 try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				Cat objCat = new Cat(rs.getInt("id_cat"), rs.getString("name_cat"), rs.getInt("parent_id"), rs.getInt("active_cat"));
				listCat.add(objCat);
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
   	 
     	 return listCat;
	}



	public Cat getItemSearch(String txtSearch) {
		 conn = connectMySQLLibrary.getConnectMySQL();
			
			String sql="select * from danhmuc AS c "
					+ "  INNER JOIN news AS n "
					+ "  ON n.id_cat = c.id_cat "
					+ "  where  n.name_news = ? ";
			Cat objCat = null;
			try {
				pst = conn.prepareStatement(sql);
				
				pst.setString(1, txtSearch);
				
		        rs = pst.executeQuery();
		        if(rs.next()){
		        	 objCat = new Cat(rs.getInt("id_cat"), rs.getString("name_cat"), rs.getInt("parent_id"), rs.getInt("active_cat"));
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			return objCat;
		
	}



     
     
}
