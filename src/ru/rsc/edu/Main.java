package ru.rsc.edu;

import java.time.*;

import ru.rsc.edu.entities.*;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Hello, World!");
		Manager app = new Manager();
		initCategories(app);
		// first register an account
		int creditCard = 90568123;
		app.registerAccount(creditCard, "Credit Card 1");
		app.commentAccount(creditCard, "My first account in this app");
		// day 1
		LocalDate salaryDay = LocalDate.of(2016, Month.NOVEMBER, 1);
		app.registerTransaction(creditCard, LocalDateTime.of(salaryDay, LocalTime.MIN), +37000_00);
		app.commentTransaction(0, "Salary at last!");
		app.registerTransaction(creditCard, "meals", LocalDateTime.of(salaryDay, LocalTime.NOON), -300_00);
		// day 2
		LocalDate shoppingDay = salaryDay.plusDays(1);
		app.registerTransaction(creditCard, "shoes", LocalDateTime.of(shoppingDay, LocalTime.NOON), -2500_00);
		// day 3
		LocalDate dateDay = shoppingDay.plusWeeks(2);
		app.registerTransaction(creditCard, "cinema", LocalDateTime.of(dateDay, LocalTime.parse("18:00:00")), 2 * -400_00);
		app.commentTransaction(1, "Two cuz was going with my girlfriend");
		app.registerTransaction(creditCard, "rings", LocalDateTime.of(dateDay, LocalTime.parse("21:00:00")), -32000_00);
		app.commentTransaction(2, "Of course she wanted the one with a diamond");
		// day 4
		LocalDate lastDay = dateDay.plusDays(3);
		app.registerTransaction(creditCard, "videogames", LocalDateTime.of(lastDay, LocalTime.MAX), -1400_00);
		// say I wanna filter all transactions made in period
		Report fullReport = app.buildReport(creditCard, salaryDay, lastDay);
		System.out.println(fullReport.toString());
	}

	public static void initCategories(Manager app) {
		app.registerCategory("meals");
		app.registerCategory("wearings");
		app.registerCategory("shoes", "wearings");
		app.registerCategory("jewels", "wearings");
		app.registerCategory("rings", "jewels");
		app.registerCategory("entertainment");
		app.registerCategory("videogames", "entertainment");
		app.registerCategory("cinema", "entertainment");
	}
}
