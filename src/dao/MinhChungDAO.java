package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.MinhChung;

public class MinhChungDAO {

	public ArrayList<MinhChung> getListMinhChung() throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM minhchung";
		PreparedStatement ps = conn.prepareCall(sql);

		ArrayList<MinhChung> list = new ArrayList<MinhChung>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			MinhChung mc = new MinhChung();
			mc.setID(rs.getInt("ID"));
			mc.setMaMinhChung(rs.getString("MaMC"));
			mc.setTenMinhChung(rs.getString("TenMC"));
			mc.setIDTieuChi(rs.getInt("IDTieuChi"));
			mc.setMoTa(rs.getString("MoTa"));
			mc.setSoHieu(rs.getString("SoHieu"));
			mc.setNgayBanhanh(rs.getDate("NgayBanHanh"));
			mc.setNoiBanHanh(rs.getInt("IDNBH"));
			list.add(mc);
		}

		return list;
	}

	public MinhChung getMinhChungByID(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM minhchung WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		MinhChung mc = null;
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			mc = new MinhChung();
			mc.setID(rs.getInt("ID"));
			mc.setMaMinhChung(rs.getString("MaMC"));
			mc.setTenMinhChung(rs.getString("TenMC"));
			mc.setIDTieuChi(rs.getInt("IDTieuChi"));
			mc.setMoTa(rs.getString("MoTa"));
			mc.setSoHieu(rs.getString("SoHieu"));
			mc.setNgayBanhanh(rs.getDate("NgayBanHanh"));
			mc.setNoiBanHanh(rs.getInt("IDNBH"));
		}

		return mc;
	}
	
	public ArrayList<MinhChung> getListMinhChungByName(String name) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM minhchungvanban.minhchung WHERE TenMC LIKE concat('%',?,'%')";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, name);

		ArrayList<MinhChung> list = new ArrayList<MinhChung>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			MinhChung mc = new MinhChung();
			mc.setID(rs.getInt("ID"));
			mc.setMaMinhChung(rs.getString("MaMC"));
			mc.setTenMinhChung(rs.getString("TenMC"));
			mc.setIDTieuChi(rs.getInt("IDTieuChi"));
			mc.setMoTa(rs.getString("MoTa"));
			mc.setSoHieu(rs.getString("SoHieu"));
			mc.setNgayBanhanh(rs.getDate("NgayBanHanh"));
			mc.setNoiBanHanh(rs.getInt("IDNBH"));
			list.add(mc);
		}

		return list;
	}
	
	public ArrayList<MinhChung> getListMinhChungByMaTieuChi(int maTieuChi) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM minhchung WHERE IDTieuChi = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, maTieuChi);

		ArrayList<MinhChung> list = new ArrayList<MinhChung>();

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			MinhChung mc = new MinhChung();
			mc.setID(rs.getInt("ID"));
			mc.setMaMinhChung(rs.getString("MaMC"));
			mc.setTenMinhChung(rs.getString("TenMC"));
			mc.setIDTieuChi(rs.getInt("IDTieuChi"));
			mc.setMoTa(rs.getString("MoTa"));
			mc.setSoHieu(rs.getString("SoHieu"));
			mc.setNgayBanhanh(rs.getDate("NgayBanHanh"));
			mc.setNoiBanHanh(rs.getInt("IDNBH"));
			list.add(mc);
		}

		return list;
	}

	public void ThemMinhChung(int idTieuChi, String maMinhChung, String tenMinhChung, String moTa, String soHieu,
			String ngayBanHanh, int idNoiBanHanh) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "INSERT INTO minhchung(IDTieuChi,MaMC,TenMC,MoTa,SoHieu,NgayBanHanh,IDNBH) VALUES (?,?,?,?,?,STR_TO_DATE(?, '%d-%m-%Y'),?)";
		PreparedStatement ps;
		ps = conn.prepareCall(sql);
		ps.setInt(1, idTieuChi);
		ps.setString(2, maMinhChung);
		ps.setString(3, tenMinhChung);
		ps.setString(4, moTa);
		ps.setString(5, soHieu);
		ps.setString(6, ngayBanHanh);
		ps.setInt(7, idNoiBanHanh);
		ps.executeUpdate();
		conn.close();
	}

	public void SuaMinhChung(int id, int idTieuChi, String maMinhChung, String tenMinhChung, String moTa, String soHieu,
			String ngayBanHanh, int idNoiBanHanh) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "UPDATE minhchung SET IDTieuChi=?,MaMC=?,TenMC=?,MoTa=?,SoHieu=?,NgayBanHanh=STR_TO_DATE(?, '%d-%m-%Y'),IDNBH=? WHERE ID = ?";
		PreparedStatement ps;
		ps = conn.prepareCall(sql);
		ps.setInt(1, idTieuChi);
		ps.setString(2, maMinhChung);
		ps.setString(3, tenMinhChung);
		ps.setString(4, moTa);
		ps.setString(5, soHieu);
		ps.setString(6, ngayBanHanh);
		ps.setInt(7, idNoiBanHanh);
		ps.setInt(8, id);
		ps.executeUpdate();
		conn.close();
	}
	
	public void XoaMinhChung(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "DELETE FROM taptin WHERE IDMinhChung = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		
		String sql1 = "DELETE FROM minhchung WHERE ID = ?";
		ps = conn.prepareCall(sql1);
		ps.setInt(1, id);
		ps.executeUpdate();
		conn.close();
	}

	public int GetIDMinhChungByMaMinhChung(String maMinhChung) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "SELECT * FROM minhchung WHERE MaMC = ?";
		PreparedStatement ps = conn.prepareCall(sql);

		ps.setString(1, maMinhChung);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			return rs.getInt("ID");
		}
		return -1;
	}

}
