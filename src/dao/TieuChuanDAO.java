package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TieuChuan;

public class TieuChuanDAO {
	public ArrayList<TieuChuan> getListTieuChuan() throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM tieuchuan";
		PreparedStatement ps = conn.prepareCall(sql);

		ArrayList<TieuChuan> list = new ArrayList<TieuChuan>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TieuChuan tc = new TieuChuan();
			tc.setID(rs.getInt("ID"));
			tc.setIDBoTieuChuan(rs.getInt("IDBoTieuChuan"));
			tc.setMaTieuChuan(rs.getString("MaTieuChuan"));
			tc.setTenTieuChuan(rs.getString("TenTieuChuan"));
			list.add(tc);
		}
		conn.close();
		return list;
	}

	public ArrayList<TieuChuan> getListTieuChuanByBoTieuChuan(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM tieuchuan WHERE IDBoTieuChuan=?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ArrayList<TieuChuan> list = new ArrayList<TieuChuan>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TieuChuan tc = new TieuChuan();
			tc.setID(rs.getInt("ID"));
			tc.setIDBoTieuChuan(rs.getInt("IDBoTieuChuan"));
			tc.setMaTieuChuan(rs.getString("MaTieuChuan"));
			tc.setTenTieuChuan(rs.getString("TenTieuChuan"));
			list.add(tc);
		}
		conn.close();
		return list;
	}

	public void ThemTieuChuan(int idBoTieuChuan, String maTC, String tenTC) throws SQLException {
		if (maTC != "" && tenTC != "") {
			Connection conn = DBConnect.getConnection();
			String sql = "INSERT INTO tieuchuan(IDBoTieuChuan,MaTieuChuan,TenTieuChuan) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, idBoTieuChuan);
			ps.setString(2, maTC);
			ps.setString(3, tenTC);
			ps.executeUpdate();
			conn.close();
		}
	}

	public TieuChuan GetTieuChuanByID(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM tieuchuan WHERE ID=?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		TieuChuan tc = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			tc = new TieuChuan();
			tc.setID(rs.getInt("ID"));
			tc.setIDBoTieuChuan(rs.getInt("IDBoTieuChuan"));
			tc.setMaTieuChuan(rs.getString("MaTieuChuan"));
			tc.setTenTieuChuan(rs.getString("TenTieuChuan"));
		}
		conn.close();
		return tc;
	}

	public void SuaTieuChuan(int idTieuChuan, int idBoTieuChuan, String maTC, String tenTC) throws SQLException {
		if (maTC != "" && tenTC != "") {
			Connection conn = DBConnect.getConnection();

			String sql = "UPDATE tieuchuan SET IDBoTieuChuan=?,MaTieuChuan=?,TenTieuChuan=? WHERE ID = ?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, idBoTieuChuan);
			ps.setString(2, maTC);
			ps.setString(3, tenTC);
			ps.setInt(4, idTieuChuan);
			ps.executeUpdate();
			conn.close();
		}
	}

	public void XoaTieuChuan(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "DELETE FROM tieuchuan WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		conn.close();
	}

	public static void main(String[] agrs) throws SQLException {
		// TieuChuanDAO dao = new TieuChuanDAO();

	}

}
