package neobank;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.List;

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
		assertEquals(1, bankApp.getAccount().size());
	}

	@Test
	public void deleteAccountTest() {
		bankApp.deleteAccount(firstAccount.getId());
		assertEquals(0, bankApp.getAccount().size());
	}
	
	@Test
	public void getAccountExistsTest() {
		Account foundAccount = bankApp.getAccount(firstAccount.getId());
		assertEquals(firstUser, foundAccount);
	}
	
	@Test
	public void getAccountNullTest() {
		Account foundAccount = bankApp.getAccount("-1");
		Assert.assertNull(foundAccount);
	}

	@Test
	public void withdrawMoneyAccountTest(String id, double withdraw) {
		firstAccount.setBalance(withdraw);
		bankApp.withdrawMoneyAccount(firstAccount.getId(), withdraw);
		assertEquals(0, firstAccount.getBalance(), EPSILON);
	}

	@Test
	public void depositMoneyAccountTest(String id, double deposit) {
		firstAccount.setBalance(deposit);
		bankApp.depositMoneyAccount(firstAccount.getId(), deposit);
		assertEquals(0, firstAccount.getBalance(), EPSILON);
	}
	
	@Test
	public void linkAccountToUserTest(String id, Account account) {
		User user = bankApp.getUser(id);
		user.getAccounts().add(account);
		assertEquals(1, user.getAccounts().size());
	}
	
	@Test
	public void linkAccountsToUserTest(String id, List<Account> accounts) {
		User user = bankApp.getUser(id);
		accounts.forEach(account->user.getAccounts().add(account));
		assertEquals(accounts.size(), user.getAccounts().size());
	}
	
	@Test
	public List<Account> getUsersAccountsTest(String id) {
		return null;
	}
	
	@Test
	public double getBalanceForAccountsOfUserTest(String id) {
		return 0;
	}	
}
