package model.bean;

public class User {
	
	private int idUser;
	private String fullName;
	private String diaChiCoQuan;
	private String dienThoaiCoQuan;
	private int idHocViHocHam;
	private String tenHocViHocHam;
	private String namSinh;
	private String diaChiNhaRieng;
	private String dienThoaiNhaRieng;
	private String email;
	private String fax;
	private String userName;
	private String matKhau;
	private int idLoaiTaiKhoan;
	private String tenLoaiTaiKhoan;
	private int idKhoa;
	private String tenKhoa;
	private String avt;
	
	public User() {
		super();
	}

	public User(int idUser, String fullName,
			String diaChiCoQuan, String dienThoaiCoQuan, int idHocViHocHam,
			String tenHocViHocHam, String namSinh, String diaChiNhaRieng,
			String dienThoaiNhaRieng, String email, String fax,
			String userName, String matKhau, int idLoaiTaiKhoan,
			String tenLoaiTaiKhoan, int idKhoa, String tenKhoa, String avt) {
		super();
		this.idUser = idUser;
		this.fullName = fullName;
		this.diaChiCoQuan = diaChiCoQuan;
		this.dienThoaiCoQuan = dienThoaiCoQuan;
		this.idHocViHocHam = idHocViHocHam;
		this.tenHocViHocHam = tenHocViHocHam;
		this.namSinh = namSinh;
		this.diaChiNhaRieng = diaChiNhaRieng;
		this.dienThoaiNhaRieng = dienThoaiNhaRieng;
		this.email = email;
		this.fax = fax;
		this.userName = userName;
		this.matKhau = matKhau;
		this.idLoaiTaiKhoan = idLoaiTaiKhoan;
		this.tenLoaiTaiKhoan = tenLoaiTaiKhoan;
		this.idKhoa = idKhoa;
		this.tenKhoa = tenKhoa;
		this.avt = avt;
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

	public String getDiaChiCoQuan() {
		return diaChiCoQuan;
	}

	public void setDiaChiCoQuan(String diaChiCoQuan) {
		this.diaChiCoQuan = diaChiCoQuan;
	}

	public String getDienThoaiCoQuan() {
		return dienThoaiCoQuan;
	}

	public void setDienThoaiCoQuan(String dienThoaiCoQuan) {
		this.dienThoaiCoQuan = dienThoaiCoQuan;
	}

	public int getIdHocViHocHam() {
		return idHocViHocHam;
	}

	public void setIdHocViHocHam(int idHocViHocHam) {
		this.idHocViHocHam = idHocViHocHam;
	}

	public String getTenHocViHocHam() {
		return tenHocViHocHam;
	}

	public void setTenHocViHocHam(String tenHocViHocHam) {
		this.tenHocViHocHam = tenHocViHocHam;
	}

	public String getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(String namSinh) {
		this.namSinh = namSinh;
	}

	public String getDiaChiNhaRieng() {
		return diaChiNhaRieng;
	}

	public void setDiaChiNhaRieng(String diaChiNhaRieng) {
		this.diaChiNhaRieng = diaChiNhaRieng;
	}

	public String getDienThoaiNhaRieng() {
		return dienThoaiNhaRieng;
	}

	public void setDienThoaiNhaRieng(String dienThoaiNhaRieng) {
		this.dienThoaiNhaRieng = dienThoaiNhaRieng;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public int getIdLoaiTaiKhoan() {
		return idLoaiTaiKhoan;
	}

	public void setIdLoaiTaiKhoan(int idLoaiTaiKhoan) {
		this.idLoaiTaiKhoan = idLoaiTaiKhoan;
	}

	public String getTenLoaiTaiKhoan() {
		return tenLoaiTaiKhoan;
	}

	public void setTenLoaiTaiKhoan(String tenLoaiTaiKhoan) {
		this.tenLoaiTaiKhoan = tenLoaiTaiKhoan;
	}

	public int getIdKhoa() {
		return idKhoa;
	}

	public void setIdKhoa(int idKhoa) {
		this.idKhoa = idKhoa;
	}

	public String getTenKhoa() {
		return tenKhoa;
	}

	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}


	public String getAvt() {
		return avt;
	}

	public void setAvt(String avt) {
		this.avt = avt;
	}
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", fullName=" + fullName
				+ ", chucDanhKhoaHoc=" + ", diaChiCoQuan="
				+ diaChiCoQuan + ", dienThoaiCoQuan=" + dienThoaiCoQuan
				+ ", idHocViHocHam=" + idHocViHocHam + ", tenHocViHocHam=" + tenHocViHocHam
				+ ", namSinh=" + namSinh + ", diaChiNhaRieng=" + diaChiNhaRieng
				+ ", dienThoaiNhaRieng=" + dienThoaiNhaRieng + ", email="
				+ email + ", fax=" + fax + ", userName=" + userName
				+ ", matKhau=" + matKhau + ", idLoaiTaiKhoan=" + idLoaiTaiKhoan
				+ ", tenLoaiTaiKhoan=" + tenLoaiTaiKhoan + ", idKhoa=" + idKhoa
				+ ", tenKhoa=" + tenKhoa + "]";
	}

	
	
	

	
}
