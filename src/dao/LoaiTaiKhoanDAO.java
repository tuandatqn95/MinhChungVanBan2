package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.LoaiTaiKhoan;

public class LoaiTaiKhoanDAO {
	public ArrayList<LoaiTaiKhoan> GetListLoaiTaiKhoan() throws SQLException {
		Connection conn = DBConnect.getConnection();
		ArrayList<LoaiTaiKhoan> list = new ArrayList<LoaiTaiKhoan>();
		String sql = "SELECT * FROM loaitaikhoan";
		PreparedStatement ps = conn.prepareCall(sql);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			LoaiTaiKhoan ltk = new LoaiTaiKhoan();
			ltk.setID(rs.getInt("ID"));
			ltk.setTenLoaiTaiKhoan(rs.getString("TenLoaiTK"));
			list.add(ltk);
		}
		conn.close();
		return list;
	}

	public LoaiTaiKhoan GetLoaiTaiKhoanByID(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM loaitaikhoan WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		LoaiTaiKhoan ltk = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			ltk = new LoaiTaiKhoan();
			ltk.setID(rs.getInt("ID"));
			ltk.setTenLoaiTaiKhoan(rs.getString("TenLoaiTK"));
		}
		conn.close();
		return ltk;
	}
}
