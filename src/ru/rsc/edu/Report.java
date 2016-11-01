package ru.rsc.edu;

import java.time.LocalDate;

public class Report {
	static private int idSequence = 0;
	private int id;
	private int accountId;
	private LocalDate beginDate;
	private LocalDate endDate;
	private TransactionList history;
	
	public Report(int accountId, LocalDate beginDate, LocalDate endDate, TransactionList data) {
		this.id = idSequence++;
		this.accountId = accountId;
		if (beginDate.isBefore(endDate)) {
			this.beginDate = beginDate;
			this.endDate = endDate;
		}
		else {
			this.beginDate = endDate;
			this.endDate = beginDate;
		}
		history = data;
	}
	
	public int getId() {
		return id;
	}
	
	public int getAccountId() {
		return accountId;
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
}
