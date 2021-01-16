package imooc.bookmanagement.service;

import java.util.List;

import imooc.bookmanagement.pojo.User;
import imooc.bookmanagement.util.XmlDataSource;

public class UserServiceImpl {
	public int login(User user) {
		List<User> userList = XmlDataSource.getRawUserData();
		for(User u : userList) {
			if(user.getUsername().equals(u.getUsername()) && user.getPassword().equals(u.getPassword())) {
				return 1;
			}
		}
		return 0;
	}
}
