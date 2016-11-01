package ru.rsc.edu;

public class ReportSpec {
	private int reportId;
	private int transactionId;
	
	public ReportSpec(int reportId, int transactionId) {
		this.reportId = reportId;
		this.transactionId = transactionId;
	}
	
	public int getReportId() {
		return reportId;
	}
	
	public int getTransactionId() {
		return transactionId;
	}
}
