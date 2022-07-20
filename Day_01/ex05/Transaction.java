package ex05;

import java.util.UUID;

public class Transaction {
	private UUID id;
	private User recipient;
	private User sender;
	private Integer category;
	private Integer amount;

	public static final Integer CREDIT = 0;
	public static final Integer DEBIT = 1;

	public Transaction(User recipient, User sender, Integer category, Integer amount) {
		this.id = UUID.randomUUID();
		this.setRecipient(recipient);
		this.setSender(sender);
		this.setCategory(category);
		this.setAmount(amount);
	}

	public User getRecipient() {
		return this.recipient;
	}
	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public User getSender() {
		return this.sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}

	public Integer getCategory() {
		return this.category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getAmount() {
		return this.amount;
	}
	public void setAmount(Integer amount) {
		if (this.getCategory() == Transaction.CREDIT && amount > 0) {
			this.amount = 0;
		} else if (this.getCategory() == Transaction.DEBIT && amount < 0) {
			this.amount = 0;
		} else {
			this.amount = amount;
		}
	}

	public UUID getId() {
		return this.id;
	}
	public void setId(UUID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		String category;
		if (this.getCategory() == Transaction.CREDIT) {
			category = "Credit";
		} else {
			category = "Debit";
		}
		String str = "Sender - " + this.getSender().getName() + "; Recipient - " + this.getRecipient().getName() + "; Category - " + category + "; Amount - " + this.getAmount() + ";";
		return str;
	}
}
