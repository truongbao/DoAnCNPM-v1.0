package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import model.bean.HocVi;
import model.bean.Khoa;
import model.bean.LoaiTaiKhoan;
import model.bean.User;
import constant.define;

public class UserDAO {
	private ConnectMySQLLibrary connectMySQLLibrary;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public UserDAO() {
		  connectMySQLLibrary = new ConnectMySQLLibrary();
	}
	
	
   public User checkLoginPublic(String username, String password) {
		
        conn = connectMySQLLibrary.getConnectMySQL();
        
        String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocVi from user AS u "
        		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
        		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
        		+ " INNER JOIN  hocvi AS hv ON hv.idHocVi = u.idHocVi "
        		+ " where userName = ? and matKhau = ? ";
        
        User objUser = null;
        try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, username);
			pst.setString(2, password);
			
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

    //lay ra danh sách loai tai khoan ko phan trang (public)
	public ArrayList<LoaiTaiKhoan> getListLoaiTK(){
		ArrayList<LoaiTaiKhoan> listLoaiTK = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select * FROM loaitaikhoan ORDER BY idLoaiTaiKhoan ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   LoaiTaiKhoan objLoaiTK = new LoaiTaiKhoan(rs.getInt("idLoaiTaiKhoan") ,rs.getString("tenLoaiTaiKhoan") );
			   listLoaiTK.add(objLoaiTK);
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
		return listLoaiTK;
		
	}
	
	//lay ra danh sách hoc vi ko phan trang (public)
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

	

	
	public ArrayList<User> getItems(){
		ArrayList<User> listUser = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocVi from user AS u "
	       		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
	       		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
	       		+ " INNER JOIN  hocvi AS hv ON hv.idHocVi = u.idHocVi ORDER BY id_User DESC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   User objUser = new User(
					   rs.getInt("idUser"),
					   rs.getString("fullName"),
					   rs.getString("chucDanhKhoaHoc") ,
					   rs.getString("diaChiCoQuan") ,
			           rs.getString("dienThoaiCoQuan"),
			           rs.getInt("idHocVi") ,
			           rs.getString("tenHocVi"),
			           rs.getString("namSinh") ,
			           rs.getString("diaChiNhaRieng") , 
			           rs.getString("dienThoaiNhaRieng") ,
			           rs.getString("email") ,
			           rs.getString("fax"),rs.getString("userName") , 
			           rs.getString("matKhau") ,
			           rs.getInt("idLoaiTaiKhoan"),
			           rs.getString("tenLoaiTaiKhoan") ,
			           rs.getInt("idKhoa"), 
			           rs.getString("tenKhoa") );

               listUser.add(objUser);
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
		return listUser;
		
	}

	public int addItem(User objUser) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql="insert into user ("
				+ "fullName,"
				+ "chucDanhKhoaHoc,"
				+ "diaChiCoQuan,"
				+ "dienThoaiCoQuan,"
				+ "idHocVi,"
				+ "namSinh,"
				+ "diaChiNhaRieng,"
				+ "dienThoaiNhaRieng,"
				+ "email,"
				+ "fax,"
				+ "userName,"
				+ "matKhau,"
				+ "idLoaiTaiKhoan,"
				+ "idKhoa) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objUser.getFullName());
			pst.setString(2, objUser.getChucDanhKhoaHoc());
			pst.setString(3, objUser.getDiaChiCoQuan());
			pst.setString(4, objUser.getDienThoaiCoQuan());
			pst.setInt(5, objUser.getIdHocVi());
			pst.setString(6, objUser.getNamSinh());
			pst.setString(7, objUser.getDiaChiNhaRieng());
			pst.setString(8, objUser.getDienThoaiNhaRieng());
			pst.setString(9, objUser.getEmail());
			pst.setString(10, objUser.getFax());
			pst.setString(11, objUser.getUserName());
			pst.setString(12, objUser.getMatKhau());
			pst.setInt(13, objUser.getIdLoaiTaiKhoan());
			pst.setInt(14, objUser.getIdKhoa());
			
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

	
	public int delItem(int idUser) {
		int result = 0;
        conn = connectMySQLLibrary.getConnectMySQL();
        String sql = "delete from user where idUser = ? ";
        
        try {
			pst  = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
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
	
	public int countUser() {
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select count(*) AS total from user ";
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

	public ArrayList<User> getItemsByPage(int offset) {
		ArrayList<User> listUser = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocVi from user AS u "
	       		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
	       		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
	       		+ " INNER JOIN  hocvi AS hv ON hv.idHocVi = u.idHocVi ORDER BY idUser DESC LIMIT "+offset+","+define.ROW_COUNT;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   User objUser = new User(
					   rs.getInt("idUser"),
					   rs.getString("fullName"),
					   rs.getString("chucDanhKhoaHoc") ,
					   rs.getString("diaChiCoQuan") ,
			           rs.getString("dienThoaiCoQuan"),
			           rs.getInt("idHocVi") ,
			           rs.getString("tenHocVi"),
			           rs.getString("namSinh") ,
			           rs.getString("diaChiNhaRieng") , 
			           rs.getString("dienThoaiNhaRieng") ,
			           rs.getString("email") ,
			           rs.getString("fax"),rs.getString("userName") , 
			           rs.getString("matKhau") ,
			           rs.getInt("idLoaiTaiKhoan"),
			           rs.getString("tenLoaiTaiKhoan") ,
			           rs.getInt("idKhoa"), 
			           rs.getString("tenKhoa") );
               listUser.add(objUser);
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
		return listUser;

		
	}
	
	
	public boolean checkExistUserName(String username) {
		
        conn = connectMySQLLibrary.getConnectMySQL();
        
        String sql = "select * from user where username = ? ";
        
        User objUser = null;
        try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, username);
			
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
	
public boolean checkExistEmail(String email) {
		
        conn = connectMySQLLibrary.getConnectMySQL();
        
        String sql = "select * from user where email = ? ";
        
        User objUser = null;
        try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, email);
			
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
