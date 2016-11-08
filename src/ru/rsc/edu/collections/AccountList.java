package ru.rsc.edu.collections;

import java.util.*;

import ru.rsc.edu.entities.Account;
import ru.rsc.edu.exceptions.AlreadyExistsException;

public class AccountList {
	List<Account> accounts;
	
	public AccountList() {
		this.accounts = new ArrayList<Account>();
	}
	
	public AccountList(List<Account> initial) {
		this.accounts = initial;
	}
	
	public void add(Account acc) throws AlreadyExistsException {
		if (!accounts.contains(acc))
			this.accounts.add(acc);
		else throw new AlreadyExistsException("Account " + acc.getNumber() + " already exists");
	}
	
	// getters
	public List<Account> get() {
		return this.accounts;
	}
	
	public Account getByNumber(int number) {
		for (Account account : this.accounts)
			if (account.getNumber() == number)
				return account;
		return null;
	}
	
	public AccountList selectByName(String name) {
		List<Account> result = new ArrayList<Account>();
		for (Account account : this.accounts)
			if (account.getName().equals(name))
				result.add(account);
		return new AccountList(result);
	}
}
