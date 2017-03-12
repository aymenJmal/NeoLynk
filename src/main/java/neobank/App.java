package neobank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import neobank.models.User;

public class App {
	private List<User> users = new ArrayList<User>();

	public void addUser(User user) {
		users.add(user);
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void deleteUser(String id) {
		List<User> foundUsers = users.stream()
			.filter(user -> user.getId().equals(id))
			.collect(Collectors.toList());
		
		if (foundUsers.size() == 1) {
			users.remove(foundUsers.get(0));
		}
	}
}
