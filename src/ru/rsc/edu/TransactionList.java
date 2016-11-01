package ru.rsc.edu;

import java.time.*;
import java.util.*;

public class TransactionList {
	List<Transaction> transactions;
	
	public TransactionList() {
		transactions = new ArrayList<Transaction>();
	}
	
	public TransactionList(List<Transaction> initial) {
		transactions = initial;
	}
	
	public List<Transaction> get() {
		return transactions;
	}
	
	public int add(int accountId, int categoryId, LocalDateTime dateTime, long amount) {
		Transaction trans = new Transaction(accountId, categoryId, dateTime, amount);
		transactions.add(trans);
		return trans.getId();
	}
	
	public Transaction getById(int id) {
		for (Transaction transaction : transactions)
			if (transaction.getId() == id)
				return transaction;
		return null;
	}
	
	public TransactionList getByAccount(int accountId) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getAccountId() == accountId)
				result.add(transaction);
		return new TransactionList(result);
	}
	
	public TransactionList getByCategory(int categoryId) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getCategoryId() == categoryId)
				result.add(transaction);
		return new TransactionList(result);
	}
	
	public TransactionList getByDate(LocalDate date) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getDateTime().toLocalDate().equals(date))
				result.add(transaction);
		return new TransactionList(result);
	}
	
	public TransactionList getBeforeDate(LocalDate date) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getDateTime().toLocalDate().isBefore(date))
				result.add(transaction);
		return new TransactionList(result);
	}
	
	public TransactionList getAfterDate(LocalDate date) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getDateTime().toLocalDate().isAfter(date))
				result.add(transaction);
		return new TransactionList(result);
	}
}
