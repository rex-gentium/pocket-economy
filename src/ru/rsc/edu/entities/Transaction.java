package ru.rsc.edu.entities;

import java.time.LocalDateTime;;

public class Transaction {
	private static int idSequence = 0;
	private int id;
	private Account account;
	private Category category;
	private LocalDateTime dateTime;
	private long amount;
	private String comment;
	
	public Transaction(Account account, Category category, LocalDateTime dateTime, long amount) {
		this.id = idSequence++;
		this.account = account;
		this.category = category;
		this.dateTime = dateTime;
		this.amount = amount;
		this.comment = null;
	}
	
	public Transaction(Account account, LocalDateTime dateTime, long amount) {
		this(account, null, dateTime, amount);
	}
	
	// setters
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	// getters
	public int getId() {
		return id;
	}
	
	public Account getAccount() {
		return account;
	}
	
	public Category getCategory() {
		return category;
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
	
	public String toString() {
		StringBuilder result = new StringBuilder(dateTime.toString())
				.append((amount > 0) ? " Поступление " : " Снятие ")
				.append(amount / 100).append(".")
				.append(amount % 100).append(" RUB");
		if (comment != null)
			result.append(" --").append(comment);
		result.append("\n");
		return result.toString();
	}
}
