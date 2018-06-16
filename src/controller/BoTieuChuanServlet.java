package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

import dao.BoTieuChuanDAO;

@WebServlet("/admin/BoTieuChuanServlet")
public class BoTieuChuanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BoTieuChuanDAO boTieuChuanDAO = new BoTieuChuanDAO();

	public BoTieuChuanServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		String url = "botieuchuan.jsp";
		if (request.getParameter("command") != null && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			switch (request.getParameter("command")) {
			case "delete":
				try {
					boTieuChuanDAO.XoaBoTieuChuan(id);
					response.sendRedirect(url);
				} catch (SQLException e) {
					errors.add("Có lỗi xảy ra!");
					e.printStackTrace();
				}
				break;
			case "edit":
				request.getRequestDispatcher("suabotieuchuan.jsp").forward(request, response);
				break;
			}
		} else {
			errors.add("Có lỗi xảy ra!");
		}

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();

		if (request.getParameter("Func") != null) {
			String maBoTieuChuan = request.getParameter("MaBTC");
			String tenBoTieuChuan = request.getParameter("TenBTC");
			String moTa = request.getParameter("MoTa");
			
			String regex = "<>\"[\\s\\w-,]*";
			String stringToValidate = maBoTieuChuan + tenBoTieuChuan + moTa;
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(stringToValidate);
			if (!matcher.matches()) {
				errors.add("Có lỗi xảy ra!");
			}
			
			
			if (errors.size() == 0 & maBoTieuChuan != "" && tenBoTieuChuan != "") {
				try {
					switch (request.getParameter("Func")) {
					case "add":
						boTieuChuanDAO.ThemBoTieuChuan(maBoTieuChuan, tenBoTieuChuan, moTa);
						break;
					case "edit":
						int idBoTieuChuan = Integer.parseInt(request.getParameter("IDBoTieuChuan"));
						boTieuChuanDAO.SuaBoTieuChuan(idBoTieuChuan, maBoTieuChuan, tenBoTieuChuan, moTa);
						break;
					}
				} catch (SQLException e) {
					errors.add("Có lỗi xảy ra!");
					e.printStackTrace();
				}

			} else {
				errors.add("Vui lòng điền đầy đủ thông tin các trường bắt buộc!");
			}
		} else {
			errors.add("Có lỗi xảy ra!");
		}

		if (errors.size() == 0) {
			response.sendRedirect("botieuchuan.jsp");
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("botieuchuan.jsp").forward(request, response);
		}
	}
	
	

}
