package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ThongBao;

public class ThongBaoDAO {
	public ArrayList<ThongBao> GetListThongBao() throws SQLException {
		Connection conn = DBConnect.getConnection();
		ArrayList<ThongBao> list = new ArrayList<ThongBao>();
		String sql = "SELECT * FROM thongbao";
		PreparedStatement ps = conn.prepareCall(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ThongBao tb = new ThongBao();
			tb.setID(rs.getInt("ID"));
			tb.setTieuDe(rs.getString("TieuDeTB"));
			tb.setNoiDung(rs.getString("NoiDungTB"));
			tb.setThoiGianDang(rs.getDate("ThGianDangTai"));
			list.add(tb);
		}
		conn.close();
		return list;
	}

	public void ThemThongBao(String tieuDe, String noiDung) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "INSERT INTO thongbao(TieuDeTB,NoiDungTB,ThGianDangTai) VALUES (?,?,NOW())";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, tieuDe);
		ps.setString(2, noiDung);
		ps.executeUpdate();
		conn.close();
	}

	public void SuaThongBao(int id, String tieuDe, String noiDung) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "UPDATE thongbao SET TieuDeTB=?,NoiDungTB=? WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, tieuDe);
		ps.setString(2, noiDung);
		ps.setInt(3, id);
		ps.executeUpdate();
		conn.close();
	}

	public void XoaThongBao(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "DELETE FROM thongbao WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		conn.close();
	}

	public ThongBao GetThongBaoByID(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		ThongBao tb = null;
		String sql = "SELECT * FROM thongbao WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			tb = new ThongBao();
			tb.setID(rs.getInt("ID"));
			tb.setTieuDe(rs.getString("TieuDeTB"));
			tb.setNoiDung(rs.getString("NoiDungTB"));
			tb.setThoiGianDang(rs.getDate("ThGianDangTai"));
		}
		conn.close();
		return tb;
	}
}
