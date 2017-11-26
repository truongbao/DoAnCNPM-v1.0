package model.bean;

import java.sql.Timestamp;

public class DeTai {
	
	private int idDeTai;
	private String tenDeTai;
	private String maSoDeTai;
	private int idLinhVucNghienCuu;
	private String tenLinhVucNghienCuu;
	private int idLoaiHinhNghienCuu;
	private String tenLoaiHinhNghienCuu;
	private Timestamp thoiGianBatDau;
	private Timestamp thoiGianKetThuc;
	private String donViChuTri;
	private int idUser;
	private String fullName;
	private String donViPhoiHopChinh;
	private String tongQuan;
	private String tinhCapThiet;
	private String mucTieu;
	private String phamViNghienCuu;
	private String phuongPhapNghienCuu;
	private String noiDung;
	private String sanPham;
	private String hieuQua;
	private int kinhPhiThucHien;
	private String trangThai;
	private int idCapDeTai;
	private String tenCapDeTai;
	private Timestamp thoiGianDangKy;
    private int idKhoa;
    private String linkUpload;
	
	
	public DeTai() {
		super();
	}


	public DeTai(int idDeTai, String tenDeTai, String maSoDeTai,
			int idLinhVucNghienCuu, String tenLinhVucNghienCuu,
			int idLoaiHinhNghienCuu, String tenLoaiHinhNghienCuu,
			Timestamp thoiGianBatDau, Timestamp thoiGianKetThuc,
			String donViChuTri, int idUser, String fullName,
			String donViPhoiHopChinh, String tongQuan, String tinhCapThiet,
			String mucTieu, String phamViNghienCuu, String phuongPhapNghienCuu,
			String noiDung, String sanPham, String hieuQua,
			int kinhPhiThucHien, String trangThai, int idCapDeTai,
			String tenCapDeTai, Timestamp thoiGianDangKy, int idKhoa,
			String linkUpload) {
		super();
		this.idDeTai = idDeTai;
		this.tenDeTai = tenDeTai;
		this.maSoDeTai = maSoDeTai;
		this.idLinhVucNghienCuu = idLinhVucNghienCuu;
		this.tenLinhVucNghienCuu = tenLinhVucNghienCuu;
		this.idLoaiHinhNghienCuu = idLoaiHinhNghienCuu;
		this.tenLoaiHinhNghienCuu = tenLoaiHinhNghienCuu;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.donViChuTri = donViChuTri;
		this.idUser = idUser;
		this.fullName = fullName;
		this.donViPhoiHopChinh = donViPhoiHopChinh;
		this.tongQuan = tongQuan;
		this.tinhCapThiet = tinhCapThiet;
		this.mucTieu = mucTieu;
		this.phamViNghienCuu = phamViNghienCuu;
		this.phuongPhapNghienCuu = phuongPhapNghienCuu;
		this.noiDung = noiDung;
		this.sanPham = sanPham;
		this.hieuQua = hieuQua;
		this.kinhPhiThucHien = kinhPhiThucHien;
		this.trangThai = trangThai;
		this.idCapDeTai = idCapDeTai;
		this.tenCapDeTai = tenCapDeTai;
		this.thoiGianDangKy = thoiGianDangKy;
		this.idKhoa = idKhoa;
		this.linkUpload = linkUpload;
	}


	public int getIdDeTai() {
		return idDeTai;
	}


	public void setIdDeTai(int idDeTai) {
		this.idDeTai = idDeTai;
	}


	public String getTenDeTai() {
		return tenDeTai;
	}


	public void setTenDeTai(String tenDeTai) {
		this.tenDeTai = tenDeTai;
	}


	public String getMaSoDeTai() {
		return maSoDeTai;
	}


	public void setMaSoDeTai(String maSoDeTai) {
		this.maSoDeTai = maSoDeTai;
	}


	public int getIdLinhVucNghienCuu() {
		return idLinhVucNghienCuu;
	}


	public void setIdLinhVucNghienCuu(int idLinhVucNghienCuu) {
		this.idLinhVucNghienCuu = idLinhVucNghienCuu;
	}


