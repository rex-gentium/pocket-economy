package ru.rsc.edu;

import java.time.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello, World!");
		Manager app = new Manager();
		// initialize categories
		app.registerCategory("meals");
		app.registerCategory("wearings");
		app.registerCategory("shoes", "wearings");
		app.registerCategory("jewels", "wearings");
		app.registerCategory("rings", "jewels");
		app.registerCategory("entertainment");
		app.registerCategory("videogames", "entertainment");
		app.registerCategory("cinema", "entertainment");

		// first register an account
		int firstAccount = app.registerAccount(90568123, "Credit Card 1");
		app.commentAccount(firstAccount, "My first account in this app");
		// day 1
		LocalDate salaryDay = LocalDate.of(2016, Month.NOVEMBER, 1);
		int salary = app.registerTransaction(firstAccount, LocalDateTime.of(salaryDay, LocalTime.MIN), +37000_00);
		app.commentTransaction(salary, "Salary at last!");
		app.registerTransaction(firstAccount, "meals", LocalDateTime.of(salaryDay, LocalTime.NOON), -300_00);
		// day 2
		LocalDate shoppingDay = salaryDay.plusDays(1);
		app.registerTransaction(firstAccount, "shoes", LocalDateTime.of(shoppingDay, LocalTime.NOON), -2500_00);
		// day 3
		LocalDate dateDay = shoppingDay.plusWeeks(2);
		int cinemaTour = app.registerTransaction(firstAccount, "cinema", LocalDateTime.of(dateDay, LocalTime.parse("18:00:00")), 2 * -400_00);
		app.commentTransaction(cinemaTour, "Two cuz was going with my girlfriend");
		int ring = app.registerTransaction(firstAccount, "rings", LocalDateTime.of(dateDay, LocalTime.parse("21:00:00")), -32000_00);
		app.commentTransaction(ring, "Of course she wanted the one with a diamond");
		// day 4
		LocalDate lastDay = dateDay.plusDays(3);
		app.registerTransaction(firstAccount, "videogames", LocalDateTime.of(lastDay, LocalTime.MAX), -1400_00);
		// say I wanna filter all transactions made in period
		Report fullReport = app.generateReport(firstAccount, salaryDay, lastDay);
		int income = 0;
		int expense = 0;
		for (Transaction transaction : fullReport.getTransactions().get()) {
			StringBuilder sb = new StringBuilder();
			sb.append(transaction.getDateTime().toString());
			sb.append(" " + transaction.getAmount() / 100f);
			Category category = app.getCategoryById(transaction.getCategoryId());
			if (category != null && category.getId() != app.getRootCategoryId())
				sb.append(" " + category.getName());
			if (transaction.getComment() != null)
				sb.append(" --" + transaction.getComment());
			System.out.println(sb);
			if (transaction.getAmount() > 0)
				income += transaction.getAmount();
			else expense += transaction.getAmount();
		}
		System.out.println("Total income : " + income / 100f);
		System.out.println("Total expense : " + expense / 100f);
		System.out.println("Balance : " + (income + expense) / 100f);
	}

}
