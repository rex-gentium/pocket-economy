package ru.rsc.edu.entities;

import java.time.LocalDate;
import java.util.*;

public class Report {

	private Account account;
	private LocalDate beginDate;
	private LocalDate endDate;
	private List<CashOperation> operationsHistory;
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public LocalDate getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(LocalDate beginDate) {
		this.beginDate = beginDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<CashOperation> getOperationsHistory() {
		return operationsHistory;
	}

	public void setOperationsHistory(List<CashOperation> operationsHistory) {
		this.operationsHistory = operationsHistory;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder("Отчёт по счёту ").append(account.getName())
				.append(" за период с ").append(beginDate.toString())
				.append(" по ").append(endDate.toString()).append("\n");
		int income, incomeCount, expense, expenseCount;
		income = incomeCount = expense = expenseCount = 0;
		for (CashOperation transaction : operationsHistory) {
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
			.append(" на сумму ").append(income / 100).append(".").append(income % 100).append(" RUB")
			.append("\nВсего списаний ").append(expenseCount)
			.append(" на сумму ").append(expense / 100).append(".").append(expense % 100).append(" RUB")
			.append("\nБаланс: ").append(balance / 100).append(".").append(balance % 100).append(" RUB");
		return result.toString();
	}
}
