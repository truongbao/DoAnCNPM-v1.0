package model.bean;

import java.sql.Timestamp;

public class QuaTrinhThucHien {

	private int idQuaTrinhThucHien;
	private int idGiangVien;
	private int idDeTai;
	private String trangThai;
	private Timestamp thoiGian;
	private String chuDe;
	private String noiDung;
	
	public QuaTrinhThucHien() {
		super();
	}

	public QuaTrinhThucHien(int idQuaTrinhThucHien, int idGiangVien,
			int idDeTai, String trangThai, Timestamp thoiGian, String chuDe,
			String noiDung) {
		super();
		this.idQuaTrinhThucHien = idQuaTrinhThucHien;
		this.idGiangVien = idGiangVien;
		this.idDeTai = idDeTai;
		this.trangThai = trangThai;
		this.thoiGian = thoiGian;
		this.chuDe = chuDe;
		this.noiDung = noiDung;
	}

	public int getIdQuaTrinhThucHien() {
		return idQuaTrinhThucHien;
	}

	public void setIdQuaTrinhThucHien(int idQuaTrinhThucHien) {
		this.idQuaTrinhThucHien = idQuaTrinhThucHien;
	}

	public int getIdGiangVien() {
		return idGiangVien;
	}

	public void setIdGiangVien(int idGiangVien) {
		this.idGiangVien = idGiangVien;
	}

	public int getIdDeTai() {
		return idDeTai;
	}

	public void setIdDeTai(int idDeTai) {
		this.idDeTai = idDeTai;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public Timestamp getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Timestamp thoiGian) {
		this.thoiGian = thoiGian;
	}

	public String getChuDe() {
		return chuDe;
	}

	public void setChuDe(String chuDe) {
		this.chuDe = chuDe;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	@Override
	public String toString() {
		return "QuaTrinhThucHien [idQuaTrinhThucHien=" + idQuaTrinhThucHien
				+ ", idGiangVien=" + idGiangVien + ", idDeTai=" + idDeTai
				+ ", trangThai=" + trangThai + ", thoiGian=" + thoiGian
				+ ", chuDe=" + chuDe + ", noiDung=" + noiDung + "]";
	}

	
}
