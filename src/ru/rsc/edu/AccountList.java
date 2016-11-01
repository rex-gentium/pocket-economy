package ru.rsc.edu;

import java.util.*;

public class AccountList {
	List<Account> accounts;
	
	public AccountList() {
		accounts = new ArrayList<Account>();
	}
	
	public AccountList(List<Account> initial) {
		accounts = initial;
	}
	
	public List<Account> get() {
		return accounts;
	}
	
	public int add(int number, String name) {
		if (getByNumber(number) != null) {
			System.out.println("Account already exists: " + number);
			return -1;
		}
		Account acc = new Account(number, name);
		accounts.add(acc);
		return acc.getId();
	}
	
	public Account getById(int id) {
		for (Account account : accounts)
			if (account.getId() == id)
				return account;
		return null;
	}
	
	public Account getByNumber(int number) {
		for (Account account : accounts)
			if (account.getNumber() == number)
				return account;
		return null;
	}
	
	public AccountList getByName(String name) {
		List<Account> result = new ArrayList<Account>();
		for (Account account : accounts)
			if (account.getName().equals(name))
				result.add(account);
		return new AccountList(result);
	}
}
