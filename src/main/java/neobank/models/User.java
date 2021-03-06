package neobank.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class User {
	private String id;
	private String firstname;
	private String lastname;
	private Date birthday;
	private String address;
	private String phone;
	
	private List<String> accountsId = new ArrayList<String>();
	
	public User(String firstname, String lastname, Date birthday, String address, String phone) {
		this.id = UUID.randomUUID().toString();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<String> getAccounts() {
		return accountsId;
	}
	public void setAccounts(List<String> accounts) {
		this.accountsId = accounts;
	}
	public void addAccount(String accountId) {
		this.accountsId.add(accountId);
	}
	public void removeAccount(String accountId) {
		this.accountsId.remove(accountId);
	}
}
