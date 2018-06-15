package model;

import java.sql.Date;

public class TaiKhoan {
	private int ID;
	private String email;
	private String matKhau;
	private int IDLoaiTK;
	private String hoTen;
	private boolean nu;
	private Date ngaySinh;
	private String diaChi;
	private String noiCongTac;
	private String chucVu;
	private String soDienThoai;
	private String anhDaiDien;

	public TaiKhoan() {
		super();
	}

	public TaiKhoan(int iD, String email, String matKhau, int iDLoaiTK, String hoTen, boolean nu, Date ngaySinh,
			String diaChi, String noiCongTac, String chucVu, String soDienThoai, String anhDaiDien) {
		super();
		ID = iD;
		this.email = email;
		this.matKhau = matKhau;
		IDLoaiTK = iDLoaiTK;
		this.hoTen = hoTen;
		this.nu = nu;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.noiCongTac = noiCongTac;
		this.chucVu = chucVu;
		this.soDienThoai = soDienThoai;
		this.anhDaiDien = anhDaiDien;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public int getIDLoaiTK() {
		return IDLoaiTK;
	}

	public void setIDLoaiTK(int iDLoaiTK) {
		IDLoaiTK = iDLoaiTK;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public boolean isNu() {
		return nu;
	}

	public void setNu(boolean nu) {
		this.nu = nu;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getNoiCongTac() {
		return noiCongTac;
	}

	public void setNoiCongTac(String noiCongTac) {
		this.noiCongTac = noiCongTac;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getAnhDaiDien() {
		return anhDaiDien;
	}

	public void setAnhDaiDien(String anhDaiDien) {
		this.anhDaiDien = anhDaiDien;
	}

}
