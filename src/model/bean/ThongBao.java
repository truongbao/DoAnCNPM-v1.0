package model.bean;

import java.sql.Timestamp;

public class ThongBao {

	private int idThongBao;
	private int idUserThongBao;
	private int idUserDen;
	private String noiDung;
	private Timestamp thoiGian;
	private int idDeTai;
	private String tenDeTai;
	private String tenCNDT;
	private String tenNguoiGui;
	private int wasRead;
	
	public ThongBao() {
		super();
	}


	public ThongBao(int idThongBao, int idUserThongBao, int idUserDen, String noiDung,
			Timestamp thoiGian, int idDeTai, String tenDeTai, String tenCNDT, String tenNguoiGui, int wasRead) {
		super();
		this.idThongBao = idThongBao;
		this.idUserThongBao = idUserThongBao;
		this.idUserDen = idUserDen;
		this.noiDung = noiDung;
		this.thoiGian = thoiGian;
		this.idDeTai = idDeTai;
		this.tenDeTai = tenDeTai;
		this.tenCNDT = tenCNDT;
		this.tenNguoiGui = tenNguoiGui;
		this.wasRead = wasRead;
	}


	public String getTenNguoiGui() {
		return tenNguoiGui;
	}


	public void setTenNguoiGui(String tenNguoiGui) {
		this.tenNguoiGui = tenNguoiGui;
	}


	public int getWasRead() {
		return wasRead;
	}


	public void setWasRead(int wasRead) {
		this.wasRead = wasRead;
	}


	public String getTenDeTai() {
		return tenDeTai;
	}

	public void setTenDeTai(String tenDeTai) {
		this.tenDeTai = tenDeTai;
	}

	public String getTenCNDT() {
		return tenCNDT;
	}

	public void setTenCNDT(String tenCNDT) {
		this.tenCNDT = tenCNDT;
	}

	public int getIdThongBao() {
		return idThongBao;
	}

	public void setIdThongBao(int idThongBao) {
		this.idThongBao = idThongBao;
	}

	public int getIdUserThongBao() {
		return idUserThongBao;
	}

	public void setIdUserThongBao(int idUserThongBao) {
		this.idUserThongBao = idUserThongBao;
	}

	public int getIdUserDen() {
		return idUserDen;
	}

	public void setIdUserDen(int idUserDen) {
		this.idUserDen = idUserDen;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Timestamp getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Timestamp thoiGian) {
		this.thoiGian = thoiGian;
	}

	public int getIdDeTai() {
		return idDeTai;
	}

	public void setIdDeTai(int idDeTai) {
		this.idDeTai = idDeTai;
	}

	@Override
	public String toString() {
		return "ThongBao [idThongBao=" + idThongBao + ", idUserThongBao="
				+ idUserThongBao + ", idUserDen=" + idUserDen + ", noiDung="
				+ noiDung 
				+ ", thoiGian=" + thoiGian + ", idDeTai=" + idDeTai + "]";
	}

	

	
}
