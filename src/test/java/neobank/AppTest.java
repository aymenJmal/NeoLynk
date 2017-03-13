package neobank;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import neobank.models.Account;
import neobank.models.User;

public class AppTest {
	App bankApp;
	User firstUser;
	Account firstAccount;
	
	final double EPSILON = 0.0001;

	@Before
	public void before() {
		bankApp = new App();
		
		firstUser = new User("firstname", "lastname", Calendar.getInstance().getTime(), "address", "phone");
		bankApp.addUser(firstUser);
		
		firstAccount = new Account(0, Calendar.getInstance().getTime());
		bankApp.addAccount(firstAccount);
		
		bankApp.linkAccountToUser(firstUser.getId(), firstAccount);
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
	
	@Test
	public void addAccountTest() {
		assertEquals(1, bankApp.getAccounts().size());
	}
	
	@Test
	public void getOwnerTest() {
		User owner = bankApp.getOwner(firstAccount.getId());
		assertEquals(firstUser, owner);
	}

	@Test
	public void deleteAccountTest() {
		assertEquals(1, firstUser.getAccounts().size());
		bankApp.deleteAccount(firstAccount.getId());
		assertEquals(0, firstUser.getAccounts().size());
		assertEquals(0, bankApp.getAccounts().size());
	}

	@Test
	public void getAccountExistsTest() {
		Account foundAccount = bankApp.getAccount(firstAccount.getId());
		assertEquals(firstAccount, foundAccount);
	}

	@Test
	public void getAccountNullTest() {
		Account foundAccount = bankApp.getAccount("-1");
		Assert.assertNull(foundAccount);
	}

	@Test
	public void withdrawMoneyAccountTest() {
		final double amount = 1000;
		bankApp.withdrawMoneyAccount(firstAccount.getId(), amount);
		assertEquals(-1000, firstAccount.getBalance(), EPSILON);
	}

	@Test
	public void depositMoneyAccountTest() {
		final double amount = 1000;
		bankApp.depositMoneyAccount(firstAccount.getId(), amount);
		assertEquals(1000, firstAccount.getBalance(), EPSILON);
	}

	@Test
	public void linkAccountToUserTest() {
		assertEquals(1, firstUser.getAccounts().size());
		assertEquals(firstAccount.getId(), firstUser.getAccounts().get(0));
	}

	@Test
	public void getUserWealthTest() {
		double balance1 = 1000, balance2 = -200;
		bankApp.addAccount(new Account(balance1, Calendar.getInstance().getTime()));
		bankApp.addAccount(new Account(balance2, Calendar.getInstance().getTime()));
		bankApp.linkAccountsToUser(firstUser.getId(), bankApp.getAccounts());
		
		assertEquals(balance1 + balance2, bankApp.getUserWealth(firstUser.getId()), EPSILON);
	}	
}
