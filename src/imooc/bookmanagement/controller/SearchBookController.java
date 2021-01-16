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
import imooc.bookmanagement.service.*;

/**
 * Servlet implementation class SearchBookController
 */
@WebServlet("/searchBook")
public class SearchBookController extends HttpServlet {
	private BookServiceImpl bookService = new BookServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBookController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Book> result = null;
		String categoryName = request.getParameter("searchContent");
		if(categoryName.equals("")) {
			result = bookService.getBookList();
		} else {
			result = bookService.getBooksByCategoryName(categoryName);
		}
		String json = JSON.toJSONString(result);
		//System.out.println(json);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
