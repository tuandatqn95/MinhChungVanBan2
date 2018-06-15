package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.TaiKhoan;

public class TaiKhoanDAO {

	public boolean login(String email, String matKhau) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM taikhoan WHERE Email = ? AND MatKhau = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, email);
		ps.setString(2, getMD5(matKhau));
		
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			conn.close();
			return true;
		}
		return false;
	}
	
	public boolean checkUser(String email, String hash) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM taikhoan WHERE Email = ? AND MatKhau = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, email);
		ps.setString(2, hash);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			conn.close();
			return true;
		}
		return false;
	}

	public ArrayList<TaiKhoan> getListTaiKhoan() throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM taikhoan";
		PreparedStatement ps = conn.prepareCall(sql);

		ResultSet rs = ps.executeQuery();
		ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
		while (rs.next()) {
			TaiKhoan tk = new TaiKhoan();
			tk.setID(rs.getInt("ID"));
			tk.setEmail(rs.getString("Email"));
			tk.setMatKhau(rs.getString("MatKhau"));
			tk.setHoTen(rs.getString("HoTen"));
			tk.setIDLoaiTK(rs.getInt("IDLoaiTK"));
			tk.setNu(rs.getInt("Nu") != 0);

			tk.setNgaySinh(rs.getDate("NgaySinh"));
			tk.setDiaChi(rs.getString("DiaChi"));
			tk.setNoiCongTac(rs.getString("NoiCongTac"));
			tk.setChucVu(rs.getString("ChucVu"));
			tk.setSoDienThoai(rs.getString("SoDT"));
			tk.setAnhDaiDien(rs.getString("AnhDaiDien"));
			list.add(tk);
		}
		return list;
	}

	public ArrayList<TaiKhoan> getListTaiKhoanAreContact() throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM taikhoan WHERE IDLoaiTK = 2";
		PreparedStatement ps = conn.prepareCall(sql);

		ResultSet rs = ps.executeQuery();
		ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
		while (rs.next()) {
			TaiKhoan tk = new TaiKhoan();
			tk.setID(rs.getInt("ID"));
			tk.setEmail(rs.getString("Email"));
			tk.setMatKhau(rs.getString("MatKhau"));
			tk.setHoTen(rs.getString("HoTen"));
			tk.setIDLoaiTK(rs.getInt("IDLoaiTK"));
			tk.setNu(rs.getInt("Nu") != 0);

			tk.setNgaySinh(rs.getDate("NgaySinh"));
			tk.setDiaChi(rs.getString("DiaChi"));
			tk.setNoiCongTac(rs.getString("NoiCongTac"));
			tk.setChucVu(rs.getString("ChucVu"));
			tk.setSoDienThoai(rs.getString("SoDT"));
			tk.setAnhDaiDien(rs.getString("AnhDaiDien"));
			list.add(tk);
		}
		return list;
	}
	public void ThemTaiKhoan(String email, String matKhau, String hoTen, int idLoaiTk, Boolean nu, String ngaySinh,
			String diaChi, String noiCongTac, String chucVu, String soDT, String anhDaiDien) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "INSERT INTO taikhoan(Email,MatKhau,HoTen,IDLoaiTK,Nu,NgaySinh,DiaChi,NoiCongTac,ChucVu,SoDT,AnhDaiDien) VALUES (?,?,?,?,?,STR_TO_DATE(?, '%d-%m-%Y'),?,?,?,?,?)";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, email);
		ps.setString(2, matKhau);
		ps.setString(3, hoTen);
		ps.setInt(4, idLoaiTk);
		ps.setInt(5, nu ? 1 : 0);
		ps.setString(6, ngaySinh);
		ps.setString(7, diaChi);
		ps.setString(8, noiCongTac);
		ps.setString(9, chucVu);
		ps.setString(10, soDT);
		ps.setString(11, anhDaiDien);

		ps.executeUpdate();
		conn.close();
	}

	public void SuaTaiKhoan(int id, String email, String hoTen, int idLoaiTk, Boolean nu, String ngaySinh,
			String diaChi, String noiCongTac, String chucVu, String soDT, String anhDaiDien) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "UPDATE taikhoan SET Email=?,HoTen=?,IDLoaiTK=?,Nu=?,NgaySinh=STR_TO_DATE(?, '%d-%m-%Y'),DiaChi=?,NoiCongTac=?,ChucVu=?,SoDT=? WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, email);
		ps.setString(2, hoTen);
		ps.setInt(3, idLoaiTk);
		ps.setInt(4, nu ? 1 : 0);

		ps.setString(5, ngaySinh);
		ps.setString(6, diaChi);
		ps.setString(7, noiCongTac);
		ps.setString(8, chucVu);
		ps.setString(9, soDT);
		ps.setInt(10, id);
		ps.executeUpdate();
		if (!anhDaiDien.isEmpty()) {
			sql = "UPDATE taikhoan SET AnhDaiDien=? WHERE ID = ?";
			ps = conn.prepareCall(sql);
			ps.setString(1, anhDaiDien);
			ps.setInt(2, id);
			ps.executeUpdate();
		}
		conn.close();
	}

	public TaiKhoan GetTaiKhoanByID(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM taikhoan WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		TaiKhoan tk = null;
		if (rs.next()) {
			tk = new TaiKhoan();
			tk.setID(rs.getInt("ID"));
			tk.setEmail(rs.getString("Email"));
			tk.setMatKhau(rs.getString("MatKhau"));
			tk.setHoTen(rs.getString("HoTen"));
			tk.setIDLoaiTK(rs.getInt("IDLoaiTK"));
			tk.setNu(rs.getInt("Nu") != 0);
			tk.setNgaySinh(rs.getDate("NgaySinh"));
			tk.setDiaChi(rs.getString("DiaChi"));
			tk.setNoiCongTac(rs.getString("NoiCongTac"));
			tk.setChucVu(rs.getString("ChucVu"));
			tk.setSoDienThoai(rs.getString("SoDT"));
			tk.setAnhDaiDien(rs.getString("AnhDaiDien"));
		}
		return tk;
	}

	public TaiKhoan GetTaiKhoanByEmail(String email) throws SQLException {
		Connection conn = DBConnect.getConnection();

		String sql = "SELECT * FROM taikhoan WHERE Email = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		TaiKhoan tk = null;
		if (rs.next()) {
			tk = new TaiKhoan();
			tk.setID(rs.getInt("ID"));
			tk.setEmail(rs.getString("Email"));
			tk.setMatKhau(rs.getString("MatKhau"));
			tk.setHoTen(rs.getString("HoTen"));
			tk.setIDLoaiTK(rs.getInt("IDLoaiTK"));
			tk.setNu(rs.getInt("Nu") != 0);
			tk.setNgaySinh(rs.getDate("NgaySinh"));
			tk.setDiaChi(rs.getString("DiaChi"));
			tk.setNoiCongTac(rs.getString("NoiCongTac"));
			tk.setChucVu(rs.getString("ChucVu"));
			tk.setSoDienThoai(rs.getString("SoDT"));
			tk.setAnhDaiDien(rs.getString("AnhDaiDien"));
		}
		return tk;
	}

	public void XoaTaiKhoan(int id) throws SQLException {
		Connection conn = DBConnect.getConnection();
		String sql = "DELETE FROM taikhoan WHERE ID = ?";
		PreparedStatement ps = conn.prepareCall(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		conn.close();
	}

	public static String getMD5(String str) {
		byte[] defaultBytes = str.getBytes();
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
			algorithm.update(defaultBytes);
			byte messageDigest[] = algorithm.digest();
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < messageDigest.length; i++) {
				String hex = Integer.toHexString(0xFF & messageDigest[i]);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			str = hexString + "";
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static void main(String agrs[]) throws SQLException {
		TaiKhoanDAO dao = new TaiKhoanDAO();
		System.out.println(dao.login("nguyena", "123456"));
	}
}
