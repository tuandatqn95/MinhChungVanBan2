package model;

public class TieuChi {
	private int ID;
	private int IDTieuChuan;
	private String maTieuChi;
	private String tenTieuChi;

	public TieuChi() {
		super();
	}

	public TieuChi(int iD, int iDTieuChuan, String maTieuChi, String tenTieuChi) {
		super();
		ID = iD;
		IDTieuChuan = iDTieuChuan;
		this.maTieuChi = maTieuChi;
		this.tenTieuChi = tenTieuChi;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getIDTieuChuan() {
		return IDTieuChuan;
	}

	public void setIDTieuChuan(int iDTieuChuan) {
		IDTieuChuan = iDTieuChuan;
	}

	public String getMaTieuChi() {
		return maTieuChi;
	}

	public void setMaTieuChi(String maTieuChi) {
		this.maTieuChi = maTieuChi;
	}

	public String getTenTieuChi() {
		return tenTieuChi;
	}

	public void setTenTieuChi(String tenTieuChi) {
		this.tenTieuChi = tenTieuChi;
	}

}
