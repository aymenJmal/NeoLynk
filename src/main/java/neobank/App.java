package neobank;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import neobank.models.Account;
import neobank.models.User;

public class App {
	private List<User> users = new ArrayList<User>();
	private List<Account> accounts = new ArrayList<Account>();

	//Gestion des users
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
	
	public Account getAccount(String id) {
		List<Account> foundAccount = accounts.stream()
				.filter(account -> account.getId().equals(id))
				.collect(Collectors.toList());
		
		if (foundAccount.size() == 1) {
			return foundAccount.get(0);
		}
		
		return null;
	}
	
	public List<Account> getAccount() {
		return accounts;
	}
	
	public void deleteAccount(String id) {
		Account account = getAccount(id);
		
		if (null != account) {
			accounts.remove(account);
		}
	}

	public void withdrawMoneyAccount(String id, double withdraw) {
		Account account = getAccount(id);
		if (null != account) {
			double balance = account.getBalance();
			account.setBalance(balance - withdraw);
		}
	}
	
	public void depositMoneyAccount(String id, double deposit) {
		Account account = getAccount(id);
		if (null != account) {
			double balance = account.getBalance();
			account.setBalance(balance + deposit);
		}
	}
	
	public void linkAccountToUser(String id, Account account) {
		User user = getUser(id);
		if (null != user) {
			user.getAccounts().add(account);
		}
	}
	
	public void linkAccountsToUser(String id, List<Account> accounts) {
		User user = getUser(id);
		if (null != user) {
			accounts.forEach(account->user.getAccounts().add(account));
		}
	}
	
	public List<Account> getUsersAccounts(String id) {
		User user = getUser(id);
		if (null != user) {
			return user.getAccounts();
		}
		return null;
	}
	
	public double getBalanceForAccountsOfUser(String id) {
		User user = getUser(id);
		double total = 0 ;
		
		if (null != user) {
			List<Account> accounts =  user.getAccounts();
			accounts.forEach(item->total = total + item.getBalance()); 
		}
		return total;
	}
	
}
