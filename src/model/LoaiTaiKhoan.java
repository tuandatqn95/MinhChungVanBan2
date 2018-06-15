package model;

public class LoaiTaiKhoan {
	private int ID;
	private String tenLoaiTaiKhoan;

	public LoaiTaiKhoan() {
		super();
	}

	public LoaiTaiKhoan(int iD, String tenLoaiTaiKhoan) {
		super();
		this.ID = iD;
		this.tenLoaiTaiKhoan = tenLoaiTaiKhoan;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getTenLoaiTaiKhoan() {
		return tenLoaiTaiKhoan;
	}

	public void setTenLoaiTaiKhoan(String tenLoaiTaiKhoan) {
		this.tenLoaiTaiKhoan = tenLoaiTaiKhoan;
	}

}
