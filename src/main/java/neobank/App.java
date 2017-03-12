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
	
	public User getUser(String id) {
		List<User> foundUsers = users.stream()
				.filter(user -> user.getId().equals(id))
				.collect(Collectors.toList());
		
		if (foundUsers.size() == 1) {
			return foundUsers.get(0);
		}
		
		return null;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void deleteUser(String id) {
		User user = getUser(id);
		
		if (null != user) {
			users.remove(user);
		}
	}

	public void updateUserAddress(String id, String newAddress) {
		User user = getUser(id);
		if (null != user) {
			user.setAddress(newAddress);
		}
	}
}
