package imooc.bookmanagement.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import imooc.bookmanagement.service.BookServiceImpl;
import imooc.bookmanagement.service.CategoryServiceImpl;

/**
 * Servlet implementation class AddBookController
 */
@WebServlet("/addBook")
public class AddBookController extends HttpServlet {
	private BookServiceImpl bookService = new BookServiceImpl();
	private CategoryServiceImpl categoryService = new CategoryServiceImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
