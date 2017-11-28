package model.bean;

public class BieuMau {

	private int idBieuMau;
	private int idDeTai;
	private String maBieuMau;
	private String linkBieuMau;
	
	public BieuMau() {
		super();
	}

	public BieuMau(int idBieuMau, int idDeTai, String maBieuMau,
			String linkBieuMau) {
		super();
		this.idBieuMau = idBieuMau;
		this.idDeTai = idDeTai;
		this.maBieuMau = maBieuMau;
		this.linkBieuMau = linkBieuMau;
	}

	public int getIdBieuMau() {
		return idBieuMau;
	}

	public void setIdBieuMau(int idBieuMau) {
		this.idBieuMau = idBieuMau;
	}

	public int getIdDeTai() {
		return idDeTai;
	}

	public void setIdDeTai(int idDeTai) {
		this.idDeTai = idDeTai;
	}

	public String getMaBieuMau() {
		return maBieuMau;
	}

	public void setMaBieuMau(String maBieuMau) {
		this.maBieuMau = maBieuMau;
	}

	public String getLinkBieuMau() {
		return linkBieuMau;
	}

	public void setLinkBieuMau(String linkBieuMau) {
		this.linkBieuMau = linkBieuMau;
	}

	
	
	
	
	
	
	
}
