package model.bean;

public class Cat {

	 private int idCat;
	 private String nameCat;
	 private int id_parent;
	 private int active_cat;
	public Cat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cat(int idCat, String nameCat, int id_parent, int active_cat) {
		super();
		this.idCat = idCat;
		this.nameCat = nameCat;
		this.id_parent = id_parent;
		this.active_cat = active_cat;
	}
	public int getIdCat() {
		return idCat;
	}
	public void setIdCat(int idCat) {
		this.idCat = idCat;
	}
	public String getNameCat() {
		return nameCat;
	}
	public void setNameCat(String nameCat) {
		this.nameCat = nameCat;
	}
	public int getId_parent() {
		return id_parent;
	}
	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}
	public int getActive_cat() {
		return active_cat;
	}
	public void setActive_cat(int active_cat) {
		this.active_cat = active_cat;
	}
	@Override
	public String toString() {
		return "Cat [idCat=" + idCat + ", nameCat=" + nameCat + ", id_parent="
				+ id_parent + ", active_cat=" + active_cat + "]";
	}
	 
	 
	 
	
}
