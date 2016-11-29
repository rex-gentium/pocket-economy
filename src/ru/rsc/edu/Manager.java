package ru.rsc.edu;

import java.time.*;
import java.util.*;

import ru.rsc.edu.entities.*;
import ru.rsc.edu.entities.CashOperation.Category;
import ru.rsc.edu.exceptions.*;

/* Базовый класс, релизующий приложение
 * позволяет получать доступ к счетам, категориям, транзакциям и отчётам
 * и регистрировать новые объекты
 * */
public class Manager {
	
	private Set<Account> accounts;
	private static long operationIdSequence = 0;
	private Map<Long, CashOperation> operations;
	private Set<CashOperation.Category> categories;
	private static long reportIdSequence = 0;
	private Map<Long, Report> reports;
	
	public enum ReportSaveOption { DO_NOT_SAVE, SAVE };
	
	public Manager() {
		accounts = new HashSet<Account>();
		operations = new HashMap<Long, CashOperation>();
		categories = new HashSet<CashOperation.Category>();
		reports = new HashMap<Long, Report>();
	}
	
	// registering
	public void registerAccount(String name) throws AlreadyExistsException {
		Account acc = new Account();
		acc.setName(name);
		if (!accounts.add(acc))
			throw new AlreadyExistsException("Account with name " + name + " already registered");
	}
	
	public void registerCategory(String name, String parentFullName) throws NotFoundException, AlreadyExistsException {
		Category category = new CashOperation.Category();
		category.setName(name);
		Category parent = getCategoryByName(parentFullName);
		if (parent == null)
			throw new NotFoundException("There's no category with name " + parentFullName);
		category.setParentCategory(parent);
		if (!categories.add(category))
			throw new AlreadyExistsException("Category " + category.getFullName() + "already exists");
	}
	
	public void registerCategory(String name) throws AlreadyExistsException {
		Category category = new CashOperation.Category();
		category.setName(name);
		category.setParentCategory(null);
		if (!categories.add(category))
			throw new AlreadyExistsException("Category " + name + "already exists");
	}
		
	public long registerCashOperation(String accountName, LocalDateTime dateTime, String categoryName, long amount) throws NotFoundException {
		CashOperation operation = new CashOperation();
		Account account = getAccountByName(accountName);
		if (account == null) 
			throw new NotFoundException("There's no account with name " + accountName);
		Category category = (categoryName == null) ? null : getCategoryByName(categoryName);
		if (categoryName != null && category == null)
			throw new NotFoundException("There's no category with name " + categoryName);
		operation.setAccount(account);
		operation.setAmount(amount);
		operation.setCategory(category);
		operation.setDateTime(dateTime);
		long key = operationIdSequence++;
		operations.put(key, operation);
		return key; // временно, чтобы иметь возможность извне сослаться на запись
	}
	
	public long registerCashOperation(String accountName, LocalDateTime dateTime, long amount) throws NotFoundException {
		return registerCashOperation(accountName, dateTime, null, amount);
	}
	
	public Report buildReport(String accountName, LocalDate beginDate, LocalDate endDate, ReportSaveOption ... saveOptions) throws NotFoundException {
		Account account = getAccountByName(accountName);
		if (account == null)
			throw new NotFoundException("No such account with number " + accountName);
		Report report = new Report();
		report.setAccount(account);
		report.setBeginDate(beginDate);
		report.setEndDate(endDate);
		List<CashOperation> history = new LinkedList<CashOperation>();
		for (Map.Entry<Long, CashOperation> entry : operations.entrySet()) {
			CashOperation operation = entry.getValue();
			if (operation.getAccount().equals(account)) {
				LocalDate operationDate = operation.getDateTime().toLocalDate();
				if (operationDate.isAfter(beginDate.minusDays(1)) && operationDate.isBefore(endDate.plusDays(1)))
					history.add(operation);
			}
		}
		report.setOperationsHistory(history);
		if (saveOptions.length > 0 && saveOptions[0] == ReportSaveOption.SAVE) {
			long key = reportIdSequence++;
			reports.put(key, report);
		}		
		return report;			
	}
	
	// user actions
	public void commentAccount(String accountName, String comment) throws NotFoundException {
		Account account = getAccountByName(accountName);
		if (account == null)
			throw new NotFoundException("There's no account with name " + accountName);
		account.setComment(comment);
	}
	
	public void commentCashOperation(long id, String comment) throws NotFoundException {
		if (!operations.containsKey(id))
			throw new NotFoundException("There's no operation with id " + id);
		CashOperation operation = operations.get(id);
		operation.setComment(comment);
	}
	
	public void renameAccount(String currentName, String newName) throws NotFoundException, InvalidArgumentException {
		Account account = getAccountByName(currentName);
		if (account == null)
			throw new NotFoundException("There's no account with name " + currentName);
		if (newName == null)
			throw new InvalidArgumentException("Cannot rename to null!");
		account.setName(newName);
	}
	
	public void changeCashOperationCategory(long id, String newCategoryFullname) throws NotFoundException {
		if (!operations.containsKey(id))
			throw new NotFoundException("There's no operation with id " + id);
		CashOperation operation = operations.get(id);
		Category category = getCategoryByName(newCategoryFullname);
		if (category == null)
			throw new NotFoundException("There's no category with name" + newCategoryFullname);
		operation.setCategory(category);
	}
	
	public void moveCategory(String categoryFullName, String newParentFullName) throws NotFoundException {
		Category category = getCategoryByName(categoryFullName);
		if (category == null)
			throw new NotFoundException("There's no category " + categoryFullName);
		Category parent = getCategoryByName(newParentFullName);
		if (parent == null)
			throw new NotFoundException("There's no category " + newParentFullName);
		category.setParentCategory(parent);
	}
	
	// private getters
	private Account getAccountByName(String name) {
		for (Account account : accounts)
			if (account.getName().equals(name))
				return account;
		return null;
	}
	
	private Category getCategoryByName(String fullName) {
		for (Category category : categories)
			if (category.getFullName().equals(fullName))
				return category;
		return null;
	}
}
