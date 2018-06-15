package controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TaiKhoanDAO;
import util.AesUtil;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();

	public LoginServlet() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		String decryptedPassword = new String(java.util.Base64.getDecoder().decode(request.getParameter("password")));

		System.out.println("decryptedPassword:" + decryptedPassword);

		AesUtil aesUtil = new AesUtil(128, 1000);
		Map map = new HashMap<>();
		if (decryptedPassword != null && decryptedPassword.split("::").length == 3) {
			password = aesUtil.decrypt(decryptedPassword.split("::")[1], decryptedPassword.split("::")[0],
					"01234567890", decryptedPassword.split("::")[2]);
		}

		System.out.println("password:" + password);

		ArrayList<String> errors = new ArrayList<String>();
		try {
			if (email == "" || password == "" || !taiKhoanDAO.login(email, password)) {
				errors.add("Sai tài khoản hoặc mật khẩu!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			errors.add("Có lỗi xảy ra!");
		}
		if (errors.size() == 0) {
			Cookie loginCookie = new Cookie("loginUser", email);
			loginCookie.setMaxAge(-1);
			response.addCookie(loginCookie);
			Cookie PassCookie = new Cookie("passUser", getMD5(password));
			PassCookie.setMaxAge(-1);
			response.addCookie(PassCookie);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	// protected void doPost(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// String email = request.getParameter("email");
	// String password = request.getParameter("password");
	// ArrayList<String> errors = new ArrayList<String>();
	// try {
	// if (email == "" || password == "" || !taiKhoanDAO.login(email, password)) {
	// errors.add("Sai tài khoản hoặc mật khẩu!");
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// errors.add("Có lỗi xảy ra!");
	// }
	// if (errors.size() == 0) {
	// Cookie loginCookie = new Cookie("loginUser", email);
	// loginCookie.setMaxAge(-1);
	// response.addCookie(loginCookie);
	// Cookie PassCookie = new Cookie("passUser", password);
	// PassCookie.setMaxAge(-1);
	// response.addCookie(PassCookie);
	// response.sendRedirect("index.jsp");
	// } else {
	// request.setAttribute("errors", errors);
	// request.getRequestDispatcher("login.jsp").forward(request, response);
	// }
	// }
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
	public static void main(String agrs[]) {
		System.out.println();
	}
}
