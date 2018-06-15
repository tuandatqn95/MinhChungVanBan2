package model;

public class TapTin {
	private int ID;
	private int IDMinhChung;
	private String filePath;
	private String fileType;

	public TapTin() {
		super();
	}

	public TapTin(int iD, int iDMinhChung, String filePath, String fileType) {
		super();
		ID = iD;
		IDMinhChung = iDMinhChung;
		this.filePath = filePath;
		this.fileType = fileType;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getIDMinhChung() {
		return IDMinhChung;
	}

	public void setIDMinhChung(int iDMinhChung) {
		IDMinhChung = iDMinhChung;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

}
