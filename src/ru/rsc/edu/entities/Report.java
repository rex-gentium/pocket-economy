package ru.rsc.edu.entities;

import java.time.LocalDate;

import ru.rsc.edu.collections.TransactionList;

public class Report {
	private Account account;
	private LocalDate beginDate;
	private LocalDate endDate;
	private TransactionList history;
	
	public Report(Account account, LocalDate beginDate, LocalDate endDate, TransactionList data) throws IllegalArgumentException {
		this.account = account;
		if (beginDate.isBefore(endDate)) {
			this.beginDate = beginDate;
			this.endDate = endDate;
		}
		else throw new IllegalArgumentException("Attempted to create a report with beginDate after endDate");
		this.history = data;
	}
	
	public boolean equals(Report rep) {
		// структурную идентичность определяют принадлежность
		// счёту и отчётный период
		return (this.account.equals(rep.account) &&
				this.beginDate.equals(rep.beginDate) &&
				this.endDate.equals(rep.endDate));
	}
	
	public void setTransactions(TransactionList t) {
		this.history = t;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public LocalDate getBeginDate() {
		return beginDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	public TransactionList getTransactions() {
		return history;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("Отчёт")
				.append(" за период с ").append(beginDate.toString())
				.append(" по ").append(endDate.toString()).append("\n");
		int income, incomeCount, expense, expenseCount;
		income = incomeCount = expense = expenseCount = 0;
		for (Transaction transaction : history.get()) {
			result.append(transaction.toString());
			if (transaction.getAmount() > 0) {
				income += transaction.getAmount();
				++incomeCount;
			}
			else {
				expense += transaction.getAmount();
				++expenseCount;
			}
		}
		int balance = income + expense;
		result.append("Всего поступлений ").append(incomeCount)
			.append(" на сумму ").append(income / 100).append(".").append(income % 100)
			.append("\nВсего списаний ").append(expenseCount)
			.append(" на сумму ").append(expense / 100).append(".").append(expense % 100)
			.append("\nБаланс: ").append(balance / 100).append(".").append(balance % 100);
		return result.toString();
	}
}
