package model.bean;

public class ThanhVien {
	
	private int idThanhVien;
	private String tenThanhVien;
	private String donVi;
	private String noiDungNghienCuu;
	private int idDeTai;
	private String maSoDeTai;
	
	public ThanhVien() {
		super();
	}

	public ThanhVien(int idThanhVien, String tenThanhVien, String donVi,
			String noiDungNghienCuu, int idDeTai, String maSoDeTai) {
		super();
		this.idThanhVien = idThanhVien;
		this.tenThanhVien = tenThanhVien;
		this.donVi = donVi;
		this.noiDungNghienCuu = noiDungNghienCuu;
		this.idDeTai = idDeTai;
		this.maSoDeTai = maSoDeTai;
	}

	public int getIdThanhVien() {
		return idThanhVien;
	}

	public void setIdThanhVien(int idThanhVien) {
		this.idThanhVien = idThanhVien;
	}

	public String getTenThanhVien() {
		return tenThanhVien;
	}

	public void setTenThanhVien(String tenThanhVien) {
		this.tenThanhVien = tenThanhVien;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public String getNoiDungNghienCuu() {
		return noiDungNghienCuu;
	}

	public void setNoiDungNghienCuu(String noiDungNghienCuu) {
		this.noiDungNghienCuu = noiDungNghienCuu;
	}

	public int getIdDeTai() {
		return idDeTai;
	}

	public void setIdDeTai(int idDeTai) {
		this.idDeTai = idDeTai;
	}

	public String getMaSoDeTai() {
		return maSoDeTai;
	}

	public void setMaSoDeTai(String maSoDeTai) {
		this.maSoDeTai = maSoDeTai;
	}

	@Override
	public String toString() {
		return "ThanhVien [idThanhVien=" + idThanhVien + ", tenThanhVien="
				+ tenThanhVien + ", donVi=" + donVi + ", noiDungNghienCuu="
				+ noiDungNghienCuu + ", idDeTai=" + idDeTai + ", maSoDeTai="
				+ maSoDeTai + "]";
	}

	
	
	
}
