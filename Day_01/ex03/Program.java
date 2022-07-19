package ex03;

public class Program {
	public static void main(String[] args) {
		User user1 = new User("Bob1");
		User user2 = new User("Bob2");
		User user3 = new User("Bob3");
		Transaction action1 = new Transaction(user1, user2, Transaction.CREDIT, -10000);
		Transaction action2 = new Transaction(user2, user3, Transaction.DEBIT, 1050);
		Transaction action3 = new Transaction(user3, user1, Transaction.CREDIT, -4340);
		TransactionsLinkedList actions = new TransactionsLinkedList(action1);
		TransactionsLinkedList tmp;

		actions.addTransaction(action2);

		try {
			actions.removeById(action2.getId());
		} catch (TransactionNotFoundException e) {
			System.out.println(e.getMessage());
		}

		actions.addTransaction(action3);
		actions.addTransaction(action2);
		actions.addTransaction(action3);
		
		tmp = actions;
		while (tmp != null) {
			System.out.println(tmp.value);
			tmp = tmp.next;
		}

		System.out.println("------------------------------------------");

		try {
			actions.removeById(action2.getId());
			actions.removeById(action3.getId());
			actions.removeById("666");
		} catch (TransactionNotFoundException e) {
			System.out.println(e.getMessage());
		}

		tmp = actions;
		while (tmp != null) {
			System.out.println(tmp.value);
			tmp = tmp.next;
		}

		System.out.println("------------------------------------------");

		Transaction[] array = actions.toArray();
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
