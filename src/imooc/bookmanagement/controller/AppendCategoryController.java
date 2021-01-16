package imooc.bookmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import imooc.bookmanagement.service.CategoryServiceImpl;

/**
 * Servlet implementation class AppendCategoryController
 */
@WebServlet("/addCategory")
public class AppendCategoryController extends HttpServlet {
	private CategoryServiceImpl categoryService = new CategoryServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppendCategoryController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String categoryId = request.getParameter("categoryId");
		String categoryName = request.getParameter("categoryName");
		categoryService.addCategory(categoryId, categoryName);
		response.sendRedirect("/management?target=category");
	}

}
