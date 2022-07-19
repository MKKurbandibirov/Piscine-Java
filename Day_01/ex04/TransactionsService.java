package ex04;

public class TransactionsService {
	private UsersArrayList listOfUsers;

	public void addUser(User newUser) {
		this.listOfUsers.addUser(newUser);
	}

	public Integer getUserBalance(int id) throws UserNotFoundException {
		return this.listOfUsers.getById(id).getBalance();
	}

	public void performTransaction(int recipientId, int senderId, Integer amount) {
		try {
			User recipient = this.listOfUsers.getById(recipientId);
			User sender = this.listOfUsers.getById(recipientId);
			Transaction action1 = new Transaction(recipient, sender, Transaction.CREDIT, -amount);
			Transaction action2 = new Transaction(sender, recipient, Transaction.DEBIT, amount);
	
			action1.setId(action2.getId());
			if (recipient.getBalance() - amount < 0) {
				throw new IllegalTransactionException("Transaction " + action1.getId().toString() + " can't be made!");
			}
			recipient.setBalance(recipient.getBalance() - amount);
			sender.setBalance(sender.getBalance() + amount);

			recipient.getList().addTransaction(action1);
			sender.getList().addTransaction(action2);

		} catch (UserNotFoundException | IllegalTransactionException e) {
			System.out.println(e.getMessage());
		}
	}

	public Transaction[] getTransactionArray() {
		return null; // FIX
	} 
}
