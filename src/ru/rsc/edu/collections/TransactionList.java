package ru.rsc.edu.collections;

import java.time.*;
import java.util.*;

import ru.rsc.edu.entities.*;
import ru.rsc.edu.exceptions.AlreadyExistsException;

public class TransactionList {
	List<Transaction> transactions;
	
	public TransactionList() {
		transactions = new ArrayList<Transaction>();
	}
	
	public TransactionList(List<Transaction> initial) {
		transactions = initial;
	}
	
	public void add(Transaction t) throws AlreadyExistsException {
		if (!transactions.contains(t))
			transactions.add(t);
		else throw new AlreadyExistsException("Transaction " + t.toString() + " already exists");
	}
	
	// getters
	public List<Transaction> get() {
		return transactions;
	}
	
	public Transaction getById(int id) {
		for (Transaction transaction : transactions)
			if (transaction.getId() == id)
				return transaction;
		return null;
	}
	
	public TransactionList selectByAccount(Account acc) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getAccount().equals(acc))
				result.add(transaction);
		return new TransactionList(result);
	}
	
	public TransactionList selectByCategory(Category category) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getCategory().equals(category))
				result.add(transaction);
		return new TransactionList(result);
	}
	
	public TransactionList selectByDate(LocalDate date) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getDateTime().toLocalDate().equals(date))
				result.add(transaction);
		return new TransactionList(result);
	}
	
	public TransactionList selectBeforeDate(LocalDate date) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getDateTime().toLocalDate().isBefore(date))
				result.add(transaction);
		return new TransactionList(result);
	}
	
	public TransactionList selectAfterDate(LocalDate date) {
		List<Transaction> result = new ArrayList<Transaction>();
		for (Transaction transaction : transactions)
			if (transaction.getDateTime().toLocalDate().isAfter(date))
				result.add(transaction);
		return new TransactionList(result);
	}
}
