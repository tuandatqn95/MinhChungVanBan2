package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TieuChi;

public class TieuChiDAO {

	public ArrayList<TieuChi> getListTieuChi() throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM tieuchi ORDER BY IDTieuChuan";
		PreparedStatement ps = conn.prepareCall(sql);

		ArrayList<TieuChi> list = new ArrayList<TieuChi>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TieuChi tc = new TieuChi();
			tc.setID(rs.getInt("ID"));
			tc.setIDTieuChuan(rs.getInt("IDTieuChuan"));
			tc.setMaTieuChi(rs.getString("MaTieuChi"));
			tc.setTenTieuChi(rs.getString("TenTieuChi"));
			list.add(tc);
		}

		return list;
	}

	public ArrayList<TieuChi> getListTieuChiByTieuChuan(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM tieuchi WHERE IDTieuChuan = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ArrayList<TieuChi> list = new ArrayList<TieuChi>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			TieuChi tc = new TieuChi();
			tc.setID(rs.getInt("ID"));
			tc.setIDTieuChuan(rs.getInt("IDTieuChuan"));
			tc.setMaTieuChi(rs.getString("MaTieuChi"));
			tc.setTenTieuChi(rs.getString("TenTieuChi"));
			list.add(tc);
		}

		return list;
	}

	public void ThemTieuChi(int idTieuChuan, String maTieuChi, String tenTieuChi) throws SQLException {
		if (maTieuChi != "" && tenTieuChi != "") {
			Connection conn = DBConnect.getConnection();

			String sql = "INSERT INTO tieuchi(IDTieuChuan,MaTieuChi,TenTieuChi) VALUES (?,?,?)";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, idTieuChuan);
			ps.setString(2, maTieuChi);
			ps.setString(3, tenTieuChi);
			ps.executeUpdate();
		}
	}

	public TieuChi GetTieuChiByID(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM tieuchi WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		TieuChi tc = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			tc = new TieuChi();
			tc.setID(rs.getInt("ID"));
			tc.setIDTieuChuan(rs.getInt("IDTieuChuan"));
			tc.setMaTieuChi(rs.getString("MaTieuChi"));
			tc.setTenTieuChi(rs.getString("TenTieuChi"));
		}
		return tc;
	}

	public void XoaTieuChi(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "DELETE FROM tieuchi WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		conn.close();
	}

	public void SuaTieuChi(int idTieuChi, int idTieuChuan, String maTieuChi, String tenTieuChi) throws SQLException {
		if (maTieuChi != "" && tenTieuChi != "") {
			Connection conn = DBConnect.getConnection();

			String sql = "UPDATE  tieuchi SET IDTieuChuan=?,MaTieuChi=?,TenTieuChi=? WHERE ID = ?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setInt(1, idTieuChuan);
			ps.setString(2, maTieuChi);
			ps.setString(3, tenTieuChi);
			ps.setInt(4, idTieuChi);
			ps.executeUpdate();
		}
	}

}
