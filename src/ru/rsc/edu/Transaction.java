package ru.rsc.edu;

import java.time.LocalDateTime;;

public class Transaction {
	private static int idSequence = 0;
	private int id;
	private int accountId;
	private int categoryId;
	private LocalDateTime dateTime;
	private long amount;
	private String comment;
	
	public Transaction(int accountId, int categoryId, LocalDateTime dateTime, long amount) {
		this.id = idSequence++;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.dateTime = dateTime;
		this.amount = amount;
		this.comment = null;
	}
	
	// setters
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	// getters
	public int getId() {
		return id;
	}
	
	public int getAccountId() {
		return accountId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	
	public long getAmount() {
		return amount;
	}
	
	public String getComment() {
		return comment;
	}
}
