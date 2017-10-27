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

	
	public ArrayList<ThongBao> getListThongBaoByIdDeTai(int idDeTai ) {

		ArrayList<ThongBao> listDeTai = new ArrayList<>();
		conn = connectMySQLLibrary.getConnectMySQL();
		
		String sql = "select * FROM thongbao AS tb "
				+ " INNER JOIN detai AS dt ON tb.idDeTai = dt.idDeTai "
				+ " where tb.idDeTai = ? ORDER BY tb.idThongBao DESC";
		
		ThongBao objTB = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, idDeTai);
			rs = pst.executeQuery();

			while (rs.next()) {
				
				objTB = new ThongBao(rs.getInt("idThongBao"),rs.getInt("idUserThongBao") ,rs.getInt("idUserDen"),
						             rs.getString("noiDung"),rs.getInt("idQuaTrinhThucHien"),rs.getTimestamp("thoiGian"),
						             rs.getInt("idDeTai") );
				
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
	
	
	

	
}
