package imooc.bookmanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import imooc.bookmanagement.pojo.Book;
import imooc.bookmanagement.pojo.Category;
import imooc.bookmanagement.service.BookServiceImpl;
import imooc.bookmanagement.service.CategoryServiceImpl;
import imooc.bookmanagement.util.XmlDataSource;

/**
 * Servlet implementation class ManagementController
 */
@WebServlet("/management")
public class ManagementController extends HttpServlet {
	private BookServiceImpl bookService = new BookServiceImpl();
	private CategoryServiceImpl categoryService = new CategoryServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = request.getParameter("target");
		target = target == null ? "book" : target;
		if(target.equals("book")) {
			this.bookManagement(request, response);
		} else if(target.equals("category")) {
			this.categoryManagement(request, response);
		} else if(target.equals("category_json")) {
			// 这个用来返回category list的json格式字符串，在添加图书页面会用得到
			this.categoryJson(request, response);
		}
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void bookManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> bookList = bookService.getBookList();
		request.setAttribute("bookList", bookList);
		request.getRequestDispatcher("/WEB-INF/jsp/bookList.jsp").forward(request, response);
	}
	
	private void categoryManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Category> categoryList = categoryService.getCategoryList();
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/WEB-INF/jsp/categoryList.jsp").forward(request, response);
	}
	
	private void categoryJson(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 这个方法返回category list的json格式字符串，在添加图书页面使用ajax访问
		List<Category> categories = categoryService.getCategoryList();
		String text = JSON.toJSONString(categories);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(text);
	}
}
