package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BoTieuChuan;

public class BoTieuChuanDAO {
	public ArrayList<BoTieuChuan> getListBoTieuChuan() throws SQLException {
		ArrayList<BoTieuChuan> list = new ArrayList<BoTieuChuan>();
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM botieuchuan";
		PreparedStatement ps = conn.prepareCall(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			BoTieuChuan btc = new BoTieuChuan();
			btc.setID(rs.getInt("ID"));
			btc.setMaBoTieuChuan(rs.getString("MaBoTC"));
			btc.setTenBoTieuChuan(rs.getString("TenBoTC"));
			btc.setMoTa(rs.getString("MoTa"));
			list.add(btc);
		}
		conn.close();
		return list;
	}

	public BoTieuChuan GetBoTieuChuanByID(int id) throws SQLException {
		BoTieuChuan btc = null;
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM botieuchuan WHERE ID=?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			btc = new BoTieuChuan();
			btc.setID(rs.getInt("ID"));
			btc.setMaBoTieuChuan(rs.getString("MaBoTC"));
			btc.setTenBoTieuChuan(rs.getString("TenBoTC"));
			btc.setMoTa(rs.getString("MoTa"));
		}
		conn.close();
		return btc;
	}

	public void ThemBoTieuChuan(String maBoTieuChuan, String tenBoTieuChuan, String moTa) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "INSERT INTO botieuchuan(MaBoTC,TenBoTC,MoTa) VALUES (?,?,?)";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, maBoTieuChuan);
		ps.setString(2, tenBoTieuChuan);
		ps.setString(3, moTa);
		ps.executeUpdate();
		conn.close();
	}

	public void SuaBoTieuChuan(int id, String maBoTieuChuan, String tenBoTieuChuan, String moTa) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "UPDATE botieuchuan SET MaBoTC=?, TenBoTC=?, MoTa=?  WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, maBoTieuChuan);
		ps.setString(2, tenBoTieuChuan);
		ps.setString(3, moTa);
		ps.setInt(4, id);
		ps.executeUpdate();
		conn.close();
	}

	public void XoaBoTieuChuan(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "DELETE FROM botieuchuan WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		conn.close();
	}
}
