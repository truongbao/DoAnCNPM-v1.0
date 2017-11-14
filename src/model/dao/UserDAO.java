package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import library.LibraryConstant;
import model.bean.DeTai;
import model.bean.HocViHocHam;
import model.bean.Khoa;
import model.bean.LoaiTaiKhoan;
import model.bean.ThanhVien;
import model.bean.User;

public class UserDAO {
	private ConnectMySQLLibrary connectMySQLLibrary;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public UserDAO() {
		  connectMySQLLibrary = new ConnectMySQLLibrary();
	}
	
	//lây danh sach giang vien với idLoaiTaiKhoan  = 2 
	
	public ArrayList<User> getListGiangVien(){
		ArrayList<User> listUser = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		 String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocViHocHam from user AS u "
	        		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
	        		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
	        		+ " INNER JOIN  hocvihocham AS hv ON hv.idHocViHocHam = u.idHocViHocHam where u.idLoaiTaiKhoan = 2 ";
		
	    User objUser = null;
		  
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				 objUser = new User(rs.getInt("idUser"),rs.getString("fullName"),rs.getString("diaChiCoQuan") ,
			             rs.getString("dienThoaiCoQuan"),rs.getInt("idHocViHocHam") ,rs.getString("tenHocViHocHam"),rs.getString("namSinh") ,rs.getString("diaChiNhaRieng") , 
			             rs.getString("dienThoaiNhaRieng") ,rs.getString("email") ,rs.getString("fax"),rs.getString("userName") , 
			             rs.getString("matKhau") ,rs.getInt("idLoaiTaiKhoan"),rs.getString("tenLoaiTaiKhoan") ,rs.getInt("idKhoa"), rs.getString("tenKhoa"), rs.getString("avt"));
			                        
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
	
	
	//lây danh sach giao vien trong trường (trong bảng user)
	
		public ArrayList<User> getListUserInSchool(){
			ArrayList<User> listUser = new ArrayList<>();
			conn = connectMySQLLibrary.getConnectMySQL();
			
			 String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocViHocHam from user AS u "
		        		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
		        		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
		        		+ " INNER JOIN  hocvihocham AS hv ON hv.idHocViHocHam = u.idHocViHocHam ";
			
		    User objUser = null;
			  
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				
				while(rs.next()){
					 objUser = new User(rs.getInt("idUser"),rs.getString("fullName"),rs.getString("diaChiCoQuan") ,
				             rs.getString("dienThoaiCoQuan"),rs.getInt("idHocViHocHam") ,rs.getString("tenHocViHocHam"),rs.getString("namSinh") ,rs.getString("diaChiNhaRieng") , 
				             rs.getString("dienThoaiNhaRieng") ,rs.getString("email") ,rs.getString("fax"),rs.getString("userName") , 
				             rs.getString("matKhau") ,rs.getInt("idLoaiTaiKhoan"),rs.getString("tenLoaiTaiKhoan") ,rs.getInt("idKhoa"), rs.getString("tenKhoa"), rs.getString("avt") );
				                        
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
	
	
	
	

	//Hien thi danh sach thanh vien có phân trang ứng vs id chủ nhiệm đề tài (thèn đang login)
	
	public ArrayList<ThanhVien> getListThanhVienByIdUser(int idUser){
		ArrayList<ThanhVien> listTV = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select tv.idThanhVien, tv.tenThanhVien,tv.donVi, tv.noiDungNghienCuu, dt.idDeTai, dt.maSoDeTai FROM thanhvien AS tv"
				+ " INNER JOIN  detai AS dt ON dt.idDeTai = tv.idDeTai"
				+ " INNER JOIN  user AS u ON u.idUser = dt.idUser WHERE u.idUser = "+idUser+" and u.idUser != tv.idThanhVien ";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   ThanhVien objTV = new ThanhVien(rs.getInt("idThanhVien"),rs.getString("tenThanhVien"),rs.getString("donVi"), 
					                           rs.getString("noiDungNghienCuu"),rs.getInt("idDeTai"),rs.getString("maSoDeTai"));
			   listTV.add(objTV);
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
		return listTV;
		
	}
	
	
	
	public ArrayList<DeTai> getListMaSoDeTaiByIdUser(int idUser){
		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select  dt.idDeTai, dt.maSoDeTai FROM detai AS dt "
				+ " INNER JOIN  user AS u ON u.idUser = dt.idUser WHERE dt.maSoDeTai != 'no' and  u.idUser = "+idUser ;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
				DeTai objDeTai = new DeTai(rs.getInt("idDeTai"), "", rs.getString("maSoDeTai"),
					    0, "",0, "",null, null,"", 0, "","", "", "","", "", "","", "", "",0, "", "",null, 0, "");

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
	public ArrayList<DeTai> getListDeTaiByIdUser(int idUser) {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " where dt.idUser = ? ORDER BY idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			rs = pst.executeQuery();

			while (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getString("capDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
				listDeTai.add(objDeTai);
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
		return listDeTai;
	}
	
	public ArrayList<DeTai> getListDeTaiByIdUserCanCreateHopDong(int idUser) {

		ArrayList<DeTai> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select dt.*,u.fullName, lvnc.tenLinhVucNghienCuu, lhnc.tenLoaiHinhNghienCuu  FROM detai AS dt "
				+ " INNER JOIN user AS u ON u.idUser = dt.idUser "
				+ " INNER JOIN linhvucnghiencuu AS lvnc ON lvnc.idLinhVucNghienCuu = dt.idLinhVucNghienCuu "
				+ " INNER JOIN loaihinhnghiencuu AS lhnc ON lhnc.idLoaiHinhNghienCuu = dt.idLoaiHinhNghienCuu "
				+ " where dt.idUser = ? and trangThai = '11' ORDER BY idDeTai ASC";
		// String sql = "select * FROM detai WHERE idKhoa = ? ORDER BY idDeTai
		// ASC";
		DeTai objDeTai = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idUser);
			rs = pst.executeQuery();

			while (rs.next()) {
				objDeTai = new DeTai(rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("maSoDeTai"),
						rs.getInt("idLinhVucNghienCuu"), rs.getString("tenLinhVucNghienCuu"),
						rs.getInt("idLoaiHinhNghienCuu"), rs.getString("tenLoaiHinhNghienCuu"),
						rs.getTimestamp("thoiGianBatDau"), rs.getTimestamp("thoiGianKetThuc"),
						rs.getString("donViChuTri"), rs.getInt("idUser"), rs.getString("fullName"),
						rs.getString("donViPhoiHopChinh"), rs.getString("tongQuan"), rs.getString("tinhCapThiet"),
						rs.getString("mucTieu"), rs.getString("phamViNghienCuu"), rs.getString("phuongPhapNghienCuu"),
						rs.getString("noiDung"), rs.getString("sanPham"), rs.getString("hieuQua"),
						rs.getInt("kinhPhiThucHien"), rs.getString("trangThai"), rs.getString("capDeTai"),
						rs.getTimestamp("thoiGianDangKy"), rs.getInt("idKhoa"), rs.getString("linkUpload"));
				listDeTai.add(objDeTai);
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
		return listDeTai;
	}

	
   //kiểm tra đăng nhập

   public User checkLoginPublic(String username, String password) {
		
        conn = connectMySQLLibrary.getConnectMySQL();
        
        String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocViHocHam from user AS u "
        		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
        		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
        		+ " INNER JOIN  hocvihocham AS hv ON hv.idHocViHocHam = u.idHocViHocHam "
        		+ " where userName = ? and matKhau = ? ";
        
        User objUser = null;
        try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, username);
			pst.setString(2, password);
			
			rs = pst.executeQuery();
			if(rs.next()){
			  objUser = new User(rs.getInt("idUser"),rs.getString("fullName") ,rs.getString("diaChiCoQuan") ,
					             rs.getString("dienThoaiCoQuan"),rs.getInt("idHocViHocHam") ,rs.getString("tenHocViHocHam"),rs.getString("namSinh") ,rs.getString("diaChiNhaRieng") , 
					             rs.getString("dienThoaiNhaRieng") ,rs.getString("email") ,rs.getString("fax"),rs.getString("userName") , 
					             rs.getString("matKhau") ,rs.getInt("idLoaiTaiKhoan"),rs.getString("tenLoaiTaiKhoan") ,rs.getInt("idKhoa"), rs.getString("tenKhoa"), rs.getString("avt") );
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
       
       String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocViHocHam from user AS u "
       		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
       		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
       		+ " INNER JOIN  hocvihocham AS hv ON hv.idHocViHocHam = u.idHocViHocHam where u.idUser = "+idUser ;
       	
       
       User objUser = null;
       try {
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			if(rs.next()){
			  objUser = new User(rs.getInt("idUser"),rs.getString("fullName"), rs.getString("diaChiCoQuan") ,
					             rs.getString("dienThoaiCoQuan"),rs.getInt("idHocViHocHam") ,rs.getString("tenHocViHocHam"),rs.getString("namSinh") ,rs.getString("diaChiNhaRieng") , 
					             rs.getString("dienThoaiNhaRieng") ,rs.getString("email") ,rs.getString("fax"),rs.getString("userName") , 
					             rs.getString("matKhau") ,rs.getInt("idLoaiTaiKhoan"),rs.getString("tenLoaiTaiKhoan") ,rs.getInt("idKhoa"), rs.getString("tenKhoa") , rs.getString("avt"));
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
	public ArrayList<HocViHocHam> getListHocViHocHam(){
		ArrayList<HocViHocHam> listHocViHocHam = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select * FROM hocvihocham ORDER BY idHocViHocHam ASC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   HocViHocHam objHocViHocHam = new HocViHocHam(rs.getInt("idHocViHocHam") ,rs.getString("tenHocViHocHam") );
			   listHocViHocHam.add(objHocViHocHam);
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
		return listHocViHocHam;
		
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
        
        String sql = "UPDATE user SET fullname = ?, matKhau = ?, email = ?, idKhoa = ?,idHocViHocHam = ?, "
        		+ " idLoaiTaiKhoan = ?, diaChiCoQuan = ?,diaChiNhaRieng = ?,fax = ?, "
        		+ " dienThoaiCoQuan = ?,dienThoaiNhaRieng = ?,namSinh = ? ,avt = ? "
        		+ " WHERE idUser = ?";
        
        try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, objUser.getFullName());
			pst.setString(2, objUser.getMatKhau());
			pst.setString(3, objUser.getEmail());
			pst.setInt(4, objUser.getIdKhoa());
			pst.setInt(5, objUser.getIdHocViHocHam());
			pst.setInt(6, objUser.getIdLoaiTaiKhoan());
			pst.setString(7, objUser.getDiaChiCoQuan());
			pst.setString(8, objUser.getDiaChiNhaRieng());
			pst.setString(9, objUser.getFax());
			pst.setString(10, objUser.getDienThoaiCoQuan());
			pst.setString(11, objUser.getDienThoaiNhaRieng());
			pst.setString(12, objUser.getNamSinh());
			pst.setString(13, objUser.getAvt());
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
		
		String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocViHocHam from user AS u "
	       		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
	       		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
	       		+ " INNER JOIN  hocvihocham AS hv ON hv.idHocViHocHam = u.idHocViHocHam ORDER BY idUser DESC";
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   User objUser = new User(
					   rs.getInt("idUser"),
					   rs.getString("fullName"),
					   rs.getString("diaChiCoQuan") ,
			           rs.getString("dienThoaiCoQuan"),
			           rs.getInt("idHocViHocHam") ,
			           rs.getString("tenHocViHocHam"),
			           rs.getString("namSinh") ,
			           rs.getString("diaChiNhaRieng") , 
			           rs.getString("dienThoaiNhaRieng") ,
			           rs.getString("email") ,
			           rs.getString("fax"),rs.getString("userName") , 
			           rs.getString("matKhau") ,
			           rs.getInt("idLoaiTaiKhoan"),
			           rs.getString("tenLoaiTaiKhoan") ,
			           rs.getInt("idKhoa"), 
			           rs.getString("tenKhoa"),
			           rs.getString("avt"));

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
				+ "diaChiCoQuan,"
				+ "dienThoaiCoQuan,"
				+ "idHocViHocHam,"
				+ "namSinh,"
				+ "diaChiNhaRieng,"
				+ "dienThoaiNhaRieng,"
				+ "email,"
				+ "fax,"
				+ "userName,"
				+ "matKhau,"
				+ "idLoaiTaiKhoan,"
				+ "idKhoa,"
				+ "avt) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		try {
			pst = conn.prepareStatement(sql);
			
			pst.setString(1, objUser.getFullName());
			pst.setString(2, objUser.getDiaChiCoQuan());
			pst.setString(3, objUser.getDienThoaiCoQuan());
			pst.setInt(4, objUser.getIdHocViHocHam());
			pst.setString(5, objUser.getNamSinh());
			pst.setString(6, objUser.getDiaChiNhaRieng());
			pst.setString(7, objUser.getDienThoaiNhaRieng());
			pst.setString(8, objUser.getEmail());
			pst.setString(9, objUser.getFax());
			pst.setString(10, objUser.getUserName());
			pst.setString(11, objUser.getMatKhau());
			pst.setInt(12, objUser.getIdLoaiTaiKhoan());
			pst.setInt(13, objUser.getIdKhoa());
			pst.setString(14, objUser.getAvt());
			
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
	
	public int countUserSearch(String key, int loaiTaiKhoan, int khoa) {
		if ((key == "") && (loaiTaiKhoan == 0) && (khoa == 0)) return this.countUser();
		int total = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select count(*) AS total from user";
		if (key != "") {
			sql += " where ((userName like '%" 
				+ key 
				+ "%') or (fullName like '%" 
				+ key 
				+ "%') or (email like '%" 
				+ key 
				+ "%'))";
		}
		if (loaiTaiKhoan != 0){
			String pre = key != "" ? " and" : " where";
			sql += pre + "(idLoaiTaiKhoan = " + loaiTaiKhoan + ") ";
		}
		if (khoa != 0){
			String pre = key != "" || loaiTaiKhoan != 0 ? " and" : " where";
			sql += pre + "(u.idKhoa = " + khoa + ") ";
		}
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
		
		String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocViHocHam from user AS u "
	       		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
	       		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
	       		+ " INNER JOIN  hocvihocham AS hv ON hv.idHocViHocHam = u.idHocViHocHam ORDER BY idUser DESC LIMIT "+offset+","+LibraryConstant.ROW_COUNT;
		
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   User objUser = new User(
					   rs.getInt("idUser"),
					   rs.getString("fullName"),
					   rs.getString("diaChiCoQuan") ,
			           rs.getString("dienThoaiCoQuan"),
			           rs.getInt("idHocViHocHam") ,
			           rs.getString("tenHocViHocHam"),
			           rs.getString("namSinh") ,
			           rs.getString("diaChiNhaRieng") , 
			           rs.getString("dienThoaiNhaRieng") ,
			           rs.getString("email") ,
			           rs.getString("fax"),rs.getString("userName") , 
			           rs.getString("matKhau") ,
			           rs.getInt("idLoaiTaiKhoan"),
			           rs.getString("tenLoaiTaiKhoan") ,
			           rs.getInt("idKhoa"), 
			           rs.getString("tenKhoa"),
			           rs.getString("avt") );
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
	
	public ArrayList<User> getItemsByPageSearch(int offset, String key, int loaiTaiKhoan, int khoa) {
		if ((key == "") && (loaiTaiKhoan == 0) && (khoa == 0)) return this.getItemsByPage(offset);

		ArrayList<User> listUser = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocViHocHam from user AS u "
	       		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
	       		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
	       		+ " INNER JOIN  hocvihocham AS hv ON hv.idHocViHocHam = u.idHocViHocHam";
		if (key != "") {
			sql += " where ((userName like '%" 
				+ key 
				+ "%') or (fullName like '%" 
				+ key 
				+ "%') or (email like '%" 
				+ key 
				+ "%'))";
		}
		if (loaiTaiKhoan != 0){
			String pre = key != "" ? " and" : " where";
			sql += pre + "(u.idLoaiTaiKhoan = " + loaiTaiKhoan + ") ";
		}

		if (khoa != 0){
			String pre = key != "" || loaiTaiKhoan != 0 ? " and" : " where";
			sql += pre + "(u.idKhoa = " + khoa + ") ";
		}
		sql += " ORDER BY idUser DESC LIMIT "+offset+","+LibraryConstant.ROW_COUNT;
		System.out.println(sql);
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()){
			   User objUser = new User(
					   rs.getInt("idUser"),
					   rs.getString("fullName"),
					   rs.getString("diaChiCoQuan") ,
			           rs.getString("dienThoaiCoQuan"),
			           rs.getInt("idHocViHocHam") ,
			           rs.getString("tenHocViHocHam"),
			           rs.getString("namSinh") ,
			           rs.getString("diaChiNhaRieng") , 
			           rs.getString("dienThoaiNhaRieng") ,
			           rs.getString("email") ,
			           rs.getString("fax"),rs.getString("userName") , 
			           rs.getString("matKhau") ,
			           rs.getInt("idLoaiTaiKhoan"),
			           rs.getString("tenLoaiTaiKhoan") ,
			           rs.getInt("idKhoa"), 
			           rs.getString("tenKhoa"),
			           rs.getString("avt") );
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



	public int addThanhVienPublic(ThanhVien objTV) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql="insert into thanhvien (idThanhVien,tenThanhVien,donVi,noiDungNghienCuu,idDeTai) values(?,?,?,?,?)";
		
		try {
			pst = conn.prepareStatement(sql);
			
			
			
			pst.setInt(1, objTV.getIdThanhVien());
			pst.setString(2, objTV.getTenThanhVien());
			pst.setString(3, objTV.getDonVi());
			pst.setString(4, objTV.getNoiDungNghienCuu());
			pst.setInt(5, objTV.getIdDeTai());
			
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


	 public ThanhVien getObjThanhVien(int idTV,String tenTV,int idDeTai) {
			
	       conn = connectMySQLLibrary.getConnectMySQL();
	       
	       String sql = "select tv.idThanhVien,tv.tenThanhVien,tv.donVi,tv.noiDungNghienCuu, tv.idDeTai, dt.maSoDeTai"
	       		+ "  from thanhvien AS tv "
	       		+ "  INNER JOIN detai AS dt ON dt.idDeTai = tv.idDeTai where tv.idThanhVien = ? and tv.tenThanhVien = ? and tv.idDeTai = ? ";
	       	
	       
	       ThanhVien objTV = null;
	       try {
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, idTV);
				pst.setString(2, tenTV);
				pst.setInt(3, idDeTai);
				
				rs = pst.executeQuery();
				
				if(rs.next()){
				     objTV = new ThanhVien(rs.getInt("idThanhVien"),rs.getString("tenThanhVien"),rs.getString("donVi"),rs.getString("noiDungNghienCuu"), 
				    		               rs.getInt("idDeTai"),rs.getString("maSoDeTai"));
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
			
			return objTV;
		}


	 public int editThanhVienPublic(ThanhVien objTV,int idThanhVien1,int idDeTai1,String tenThanhVien1) {
			int result = 0;
			conn = connectMySQLLibrary.getConnectMySQL();
			
			String sql=" update thanhvien SET idThanhVien = ? , tenThanhVien = ?, donVi = ?, noiDungNghienCuu = ?, idDeTai = ? "
					 + " where idThanhVien = "+idThanhVien1+" and idDeTai = "+idDeTai1+" and tenThanhVien = '"+tenThanhVien1+"'";
			
			try {
				pst = conn.prepareStatement(sql);
				
				pst.setInt(1, objTV.getIdThanhVien());
				pst.setString(2, objTV.getTenThanhVien());
				pst.setString(3, objTV.getDonVi());
				pst.setString(4, objTV.getNoiDungNghienCuu());
				pst.setInt(5, objTV.getIdDeTai());
				
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


	 // lay danh sách giang vien có dang ki de tai
	 public ArrayList<User> getListGiangVienDetai(){
			ArrayList<User> listUser = new ArrayList<>();
			conn = connectMySQLLibrary.getConnectMySQL();
			
			 String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocViHocHam from user AS u "
			       		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
			       		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
			       		+ " INNER JOIN  hocvihocham AS hv ON hv.idHocViHocHam = u.idHocViHocHam where u.idUser in (select distinct idUser from detai)";
			
		    User objUser = null;
			  
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				
				while(rs.next()){
					 objUser = new User(rs.getInt("idUser"),rs.getString("fullName"),rs.getString("diaChiCoQuan") ,
				             rs.getString("dienThoaiCoQuan"),rs.getInt("idHocViHocHam") ,rs.getString("tenHocViHocHam"),rs.getString("namSinh") ,rs.getString("diaChiNhaRieng") , 
				             rs.getString("dienThoaiNhaRieng") ,rs.getString("email") ,rs.getString("fax"),rs.getString("userName") , 
				             rs.getString("matKhau") ,rs.getInt("idLoaiTaiKhoan"),rs.getString("tenLoaiTaiKhoan") ,rs.getInt("idKhoa"), rs.getString("tenKhoa") , rs.getString("avt"));
				                        
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
    /*
     * Author:Quoc
     * Create Date: 2/11/2017
     * */
	 public ArrayList<User> getListGV(int idFac,String key_word, int offset, int row_count){
			ArrayList<User> listUser = new ArrayList<>();
			conn = connectMySQLLibrary.getConnectMySQL();
			
			String sql = "select u.*, k.tenKhoa, ltk.tenLoaiTaiKhoan, hv.tenHocViHocHam from user AS u "
		        		+ " INNER JOIN loaitaikhoan AS ltk ON ltk.idLoaiTaiKhoan = u.idLoaiTaiKhoan  "
		        		+ " INNER JOIN  khoa AS k ON k.idKhoa = u.idKhoa "
		        		+ " INNER JOIN  hocvihocham AS hv ON hv.idHocViHocHam = u.idHocViHocHam where u.idLoaiTaiKhoan = 2";
			if(idFac != 0){
				 sql += " AND k.idKhoa = "+idFac;
			}
			if(!"".equals(key_word)){
					sql += " AND (u.idUser LIKE '"+key_word+"' OR u.fullName LIKE '%"+key_word+"%')";
			}
			 sql += " GROUP BY u.fullName LIMIT ?,?";
		    User objUser = null;	  
			try {
				pst = conn.prepareStatement(sql);
				pst.setInt(1, offset);
				pst.setInt(2,row_count);
				rs = pst.executeQuery();
				
				while(rs.next()){
					 objUser = new User(rs.getInt("idUser"),rs.getString("fullName"),rs.getString("diaChiCoQuan") ,
				             rs.getString("dienThoaiCoQuan"),rs.getInt("idHocViHocHam") ,rs.getString("tenHocViHocHam"),rs.getString("namSinh") ,rs.getString("diaChiNhaRieng") , 
				             rs.getString("dienThoaiNhaRieng") ,rs.getString("email") ,rs.getString("fax"),rs.getString("userName") , 
				             rs.getString("matKhau") ,rs.getInt("idLoaiTaiKhoan"),rs.getString("tenLoaiTaiKhoan") ,rs.getInt("idKhoa"), rs.getString("tenKhoa") , rs.getString("avt"));
				                        
					 listUser.add(objUser);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					pst.close();
					rs.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return listUser;
			
		}
	 
	public int getSumGV(int idFaculty, String key_word) {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "select count(*) AS soluong from user WHERE idLoaiTaiKhoan = 2";
		if(idFaculty != 0){
			sql += " AND idKhoa = "+idFaculty;
		}
		if(!"".equals(key_word)){
			sql += " AND (idUser LIKE '"+key_word+"' OR fullName LIKE '%"+key_word+"%')";
		}
		System.out.println(sql);
        try {
        	pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if(rs.next()){
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			 try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sum;
	}
	




	
	
}
