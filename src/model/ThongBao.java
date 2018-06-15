package model;

import java.sql.Date;

public class ThongBao {
	private int ID;
	private String tieuDe;
	private String noiDung;
	private Date thoiGianDang;

	public ThongBao() {
		super();
	}

	public ThongBao(int iD, String tieuDe, String noiDung, Date thoiGianDang) {
		super();
		ID = iD;
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
		this.thoiGianDang = thoiGianDang;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Date getThoiGianDang() {
		return thoiGianDang;
	}

	public void setThoiGianDang(Date thoiGianDang) {
		this.thoiGianDang = thoiGianDang;
	}

}
