package ru.rsc.edu.entities;

import java.time.LocalDateTime;
import java.util.*;

public class CashOperation {

	private Account account;
	private Category category;
	private LocalDateTime dateTime;
	private long amount;
	private String comment;
	
	public static class Category {
		private String name;
		private Category parentCategory;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Category getParentCategory() {
			return parentCategory;
		}
		public void setParentCategory(Category parentCategory) {
			this.parentCategory = parentCategory;
		}
		public String getFullName() {
			Category supCategory = parentCategory;
			Stack<Category> categoryStack = new Stack<Category>();
			while(supCategory != null) {
				categoryStack.push(supCategory);
				supCategory = supCategory.getParentCategory();
			}
			StringBuilder sb = new StringBuilder();
			while(!categoryStack.isEmpty())
				sb.append(categoryStack.pop().name).append("\\");
			sb.append(this.name);
			return sb.toString();
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getFullName().hashCode();
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Category other = (Category) obj;
			if (!name.equals(other.name))
				return false;
			if (!this.getFullName().equals(other.getFullName()))
				return false;
			return true;
		}
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String toString() {
		StringBuilder result = new StringBuilder(dateTime.toString())
				.append((amount > 0) ? "\tПоступление\t" : "\tСнятие\t")
				.append(amount / 100).append(".")
				.append(amount % 100).append(" RUB");
		if (category != null)
			result.append("\t[").append(category.getFullName()).append("]");
		if (comment != null)
			result.append("\t--").append(comment);
		result.append("\n");
		return result.toString();
	}
}
