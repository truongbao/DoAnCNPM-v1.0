package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import library.ConnectMySQLLibrary;
import model.bean.ThongBao;

public class ThongBaoDAO {
	private ConnectMySQLLibrary connectMySQLLibrary;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;

	public ThongBaoDAO() {
		connectMySQLLibrary = new ConnectMySQLLibrary();
	}

	public ArrayList<ThongBao> getListThongBaoByIdDeTai(int idDeTai) {

		ArrayList<ThongBao> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "select * FROM thongbao AS tb " 
				+ " INNER JOIN user AS userTB ON userTB.idUser = tb.idUserThongBao"
				+ " INNER JOIN user AS userDen ON userDen.idUser = tb.idUserDen"
				+ " INNER JOIN detai AS dt ON tb.idDeTai = dt.idDeTai "
				+ " where tb.idDeTai = ? ORDER BY tb.idThongBao DESC";

		ThongBao objTB = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idDeTai);
			rs = pst.executeQuery();

			while (rs.next()) {
				objTB = new ThongBao(rs.getInt("idThongBao"), rs.getInt("idUserThongBao"), rs.getInt("idUserDen"),
						rs.getString("noiDung"), rs.getTimestamp("thoiGian"),
						rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("userDen.fullName"),
						rs.getString("userTB.fullName"),rs.getInt("tb.wasRead"));
				listDeTai.add(objTB);
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

	/*
	 * Author: Quoc 
	 * Create date: 1/11/2017
	 * 
	 */
	// create THONGBAO
	public int  addItem(ThongBao objItem) {
		int result = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "INSERT INTO thongbao(idUserThongBao,idUserDen,noiDung,thoiGian,idDeTai,wasRead) VALUES (?,?,?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1,objItem.getIdUserThongBao());
			pst.setInt(2, objItem.getIdUserDen());
			pst.setString(3, objItem.getNoiDung());
			pst.setTimestamp(4, objItem.getThoiGian());
			pst.setInt(5, objItem.getIdDeTai());
			pst.setInt(6, objItem.getWasRead());
			pst.executeUpdate();
			result=1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
	// get list THONGBAO without offset, rowCount
	public ArrayList<ThongBao> getListThongBao() {

		ArrayList<ThongBao> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "select * FROM thongbao AS tb " 
				+ " INNER JOIN user AS userTB ON userTB.idUser = tb.idUserThongBao"
				+ " INNER JOIN user AS userDen ON userDen.idUser = tb.idUserDen"
				+ " INNER JOIN detai AS dt ON tb.idDeTai = dt.idDeTai "
				+ " ORDER BY tb.idThongBao DESC";

		ThongBao objTB = null;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				objTB = new ThongBao(rs.getInt("idThongBao"), rs.getInt("idUserThongBao"), rs.getInt("idUserDen"),
						rs.getString("noiDung"), rs.getTimestamp("thoiGian"),
						rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("userDen.fullName"),
						rs.getString("userTB.fullName"),rs.getInt("tb.wasRead"));
				listDeTai.add(objTB);
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

	// get list THONGBAO with offset, rowCount
	public ArrayList<ThongBao> getListThongBao(int offset, int rowCount) {

		ArrayList<ThongBao> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "select * FROM thongbao AS tb " 
				+ " INNER JOIN user AS userTB ON userTB.idUser = tb.idUserThongBao"
				+ " INNER JOIN user AS userDen ON userDen.idUser = tb.idUserDen"
				+ " INNER JOIN detai AS dt ON tb.idDeTai = dt.idDeTai "
				+ " ORDER BY tb.idThongBao DESC LIMIT ?,?";

		ThongBao objTB = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, rowCount);
			rs = pst.executeQuery();

			while (rs.next()) {
				objTB = new ThongBao(rs.getInt("idThongBao"), rs.getInt("idUserThongBao"), rs.getInt("idUserDen"),
						rs.getString("noiDung"), rs.getTimestamp("thoiGian"),
						rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("userDen.fullName"),
						rs.getString("userTB.fullName"),rs.getInt("tb.wasRead"));
				listDeTai.add(objTB);
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

	// get list THONGBAO with offset, rowCount
	public ArrayList<ThongBao> getListThongBaobyFaculty(int id_faculty, int offset, int rowCount) {

		ArrayList<ThongBao> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "select * FROM thongbao AS tb " 
				+ " INNER JOIN user AS userTB ON userTB.idUser = tb.idUserThongBao"
				+ " INNER JOIN user AS userDen ON userDen.idUser = tb.idUserDen"
				+ " INNER JOIN detai AS dt ON tb.idDeTai = dt.idDeTai ";
		if(id_faculty != 0){
				sql += " where userDen.idKhoa = "+id_faculty;
		}
		sql += " ORDER BY tb.idThongBao DESC LIMIT ?,?";
		
		ThongBao objTB = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, rowCount);
			rs = pst.executeQuery();

			while (rs.next()) {
				objTB = new ThongBao(rs.getInt("idThongBao"), rs.getInt("idUserThongBao"), rs.getInt("idUserDen"),
						rs.getString("noiDung"), rs.getTimestamp("thoiGian"),
						rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("userDen.fullName"),
						rs.getString("userTB.fullName"),rs.getInt("tb.wasRead"));
				listDeTai.add(objTB);
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
	
	// get list THONGBAO with offset, rowCount
	public ArrayList<ThongBao> getListThongBaoWithSearch(int id_faculty, String create_date, String key_word, int offset, int rowCount) {

		ArrayList<ThongBao> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();

		String sql = "select * FROM thongbao AS tb " 
				+ " INNER JOIN user AS userTB ON userTB.idUser = tb.idUserThongBao"
				+ " INNER JOIN user AS userDen ON userDen.idUser = tb.idUserDen"
				+ " INNER JOIN detai AS dt ON tb.idDeTai = dt.idDeTai ";
		sql = getSQLWithSearch(sql, id_faculty, create_date, key_word)+	" ORDER BY tb.idThongBao DESC LIMIT ?,?";
		System.out.println(sql);
		ThongBao objTB = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, offset);
			pst.setInt(2, rowCount);
			rs = pst.executeQuery();

			while (rs.next()) {
				objTB = new ThongBao(rs.getInt("idThongBao"), rs.getInt("idUserThongBao"), rs.getInt("idUserDen"),
						rs.getString("noiDung"), rs.getTimestamp("thoiGian"),
						rs.getInt("idDeTai"), rs.getString("tenDeTai"), rs.getString("userDen.fullName"),
						rs.getString("userTB.fullName"),rs.getInt("tb.wasRead"));
				listDeTai.add(objTB);
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

	public int getSum(int idFaculty) {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(idThongBao) AS soluong FROM thongbao AS tb"
				+ " INNER JOIN user ON user.idUser = tb.idUserDen ";
		if(idFaculty != 0){
			 sql += " where user.idKhoa = " + idFaculty;
		}
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sum;
	}

	public int getSumWithSearch(int idFaculty, String create_date, String key_word) {
		int sum = 0;
		conn = connectMySQLLibrary.getConnectMySQL();
		String sql = "SELECT COUNT(idThongBao) AS soluong FROM thongbao AS tb"
				+ " INNER JOIN user AS userDen ON userDen.idUser = tb.idUserDen " 
				+ " INNER JOIN detai AS dt ON tb.idDeTai = dt.idDeTai ";
		sql = getSQLWithSearch(sql, idFaculty, create_date, key_word);		
		System.out.println(sql);
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				sum = rs.getInt("soluong");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sum;
	}
	
	public String getSQLWithSearch(String sql, int idFaculty, String create_date, String key_word){
		if(idFaculty != 0){
			sql += " WHERE userDen.idKhoa = "+idFaculty+" AND ";
		}else{
			sql += " WHERE ";
		}
		if(!"".equals(create_date) && !"".equals(key_word)){
			sql += " (DATE(thoiGian) = '"+create_date+"' AND (tenDetai LIKE '%"+key_word+"%' OR userDen.fullName LIKE '%"+key_word+"%'))";
		}else if(!"".equals(create_date)){
			sql += " DATE(thoiGian) = '"+create_date+"'";
		}else{
			sql += " (tenDetai LIKE '%"+key_word+"%' OR userDen.fullName LIKE '%"+key_word+"%')";
		}
		return sql;
	}

}
