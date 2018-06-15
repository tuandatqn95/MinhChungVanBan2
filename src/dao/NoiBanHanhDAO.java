package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.NoiBanHanh;

public class NoiBanHanhDAO {
	public ArrayList<NoiBanHanh> getListNoiBanHanh() throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM noibanhanh";
		PreparedStatement ps = conn.prepareCall(sql);

		ArrayList<NoiBanHanh> list = new ArrayList<NoiBanHanh>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			NoiBanHanh nbh = new NoiBanHanh();
			nbh.setID(rs.getInt("ID"));
			nbh.setMaNoiBanHanh(rs.getString("MaNBH"));
			nbh.setTenNoiBanHanh(rs.getString("TenNBH"));
			list.add(nbh);
		}
		conn.close();
		return list;
	}

	public void ThemNoiBanHanh(String maNoiBanHanh, String tenNoiBanHanh) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "INSERT INTO noibanhanh(MaNBH,TenNBH) VALUES (?,?)";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, maNoiBanHanh);
		ps.setString(2, tenNoiBanHanh);
		ps.executeUpdate();
		conn.close();
	}

	public void SuaNoiBanHanh(int id, String maNoiBanHanh, String tenNoiBanHanh) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "UPDATE noibanhanh SET MaNBH=?,TenNBH=? WHERE ID=?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, maNoiBanHanh);
		ps.setString(2, tenNoiBanHanh);
		ps.setInt(3, id);
		ps.executeUpdate();
		conn.close();
	}

	public void XoaNoiBanHanh(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "DELETE FROM noibanhanh WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		conn.close();
	}

	public NoiBanHanh GetNoiBanHanhByID(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM noibanhanh WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		NoiBanHanh nbh = null;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			nbh = new NoiBanHanh();
			nbh.setID(rs.getInt("ID"));
			nbh.setMaNoiBanHanh(rs.getString("MaNBH"));
			nbh.setTenNoiBanHanh(rs.getString("TenNBH"));
		}
		conn.close();
		return nbh;
	}

}
