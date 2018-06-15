package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TapTin;

public class TapTinDAO {
	public ArrayList<TapTin> GetListTapTin() throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM taptin";
		PreparedStatement ps = conn.prepareCall(sql);

		ArrayList<TapTin> list = new ArrayList<TapTin>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TapTin tt = new TapTin();
			tt.setID(rs.getInt("ID"));
			tt.setIDMinhChung(rs.getInt("IDMinhChung"));
			tt.setFilePath(rs.getString("FilePath"));
			tt.setFileType(rs.getString("FuleType"));
			list.add(tt);
		}
		return list;
	}

	public ArrayList<TapTin> GetListTapTinByMinhChung(int idMinhChung) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM taptin WHERE IDMinhChung = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, idMinhChung);
		ArrayList<TapTin> list = new ArrayList<TapTin>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TapTin tt = new TapTin();
			tt.setID(rs.getInt("ID"));
			tt.setIDMinhChung(rs.getInt("IDMinhChung"));
			tt.setFilePath(rs.getString("FilePath"));
			tt.setFileType(rs.getString("FileType"));
			list.add(tt);
		}
		return list;
	}
	
	public TapTin GetTapTinByMinhChung(int idMinhChung) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM taptin WHERE IDMinhChung = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, idMinhChung);
		ResultSet rs = ps.executeQuery();
		TapTin tt = new TapTin();
		while (rs.next()) {
			tt.setID(rs.getInt("ID"));
			tt.setIDMinhChung(rs.getInt("IDMinhChung"));
			tt.setFilePath(rs.getString("FilePath"));
			tt.setFileType(rs.getString("FileType"));
		}
		return tt;
	}

	public TapTin GetTapTinByID(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM taptin WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		TapTin tt = null;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			tt = new TapTin();
			tt.setID(rs.getInt("ID"));
			tt.setIDMinhChung(rs.getInt("IDMinhChung"));
			tt.setFilePath(rs.getString("FilePath"));
			tt.setFileType(rs.getString("FileType"));

		}
		conn.close();
		return tt;

	}

	public void ThemTapTin(int idMinhChung, String filePath, String fileType) throws SQLException {

		Connection conn = DBConnect.getConnection();

		String sql = "INSERT INTO taptin(IDMinhChung,FilePath,FileType) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, idMinhChung);
		ps.setString(2, filePath);
		ps.setString(3, fileType);
		ps.executeUpdate();
		conn.close();
	}

	public void XoaTapTin(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "DELETE FROM taptin WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		conn.close();
	}
}
