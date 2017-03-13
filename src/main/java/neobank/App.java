package neobank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import neobank.models.Account;
import neobank.models.User;

public class App {
	private List<User> users = new ArrayList<User>();
	private List<Account> accounts = new ArrayList<Account>();

	// Gestion des users
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
	
	// Gestion des comptes
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public Account getAccount(String accountId) {
		List<Account> foundAccount = accounts.stream()
				.filter(account -> account.getId().equals(accountId))
				.collect(Collectors.toList());
		
		if (foundAccount.size() == 1) {
			return foundAccount.get(0);
		}

		return null;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	
	public User getOwner(String accountId) {
		List<User> foundUsers = users
				.stream()
				.filter(user -> user.getAccounts().contains(accountId))
				.collect(Collectors.toList());
		if (foundUsers.size() == 1) {
			return foundUsers.get(0);
		}
		return null;
	}
	
	public void deleteAccount(String accountId) {
		Account account = getAccount(accountId);

		if (null != account) {
			accounts.remove(account);
			User owner = getOwner(accountId);
			if (null != owner) {
				owner.removeAccount(accountId);
			}
		}
	}

	public void withdrawMoneyAccount(String id, double amount) {
		Account account = getAccount(id);
		if (null != account) {
			account.withdraw(amount);
		}
	}
	
	public void depositMoneyAccount(String accountId, double amount) {
		Account account = getAccount(accountId);
		if (null != account) {
			account.deposit(amount);
		}
	}
	
	public void linkAccountToUser(String userId, Account account) {
		User user = getUser(userId);
		if (null != user) {
			user.addAccount(account.getId());
		}
	}
	
	public void linkAccountsToUser(String userId, List<Account> accounts) {
		User user = getUser(userId);
		if (null != user) {
			accounts.forEach(account -> user.addAccount(account.getId()));
		}
	}
	
	public List<Account> getUserAccounts(String userId) {
		User user = getUser(userId);
		if (null != user) {
			List<String> accountsId = user.getAccounts();
			List<Account> userAccounts = accountsId
				.stream()
				.flatMap(id -> accounts.stream().filter(account -> account.getId() == id))
				.collect(Collectors.toList());
			return userAccounts;
		}
		return Collections.emptyList();
	}

	public double getUserWealth(String userId) {
		User user = getUser(userId);

		if (null != user) {
			List<Account> accounts = this.getUserAccounts(userId);
			return accounts
					.stream()
					.reduce(0.0,
							(sum, account) -> sum + account.getBalance(),
							(sum1, sum2) -> sum1 + sum2);
		}
		
		return 0;
	}
	
}
