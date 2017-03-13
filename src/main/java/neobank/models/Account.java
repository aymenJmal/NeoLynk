package neobank.models;

import java.util.Date;
import java.util.UUID;

public class Account {
	private double balance;
	private String id;
	private Date creationDate;
	
	public Account(double balance, Date creationDate) {
		this.id = UUID.randomUUID().toString();
		this.balance = balance;
		this.creationDate = creationDate;
	}

	public double getBalance() {
		return balance;
	}
	private void setBalance(double balance) {
		this.balance = balance;
	}
	public String getId() {
		return id;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void withdraw(double amount) {
		this.setBalance(balance - amount);
	}
	public void deposit(double amount) {
		this.setBalance(balance + amount);
	}
}
