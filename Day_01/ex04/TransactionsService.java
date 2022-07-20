package ex04;

import java.util.UUID;

public class TransactionsService {
	private UsersArrayList listOfUsers;

	public TransactionsService() {
		this.listOfUsers = new UsersArrayList();
	}

	public void addUser(User newUser) {
		this.listOfUsers.addUser(newUser);
	}

	public Integer getUserBalance(int id) throws UserNotFoundException {
		return this.listOfUsers.getById(id).getBalance();
	}

	public void performTransaction(int recipientId, int senderId, Integer amount) {
		try {
			User recipient = this.listOfUsers.getById(recipientId);
			User sender = this.listOfUsers.getById(senderId);
			Transaction action1 = new Transaction(recipient, sender, Transaction.CREDIT, -1 * amount);
			Transaction action2 = new Transaction(sender, recipient, Transaction.DEBIT, amount);
	
			action1.setId(action2.getId());
			if (sender.getBalance() - amount < 0) {
				throw new IllegalTransactionException("Transaction " + action1.getId().toString() + " can't be made!");
			}

			recipient.setBalance(recipient.getBalance() + amount);
			sender.setBalance(sender.getBalance() - amount);

			recipient.getList().addTransaction(action1);
			sender.getList().addTransaction(action2);

		} catch (UserNotFoundException | IllegalTransactionException e) {
			System.err.println(e.getMessage());
		}
	}

	public Transaction[] getTransactionArray(int userId) {
		try {
			User user = this.listOfUsers.getById(userId);
			Transaction[] actions = user.getList().toArray();
			return actions;
		} catch (UserNotFoundException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public void deleteTransactionForUser(int userId, UUID actionId) {
		try {
			User user = this.listOfUsers.getById(userId);
			user.getList().removeById(actionId.toString());
		} catch (UserNotFoundException | TransactionNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	public Transaction[] checkValidity() {
		int length = 0;
		TransactionsLinkedList list = new TransactionsLinkedList();
		for (int i = 0; i < this.listOfUsers.getLength(); i++) {
			length += this.listOfUsers.getByIndex(i).getList().getLength();
			for (int j = 0; j < this.listOfUsers.getByIndex(i).getList().getLength(); j++) {
				list.addTransaction(this.listOfUsers.getByIndex(i).getList().toArray()[j]);
			}
		}
		TransactionsLinkedList notPaired = new TransactionsLinkedList();
		Transaction [] all = list.toArray();
		for (int i = 0; i < length; i++) {
			int count = 0;
			for (int j = 0; j < length; j++) {
				if (all[i].getId().toString().equals(all[j].getId().toString())) {
					count++;
				}
			}
			if (count != 2) {
				notPaired.addTransaction(all[i]);
			}
		}
		return notPaired.toArray();
	}

	@Override
	public String toString() {
		String result = "";
		for (int i = 0; this.listOfUsers.getByIndex(i) != null; i++) {
			result += this.listOfUsers.getByIndex(i).toString() + "\n";
		}
		return result;
	}
}
