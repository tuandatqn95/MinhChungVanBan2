package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TapTinDAO;

@WebServlet("/admin/TapTinServlet")
public class TapTinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	TapTinDAO tapTinDAO = new TapTinDAO();

	public TapTinServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArrayList<String> errors = new ArrayList<String>();
		String url = "MinhChungServlet?command=edit&id=";
		if (request.getParameter("command") != null && request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				url += tapTinDAO.GetTapTinByID(id).getIDMinhChung();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			switch (request.getParameter("command")) {
			case "delete":
				try {
					tapTinDAO.XoaTapTin(id);

				} catch (SQLException e) {
					errors.add("Có lỗi xảy ra!");
					e.printStackTrace();
				}
				break;
			}
		} else {
			errors.add("Có lỗi xảy ra!");
		}

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher(url).forward(request, response);
		} else {
			response.sendRedirect(url);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
