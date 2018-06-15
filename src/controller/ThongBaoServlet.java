package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ThongBaoDAO;

@WebServlet("/admin/ThongBaoServlet")
public class ThongBaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ThongBaoDAO thongBaoDAO = new ThongBaoDAO();

	public ThongBaoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		String url = "thongbao.jsp";
		if (request.getParameter("command") != null && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			switch (request.getParameter("command")) {
			case "delete":
				try {
					thongBaoDAO.XoaThongBao(id);
					response.sendRedirect(url);
				} catch (SQLException e) {
					errors.add("Có lỗi xảy ra!");
					e.printStackTrace();
				}
				break;
			case "edit":
				request.getRequestDispatcher("suathongbao.jsp").forward(request, response);
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
			String tieuDe = request.getParameter("TieuDe");
			String noiDung = request.getParameter("NoiDung");
			if (tieuDe == "" || noiDung == "") {
				errors.add("Vui lòng điền đầy đủ thông tin các trường bắt buộc!");
			} else {
				try {
					switch (request.getParameter("Func")) {
					case "add":
						thongBaoDAO.ThemThongBao(tieuDe, noiDung);
						break;
					case "edit":
						int idThongBao = Integer.parseInt(request.getParameter("IDThongBao"));
						thongBaoDAO.SuaThongBao(idThongBao, tieuDe, noiDung);
						break;
					}
				} catch (SQLException e) {
					errors.add("Có lỗi xảy ra!");
					e.printStackTrace();
				}
			}
		} else {
			errors.add("Có lỗi xảy ra!");
		}

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("themthongbao.jsp").forward(request, response);
		} else {
			response.sendRedirect("thongbao.jsp");
		}
	}

}
