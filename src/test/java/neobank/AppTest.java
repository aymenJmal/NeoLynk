package neobank;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import neobank.models.User;

public class AppTest {
	App bankApp;
	User firstUser;

	@Before
	public void before() {
		bankApp = new App();
		firstUser = new User("firstname", "lastname", Calendar.getInstance().getTime(), "address", "phone");
		bankApp.addUser(firstUser);
	}
	
	@Test
	public void addUserTest() {
		assertEquals(1, bankApp.getUsers().size());
	}
	
	@Test
	public void deleteUserTest() {
		bankApp.deleteUser(firstUser.getId());
		assertEquals(0, bankApp.getUsers().size());
	}
	
	@Test
	public void getUserExistsTest() {
		User foundUser = bankApp.getUser(firstUser.getId());
		assertEquals(firstUser, foundUser);
	}
	
	@Test
	public void getUserNullTest() {
		User foundUser = bankApp.getUser("-1");
		Assert.assertNull(foundUser);
	}
	
	@Test
	public void updateUserAddressTest() {
		final String newAddress = "newAddress";
		bankApp.updateUserAddress(firstUser.getId(), newAddress);
		User foundUser = bankApp.getUser(firstUser.getId());
		Assert.assertEquals(newAddress, foundUser.getAddress());
	}
}
