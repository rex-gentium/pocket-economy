package ru.rsc.edu;

import java.time.*;

import ru.rsc.edu.collections.*;
import ru.rsc.edu.entities.*;
import ru.rsc.edu.exceptions.*;

/* Базовый класс, релизующий приложение
 * позволяет получать доступ к счетам, категориям, транзакциям и отчётам
 * и регистрировать новые объекты
 * */
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
	
	// getters
	/*public Account getAccountById(int id) {
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
	}*/
		
	// registering
	public void registerAccount(int accNumber, String accName) {
		try {
			accountManager.add(new Account(accNumber, accName));
		}
		catch (AlreadyExistsException e) {
			alert(e);
		}
	}
	
	public void registerCategory(String catName, String superCategoryName) {
		Category superCat = categoryManager.getByName(superCategoryName);
		if (superCat == null) {
			alert(new NotFoundException("No such category with name " + superCategoryName));
			return;
		}
		
		try {
			categoryManager.add(new Category(catName, superCat));
		}
		catch (AlreadyExistsException e) {
			alert(e);
		}
	}
	
	public void registerCategory(String catName) {
		try {
			categoryManager.add(new Category(catName));
		}
		catch (AlreadyExistsException e) {
			alert(e);
		}
	}
		
	public void registerTransaction(int accountNumber, String category, LocalDateTime dateTime, long amount) {
		Account acc = accountManager.getByNumber(accountNumber);
		if (acc == null) {
			alert(new NotFoundException("No such account with number " + accountNumber));
			return;
		}
		Category cat = categoryManager.getByName(category);
		if (cat == null) {
			alert(new NotFoundException("No such category with name " + category));
			return;
		}
		
		try {
			transactionManager.add(new Transaction(acc, cat, dateTime, amount));
		} 
		catch (AlreadyExistsException e) {
			alert(e);
		}
	}
	
	public void registerTransaction(int accountNumber, LocalDateTime dateTime, long amount) {
		Account acc = accountManager.getByNumber(accountNumber);
		if (acc == null) {
			alert(new NotFoundException("No such account with number " + accountNumber));
			return;
		}
		try {
			transactionManager.add(new Transaction(acc, dateTime, amount));
		} 
		catch (AlreadyExistsException e) {
			alert(e);
		}
	}
	
	public void registerReport(int accountNumber, LocalDate beginDate, LocalDate endDate) {
		Report report = buildReport(accountNumber, beginDate, endDate);
		try {
			reportManager.add(report);
		}
		catch (AlreadyExistsException e) {
			alert(e);
		}
	}
	
	public Report buildReport(int accountNumber, LocalDate beginDate, LocalDate endDate) {
		Account account = accountManager.getByNumber(accountNumber);
		if (account == null) {
			alert(new NotFoundException("No such account with number " + accountNumber));
			return null;
		}
		TransactionList reportData = transactionManager.selectByAccount(account)
			.selectAfterDate(beginDate.minusDays(1))
			.selectBeforeDate(endDate.plusDays(1));
		return new Report(account, beginDate, endDate, reportData);
	}
	
	// comments
	public void commentAccount(int accountNumber, String comment) {
		Account account = accountManager.getByNumber(accountNumber);
		if (account == null) {
			alert(new NotFoundException("No such account with number " + accountNumber));
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
	
	public void alert(Exception ex) {
		ex.printStackTrace();
	}
}