	public String getTenLinhVucNghienCuu() {
		return tenLinhVucNghienCuu;
	}


	public void setTenLinhVucNghienCuu(String tenLinhVucNghienCuu) {
		this.tenLinhVucNghienCuu = tenLinhVucNghienCuu;
	}


	public int getIdLoaiHinhNghienCuu() {
		return idLoaiHinhNghienCuu;
	}


	public void setIdLoaiHinhNghienCuu(int idLoaiHinhNghienCuu) {
		this.idLoaiHinhNghienCuu = idLoaiHinhNghienCuu;
	}


	public String getTenLoaiHinhNghienCuu() {
		return tenLoaiHinhNghienCuu;
	}


	public void setTenLoaiHinhNghienCuu(String tenLoaiHinhNghienCuu) {
		this.tenLoaiHinhNghienCuu = tenLoaiHinhNghienCuu;
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


	public String getDonViChuTri() {
		return donViChuTri;
	}


	public void setDonViChuTri(String donViChuTri) {
		this.donViChuTri = donViChuTri;
	}


	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}


	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getDonViPhoiHopChinh() {
		return donViPhoiHopChinh;
	}


	public void setDonViPhoiHopChinh(String donViPhoiHopChinh) {
		this.donViPhoiHopChinh = donViPhoiHopChinh;
	}


	public String getTongQuan() {
		return tongQuan;
	}


	public void setTongQuan(String tongQuan) {
		this.tongQuan = tongQuan;
	}


	public String getTinhCapThiet() {
		return tinhCapThiet;
	}


	public void setTinhCapThiet(String tinhCapThiet) {
		this.tinhCapThiet = tinhCapThiet;
	}


	public String getMucTieu() {
		return mucTieu;
	}


	public void setMucTieu(String mucTieu) {
		this.mucTieu = mucTieu;
	}


	public String getPhamViNghienCuu() {
		return phamViNghienCuu;
	}


	public void setPhamViNghienCuu(String phamViNghienCuu) {
		this.phamViNghienCuu = phamViNghienCuu;
	}


	public String getPhuongPhapNghienCuu() {
		return phuongPhapNghienCuu;
	}


	public void setPhuongPhapNghienCuu(String phuongPhapNghienCuu) {
		this.phuongPhapNghienCuu = phuongPhapNghienCuu;
	}


	public String getNoiDung() {
		return noiDung;
	}


	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}


	public String getSanPham() {
		return sanPham;
	}


	public void setSanPham(String sanPham) {
		this.sanPham = sanPham;
	}


	public String getHieuQua() {
		return hieuQua;
	}


	public void setHieuQua(String hieuQua) {
		this.hieuQua = hieuQua;
	}


	public int getKinhPhiThucHien() {
		return kinhPhiThucHien;
	}


	public void setKinhPhiThucHien(int kinhPhiThucHien) {
		this.kinhPhiThucHien = kinhPhiThucHien;
	}


	public String getTrangThai() {
		return trangThai;
	}


	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}


	public int getIdCapDeTai() {
		return idCapDeTai;
	}


	public void setIdCapDeTai(int idCapDeTai) {
		this.idCapDeTai = idCapDeTai;
	}


	public String getTenCapDeTai() {
		return tenCapDeTai;
	}


	public void setTenCapDeTai(String tenCapDeTai) {
		this.tenCapDeTai = tenCapDeTai;
	}


	public Timestamp getThoiGianDangKy() {
		return thoiGianDangKy;
	}


	public void setThoiGianDangKy(Timestamp thoiGianDangKy) {
		this.thoiGianDangKy = thoiGianDangKy;
	}


	public int getIdKhoa() {
		return idKhoa;
	}


	public void setIdKhoa(int idKhoa) {
		this.idKhoa = idKhoa;
	}


	public String getLinkUpload() {
		return linkUpload;
	}


	public void setLinkUpload(String linkUpload) {
		this.linkUpload = linkUpload;
	}

	
	
}
