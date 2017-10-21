package model.bean;

import java.sql.Timestamp;

public class ThongBao {

	private int idThongBao;
	private int idUserThongBao;
	private int idUserDen;
	private String noiDung;
	private int idQuaTrinhThucHien;
	private Timestamp thoiGian;
	
	public ThongBao() {
		super();
	}

	public ThongBao(int idThongBao, int idUserThongBao, int idUserDen,
			String noiDung, int idQuaTrinhThucHien, Timestamp thoiGian) {
		super();
		this.idThongBao = idThongBao;
		this.idUserThongBao = idUserThongBao;
		this.idUserDen = idUserDen;
		this.noiDung = noiDung;
		this.idQuaTrinhThucHien = idQuaTrinhThucHien;
		this.thoiGian = thoiGian;
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

	public int getIdQuaTrinhThucHien() {
		return idQuaTrinhThucHien;
	}

	public void setIdQuaTrinhThucHien(int idQuaTrinhThucHien) {
		this.idQuaTrinhThucHien = idQuaTrinhThucHien;
	}

	public Timestamp getThoiGian() {
		return thoiGian;
	}

	public void setThoiGian(Timestamp thoiGian) {
		this.thoiGian = thoiGian;
	}

	@Override
	public String toString() {
		return "ThongBao [idThongBao=" + idThongBao + ", idUserThongBao="
				+ idUserThongBao + ", idUserDen=" + idUserDen + ", noiDung="
				+ noiDung + ", idQuaTrinhThucHien=" + idQuaTrinhThucHien
				+ ", thoiGian=" + thoiGian + "]";
	}

	
}
