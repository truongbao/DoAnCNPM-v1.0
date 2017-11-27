package model.bean;

public class BieuMau {

	private int idBieuMau;
	private int idDeTai;
	private int idLoaiBieuMau;
	private String tenLoaiBieuMau;
	private String linkBieuMau;
	
	public BieuMau() {
		super();
	}

	public BieuMau(int idBieuMau, int idDeTai, int idLoaiBieuMau,
			String tenLoaiBieuMau, String linkBieuMau) {
		super();
		this.idBieuMau = idBieuMau;
		this.idDeTai = idDeTai;
		this.idLoaiBieuMau = idLoaiBieuMau;
		this.tenLoaiBieuMau = tenLoaiBieuMau;
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

	public int getIdLoaiBieuMau() {
		return idLoaiBieuMau;
	}

	public void setIdLoaiBieuMau(int idLoaiBieuMau) {
		this.idLoaiBieuMau = idLoaiBieuMau;
	}

	public String getTenLoaiBieuMau() {
		return tenLoaiBieuMau;
	}

	public void setTenLoaiBieuMau(String tenLoaiBieuMau) {
		this.tenLoaiBieuMau = tenLoaiBieuMau;
	}

	public String getLinkBieuMau() {
		return linkBieuMau;
	}

	public void setLinkBieuMau(String linkBieuMau) {
		this.linkBieuMau = linkBieuMau;
	}
	
	
	
	
	
}
