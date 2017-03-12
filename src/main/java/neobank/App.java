package neobank;

import java.util.ArrayList;
import java.util.List;

import neobank.models.User;

public class App {
	private List<User> users = new ArrayList<User>();

	public void addUser(User user) {
		users.add(user);
	}
	
	public List<User> getUsers() {
		return users;
	}
}
