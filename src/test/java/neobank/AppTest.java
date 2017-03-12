package neobank;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import neobank.models.User;

public class AppTest {

	@Test
	public void addUserTest() {
		App bankApp = new App();
		User newUser = new User("firstname", "lastname", Calendar.getInstance().getTime(), "address", "phone");
		bankApp.addUser(newUser);
		assertEquals(1, bankApp.getUsers().size());
	}
}
