package imooc.bookmanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import imooc.bookmanagement.pojo.User;
import imooc.bookmanagement.service.UserServiceImpl;
import imooc.bookmanagement.util.XmlDataSource;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	UserServiceImpl userService = new UserServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("检测登录");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		int auth = userService.login(user);
		if(auth == 1) {
			System.out.println("登录成功");
			request.getSession().setAttribute("username", username);
			response.sendRedirect("/management?target=book");
		} else {
			// TODO : 认证失败
			response.sendRedirect("/loginFail");
		}
	}
}
