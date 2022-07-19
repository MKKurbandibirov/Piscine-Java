package ex00;

import java.util.UUID;

import org.w3c.dom.UserDataHandler;

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
		this.amount = amount;
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
		if (getCategory() == CREDIT && getAmount() > 0) {
			this.amount = 0;
		} else if (getCategory() == DEBIT && getAmount() < 0) {
			this.amount = 0;
		} else {
			this.amount = amount;
		}
	}

	@Override
	public String toString() {
		String category = this.getCategory().toString() == CREDIT.toString() ? "Credit" : "Debit";
		String str = "Sender - " + this.getSender().getName() + "; Recipient - " + this.getRecipient().getName() + "; Category - " + category + "; Amount - " + this.getAmount() + ";";
		return str;
	}
}