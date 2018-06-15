package model;

public class TieuChuan {
	private int ID;
	private int IDBoTieuChuan;
	private String maTieuChuan;
	private String tenTieuChuan;

	public TieuChuan() {
		super();
	}

	public TieuChuan(int iD, int iDBoTieuChuan, String maTieuChuan, String tenTieuChuan) {
		super();
		ID = iD;
		IDBoTieuChuan = iDBoTieuChuan;
		this.maTieuChuan = maTieuChuan;
		this.tenTieuChuan = tenTieuChuan;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getIDBoTieuChuan() {
		return IDBoTieuChuan;
	}

	public void setIDBoTieuChuan(int iDBoTieuChuan) {
		IDBoTieuChuan = iDBoTieuChuan;
	}

	public String getMaTieuChuan() {
		return maTieuChuan;
	}

	public void setMaTieuChuan(String maTieuChuan) {
		this.maTieuChuan = maTieuChuan;
	}

	public String getTenTieuChuan() {
		return tenTieuChuan;
	}

	public void setTenTieuChuan(String tenTieuChuan) {
		this.tenTieuChuan = tenTieuChuan;
	}

}
