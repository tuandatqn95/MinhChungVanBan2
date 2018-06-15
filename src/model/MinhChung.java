package model;

import java.sql.Date;

public class MinhChung {
	private int ID;
	private int IDTieuChi;
	private String maMinhChung;
	private String tenMinhChung;
	private String moTa;
	private String soHieu;
	private Date ngayBanhanh;
	private int noiBanHanh;

	public MinhChung() {
		super();
	}

	public MinhChung(int iD, int iDTieuChi, String maMinhChung, String tenMinhChung, String moTa, String soHieu,
			Date ngayBanhanh, int noiBanHanh) {
		super();
		ID = iD;
		IDTieuChi = iDTieuChi;
		this.maMinhChung = maMinhChung;
		this.tenMinhChung = tenMinhChung;
		this.moTa = moTa;
		this.soHieu = soHieu;
		this.ngayBanhanh = ngayBanhanh;
		this.noiBanHanh = noiBanHanh;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getIDTieuChi() {
		return IDTieuChi;
	}

	public void setIDTieuChi(int iDTieuChi) {
		IDTieuChi = iDTieuChi;
	}

	public String getMaMinhChung() {
		return maMinhChung;
	}

	public void setMaMinhChung(String maMinhChung) {
		this.maMinhChung = maMinhChung;
	}

	public String getTenMinhChung() {
		return tenMinhChung;
	}

	public void setTenMinhChung(String tenMinhChung) {
		this.tenMinhChung = tenMinhChung;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getSoHieu() {
		return soHieu;
	}

	public void setSoHieu(String soHieu) {
		this.soHieu = soHieu;
	}

	public Date getNgayBanhanh() {
		return ngayBanhanh;
	}

	public void setNgayBanhanh(Date ngayBanhanh) {
		this.ngayBanhanh = ngayBanhanh;
	}

	public int getNoiBanHanh() {
		return noiBanHanh;
	}

	public void setNoiBanHanh(int noiBanHanh) {
		this.noiBanHanh = noiBanHanh;
	}

}
