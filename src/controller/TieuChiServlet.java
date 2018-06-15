package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TieuChiDAO;

@WebServlet("/admin/TieuChiServlet")
public class TieuChiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TieuChiDAO tieuChiDAO = new TieuChiDAO();

	public TieuChiServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		String url = "tieuchi.jsp";
		if (request.getParameter("command") != null && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			switch (request.getParameter("command")) {
			case "delete":
				try {
					tieuChiDAO.XoaTieuChi(id);
					response.sendRedirect(url);
				} catch (SQLException e) {
					errors.add("Có lỗi xảy ra!");
					e.printStackTrace();
				}
				break;
			case "edit":
				request.getRequestDispatcher("suatieuchi.jsp").forward(request, response);
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
		String url = "tieuchi.jsp";
		if (request.getParameter("Func") != null) {
			String idTieuChuan = request.getParameter("MaTieuChuan");
			String maTieuChi = request.getParameter("MaTieuChi");
			String tenTieuChi = request.getParameter("TenTieuChi");
			if (idTieuChuan == "" || maTieuChi == "" || tenTieuChi == "") {
				errors.add("Vui lòng điền đầy đủ các trường!");
			} else {
				try {
					switch (request.getParameter("Func")) {
					case "add":
						tieuChiDAO.ThemTieuChi(Integer.parseInt(idTieuChuan), maTieuChi, tenTieuChi);
						break;
					case "edit":
						int idTieuChi = Integer.parseInt(request.getParameter("IDTieuChi"));
						tieuChiDAO.SuaTieuChi(idTieuChi, Integer.parseInt(idTieuChuan), maTieuChi, tenTieuChi);
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
			response.sendRedirect(url);
		} else {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(url).forward(request, response);
		}
	}
}