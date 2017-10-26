package model.bean;

import java.sql.Timestamp;

public class HopDong {
	
	private int idHopDong;
	private String tenKhachHang;
	private String chucVuKH;
	private String diaChiKH;
	private String emailKH;
	private int idGiangVien;
	private int idDeTai;
	private Timestamp thoiGianBatDau;
	private Timestamp thoiGianKetThuc;
	private int kinhPhi;
	private Timestamp thoiGianKyHopDong;
	private String dienThoaiKH;
	private String trangThaiHopDong;
	
	
	public HopDong() {
		super();
	}


	public HopDong(int idHopDong, String tenKhachHang, String chucVuKH,
			String diaChiKH, String emailKH, 
			int idGiangVien, int idDeTai, Timestamp thoiGianBatDau,
			Timestamp thoiGianKetThuc, int kinhPhi,
			Timestamp thoiGianKyHopDong, String dienThoaiKH,
			String trangThaiHopDong) {
		super();
		this.idHopDong = idHopDong;
		this.tenKhachHang = tenKhachHang;
		this.chucVuKH = chucVuKH;
		this.diaChiKH = diaChiKH;
		this.emailKH = emailKH;
		this.idGiangVien = idGiangVien;
		this.idDeTai = idDeTai;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.kinhPhi = kinhPhi;
		this.thoiGianKyHopDong = thoiGianKyHopDong;
		this.dienThoaiKH = dienThoaiKH;
		this.trangThaiHopDong = trangThaiHopDong;
	}


	public int getIdHopDong() {
		return idHopDong;
	}


	public void setIdHopDong(int idHopDong) {
		this.idHopDong = idHopDong;
	}


	public String getTenKhachHang() {
		return tenKhachHang;
	}


	public void setTenKhachHang(String tenKhachHang) {
		this.tenKhachHang = tenKhachHang;
	}


	public String getChucVuKH() {
		return chucVuKH;
	}


	public void setChucVuKH(String chucVuKH) {
		this.chucVuKH = chucVuKH;
	}


	public String getDiaChiKH() {
		return diaChiKH;
	}


	public void setDiaChiKH(String diaChiKH) {
		this.diaChiKH = diaChiKH;
	}


	public String getEmailKH() {
		return emailKH;
	}


	public void setEmailKH(String emailKH) {
		this.emailKH = emailKH;
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


	public Timestamp getThoiGianBatDau() {
		return thoiGianBatDau;
	}


	public void setThoiGianBatDau(Timestamp thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}


	public Timestamp getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}


	public void setThoiGianKetThuc(Timestamp thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}


	public int getKinhPhi() {
		return kinhPhi;
	}


	public void setKinhPhi(int kinhPhi) {
		this.kinhPhi = kinhPhi;
	}


	public Timestamp getThoiGianKyHopDong() {
		return thoiGianKyHopDong;
	}


	public void setThoiGianKyHopDong(Timestamp thoiGianKyHopDong) {
		this.thoiGianKyHopDong = thoiGianKyHopDong;
	}


	public String getDienThoaiKH() {
		return dienThoaiKH;
	}


	public void setDienThoaiKH(String dienThoaiKH) {
		this.dienThoaiKH = dienThoaiKH;
	}


	public String getTrangThaiHopDong() {
		return trangThaiHopDong;
	}


	public void setTrangThaiHopDong(String trangThaiHopDong) {
		this.trangThaiHopDong = trangThaiHopDong;
	}


	@Override
	public String toString() {
		return "HopDong [idHopDong=" + idHopDong + ", tenKhachHang="
				+ tenKhachHang + ", chucVuKH=" + chucVuKH + ", diaChiKH="
				+ diaChiKH + ", emailKH=" + emailKH + ", idNguoiDaiDien="
				+ ", idGiangVien=" + idGiangVien
				+ ", idDeTai=" + idDeTai + ", thoiGianBatDau=" + thoiGianBatDau
				+ ", thoiGianKetThuc=" + thoiGianKetThuc + ", kinhPhi="
				+ kinhPhi + ", thoiGianKyHopDong=" + thoiGianKyHopDong
				+ ", dienThoaiKH=" + dienThoaiKH + ", trangThaiHopDong="
				+ trangThaiHopDong + "]";
	}

    
	
	

}
