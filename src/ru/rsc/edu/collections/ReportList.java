package ru.rsc.edu.collections;
import java.util.*;

import ru.rsc.edu.entities.Report;
import ru.rsc.edu.exceptions.AlreadyExistsException;

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
	
	public void add(Report report) throws AlreadyExistsException {
		if (reports.contains(report)) {
			// перезапись
			int rewriteIndex = reports.indexOf(report);
			reports.get(rewriteIndex).setTransactions(report.getTransactions());
			StringBuilder errorMsg = new StringBuilder("Rewrited report \"")
					.append(report.getBeginDate().toString())
					.append(" - ").append(report.getEndDate().toString())
					.append("\" for account ").append(report.getAccount().getNumber());
			throw new AlreadyExistsException(errorMsg.toString());
		}
		else reports.add(report);
	}
}
