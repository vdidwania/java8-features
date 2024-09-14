package com.stream;

public class Transaction {
	private int amount;
	private String date;

	public Transaction(String date, int amount) {
		this.amount = amount;
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
