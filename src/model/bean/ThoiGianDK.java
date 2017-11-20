package model.bean;

import java.sql.Date;

public class ThoiGianDK {

	private int idThoiGianDK;
	private Date thoiGianBatDau;
	private Date thoiGianKetThuc;
	
	public ThoiGianDK() {
		super();
	}

	public ThoiGianDK(int idThoiGianDK, Date thoiGianBatDau, Date thoiGianKetThuc) {
		super();
		this.idThoiGianDK = idThoiGianDK;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public int getIdThoiGianDK() {
		return idThoiGianDK;
	}

	public void setIdThoiGianDK(int idThoiGianDK) {
		this.idThoiGianDK = idThoiGianDK;
	}

	public Date getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setTenThoiGianDK(Date thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public Date getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(Date thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}
	@Override
	public String toString() {
		return "ThoiGianDK [idThoiGianDK=" + idThoiGianDK + ", thoiGianBatDau=" + thoiGianBatDau.toString() + ", thoiGianKetThuc=" + thoiGianKetThuc.toString() + "]";
	}
	
}
