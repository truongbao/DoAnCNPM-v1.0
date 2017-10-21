package model.bean;

public class LoaiTaiKhoan {

	private int  idLoaiTaiKhoan;
	private String  tenLoaiTaiKhoan;
	
	public LoaiTaiKhoan() {
		super();
	}

	public LoaiTaiKhoan(int idLoaiTaiKhoan, String tenLoaiTaiKhoan) {
		super();
		this.idLoaiTaiKhoan = idLoaiTaiKhoan;
		this.tenLoaiTaiKhoan = tenLoaiTaiKhoan;
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

	@Override
	public String toString() {
		return "LoaiTaiKhoan [idLoaiTaiKhoan=" + idLoaiTaiKhoan
				+ ", tenLoaiTaiKhoan=" + tenLoaiTaiKhoan + "]";
	}

	
	
}
