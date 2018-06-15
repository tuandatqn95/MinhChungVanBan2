package model;

public class NoiBanHanh {
	private int ID;
	private String maNoiBanHanh;
	private String tenNoiBanHanh;

	public NoiBanHanh() {
		super();
	}

	public NoiBanHanh(int iD, String maNoiBanHanh, String tenNoiBanHanh) {
		super();
		ID = iD;
		this.maNoiBanHanh = maNoiBanHanh;
		this.tenNoiBanHanh = tenNoiBanHanh;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getMaNoiBanHanh() {
		return maNoiBanHanh;
	}

	public void setMaNoiBanHanh(String maNoiBanHanh) {
		this.maNoiBanHanh = maNoiBanHanh;
	}

	public String getTenNoiBanHanh() {
		return tenNoiBanHanh;
	}

	public void setTenNoiBanHanh(String tenNoiBanHanh) {
		this.tenNoiBanHanh = tenNoiBanHanh;
	}

}
