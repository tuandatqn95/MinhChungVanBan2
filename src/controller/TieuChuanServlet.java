package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TieuChuanDAO;

@WebServlet("/admin/TieuChuanServlet")
public class TieuChuanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TieuChuanDAO tieuChuanDAO = new TieuChuanDAO();

	public TieuChuanServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		String url = "tieuchuan.jsp";
		if (request.getParameter("command") != null && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			switch (request.getParameter("command")) {
			case "delete":
				try {
					tieuChuanDAO.XoaTieuChuan(id);
					response.sendRedirect(url);
				} catch (SQLException e) {
					errors.add("Có lỗi xảy ra!");
					e.printStackTrace();
				}
				break;
			case "edit":
				request.getRequestDispatcher("suatieuchuan.jsp").forward(request, response);
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
			String maTieuChuan = request.getParameter("MaTC");
			String tenTieuChuan = request.getParameter("TenTC");
			int idBoTieuChuan = Integer.parseInt(request.getParameter("IDBoTieuChuan"));
			if (maTieuChuan == "" || tenTieuChuan == "") {
				errors.add("Vui lòng điền đủ thông tin!");
			} else {
				try {
					switch (request.getParameter("Func")) {
					case "add":
						tieuChuanDAO.ThemTieuChuan(idBoTieuChuan, maTieuChuan, tenTieuChuan);
						break;
					case "edit":
						int idTieuChuan = Integer.parseInt(request.getParameter("IDTieuChuan"));
						tieuChuanDAO.SuaTieuChuan(idTieuChuan, idBoTieuChuan, maTieuChuan, tenTieuChuan);
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

		if (errors.size() == 0) {
			response.sendRedirect("tieuchuan.jsp");
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("tieuchuan.jsp").forward(request, response);
		}

	}

}
