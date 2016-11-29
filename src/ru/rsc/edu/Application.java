package ru.rsc.edu;

import java.time.*;

import ru.rsc.edu.entities.*;
import ru.rsc.edu.exceptions.*;

public class Application {
	
	public static void main(String[] args) {
		Manager app = new Manager();
		initCategories(app);
		initAccounts(app);
		testCashOperations(app);
		
		// say I wanna filter all transactions made in period 2016, November, 1 - 29
		try {
			Report testReport = app.buildReport("кредитка", LocalDate.of(2016, Month.NOVEMBER, 1), LocalDate.of(2016, Month.NOVEMBER, 29));
			System.out.println(testReport.toString());
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void initCategories(Manager app) {
		try {
			app.registerCategory("meals");
			app.registerCategory("fastfood", "meals");
			app.registerCategory("MacDonalds", "meals\\fastfood");
			app.registerCategory("wearings");
			app.registerCategory("shoes", "wearings");
			app.registerCategory("jewels", "wearings");
			app.registerCategory("rings", "wearings");
			app.moveCategory("wearings\\rings", "wearings\\jewels");
			app.registerCategory("entertainment");
			app.registerCategory("videogames", "entertainment");
			app.registerCategory("cinema", "entertainment");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("\tskipped action");
		}
	}
	
	public static void initAccounts(Manager app) {
		try {
			app.registerAccount("Credit Card 1");
			app.commentAccount("Credit Card 1", "My first account in this app");
			app.renameAccount("Credit Card 1", "кредитка");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("\tskipped action");
		}
	}
	
	public static void testCashOperations(Manager app) {
		long id; // для комментирования расходов, пока нет нормального UI
		try {
			// November, 1
			LocalDate salaryDay = LocalDate.of(2016, Month.NOVEMBER, 1);
			id = app.registerCashOperation("кредитка", LocalDateTime.of(salaryDay, LocalTime.MIN), +37000_00);
			app.commentCashOperation(id, "Salary at last!");
			app.registerCashOperation("кредитка", LocalDateTime.of(salaryDay, LocalTime.NOON), "meals\\fastfood\\MacDonalds", -300_00);
			// November, 2
			LocalDate shoppingDay = salaryDay.plusDays(1);
			app.registerCashOperation("кредитка", LocalDateTime.of(shoppingDay, LocalTime.NOON), "wearings\\shoes", -2500_00);
			// November, 16
			LocalDate dateDay = shoppingDay.plusWeeks(2);
			id = app.registerCashOperation("кредитка", LocalDateTime.of(dateDay, LocalTime.parse("18:00:00")), "entertainment\\cinema", 2 * -400_00);
			app.commentCashOperation(id, "Two cuz was going with my girlfriend");
			id = app.registerCashOperation("кредитка", LocalDateTime.of(dateDay, LocalTime.parse("21:00:00")), "wearings\\jewels\\rings", -32000_00);
			app.commentCashOperation(id, "Why did I buy a diamond ring, am I going to marry or smth?");
			// November, 19
			LocalDate lastDay = dateDay.plusDays(3);
			app.registerCashOperation("кредитка", LocalDateTime.of(lastDay, LocalTime.parse("23:59")), "entertainment\\videogames", -1400_00);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("\tskipped action");
		}
	}
}
