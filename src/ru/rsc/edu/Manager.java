package ru.rsc.edu;

import java.time.*;

public class Manager {
	private AccountList accountManager;
	private CategoryList categoryManager;
	private TransactionList transactionManager;
	private ReportList reportManager;
	
	public Manager() {
		accountManager = new AccountList();
		categoryManager = new CategoryList();
		transactionManager = new TransactionList();
		reportManager = new ReportList();
	}
	
	// registers
	public int registerAccount(int accNumber, String accName) {
		return accountManager.add(accNumber, accName);
	}
	
	public int registerCategory(String catName, int superCategoryId) {
		if (categoryManager.getById(superCategoryId) == null) {
			System.out.println("No such category with id " + superCategoryId);
			return -1;
		}
		return categoryManager.add(catName, superCategoryId);
	}
	
	public int registerCategory(String name, String superCategoryName) {
		Category superCategory = categoryManager.getByName(superCategoryName);
		if (superCategory == null) {
			System.out.println("No such category with name " + superCategoryName);
			return -1;
		}
		return registerCategory(name, superCategory.getId());
	}
	
	public int registerCategory(String catName) {
		return registerCategory(catName, categoryManager.getRoot().getId());
	}
		
	public int registerTransaction(int accountId, String category, LocalDateTime dateTime, long amount) {
		if (accountManager.getById(accountId) == null) {
			System.out.println("No such account with id " + accountId);
			return -1;
		}
		Category cat = categoryManager.getByName(category);
		if (cat == null) {
			System.out.println("No such category with name " + category);
			return -1;
		}
		return transactionManager.add(accountId, cat.getId(), dateTime, amount);
	}
	
	public int registerTransaction(int accountId, LocalDateTime dateTime, long amount) {
		return registerTransaction(accountId, categoryManager.getRoot().getName(), dateTime, amount);
	}
	
	// getters
	public Account getAccountById(int id) {
		return accountManager.getById(id);
	}
	
	public Category getCategoryById(int id) {
		return categoryManager.getById(id);
	}
	
	public Transaction getTransactionById(int id) {
		return transactionManager.getById(id);
	}
	
	public Report getReportById(int id) {
		return reportManager.getById(id);
	}
	
	public int getRootCategoryId() {
		return categoryManager.getRoot().getId();
	}
	
	// comments
	public void commentAccount(int accountId, String comment) {
		Account account = accountManager.getById(accountId);
		if (account == null) {
			System.out.println("No account with id " + accountId);
			return;
		}
		account.setComment(comment);
	}
	
	public void commentTransaction(int transactionId, String comment) {
		Transaction transaction = transactionManager.getById(transactionId);
		if (transaction == null) {
			System.out.println("No account with id " + transactionId);
			return;
		}
		transaction.setComment(comment);
	}
	
	// report without save
	public Report generateReport(int accountId, LocalDate beginDate, LocalDate endDate) {
		Account account = accountManager.getById(accountId);
		if (account == null) {
			System.out.println("No account with id " + accountId);
			return null;
		}
		TransactionList reportData = transactionManager.getByAccount(accountId)
			.getAfterDate(beginDate.minusDays(1))
			.getBeforeDate(endDate.plusDays(1));
		return new Report(accountId, beginDate, endDate, reportData);
	}
	
	// report with save
	public int generateAndRegisterReport(int accountId, LocalDate beginDate, LocalDate endDate) {
		Account account = accountManager.getById(accountId);
		if (account == null) {
			System.out.println("No account with id " + accountId);
			return -1;
		}
		TransactionList reportData = transactionManager.getByAccount(accountId)
			.getAfterDate(beginDate.minusDays(1))
			.getBeforeDate(endDate.plusDays(1));
		return reportManager.add(accountId, beginDate, endDate, reportData);
	}
}
