package ru.rsc.edu;
import java.util.*;
import java.time.*;

public class ReportList {
	private List<Report> reports;
	
	public ReportList() {
		reports = new ArrayList<Report>();
	}
	
	public ReportList(List<Report> initial) {
		reports = initial;
	}
	
	public List<Report> get() {
		return reports;
	}
	
	public int add(int accountId, LocalDate beginDate, LocalDate endDate, TransactionList data) {
		Report report = new Report(accountId, beginDate, endDate, data);
		reports.add(report);
		return report.getId();
	}
	
	public Report getById(int id) {
		for (Report report : reports)
			if (report.getId() == id)
				return report;
		return null;
	}
}
