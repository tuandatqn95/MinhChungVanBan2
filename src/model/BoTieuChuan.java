package model;

public class BoTieuChuan {
	private int ID;
	private String maBoTieuChuan;
	private String tenBoTieuChuan;
	private String moTa;

	public BoTieuChuan() {
		super();
	}

	public BoTieuChuan(int iD, String maBoTieuChuan, String tenBoTieuChuan, String moTa) {
		super();
		ID = iD;
		this.maBoTieuChuan = maBoTieuChuan;
		this.tenBoTieuChuan = tenBoTieuChuan;
		this.moTa = moTa;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMaBoTieuChuan() {
		return maBoTieuChuan;
	}

	public void setMaBoTieuChuan(String maBoTieuChuan) {
		this.maBoTieuChuan = maBoTieuChuan;
	}

	public String getTenBoTieuChuan() {
		return tenBoTieuChuan;
	}

	public void setTenBoTieuChuan(String tenBoTieuChuan) {
		this.tenBoTieuChuan = tenBoTieuChuan;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

}
