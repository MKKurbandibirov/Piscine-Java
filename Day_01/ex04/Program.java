package ex04;

public class Program {
	public static void main(String[] args) {
		TransactionsService service = new TransactionsService();
		User user1 = new User("Bob1");
		User user2 = new User("Bob2");
		User user3 = new User("Bob3");
		user1.setBalance(10000);
		user2.setBalance(2500);
		user3.setBalance(100);

		service.addUser(user1);
		service.addUser(user2);
		service.addUser(user3);

		System.out.println(service);

		try {
			System.out.println(service.getUserBalance(1));
			System.out.println(service.getUserBalance(5));
		} catch (UserNotFoundException e) {
			System.err.println(e.getMessage());
		}
		System.out.println();

		service.performTransaction(0, 1, 500);

		System.out.println(service);

		Transaction actions1[] = service.getTransactionArray(0);
		for (int i = 0; i < actions1.length; i++) {
			System.out.println(actions1[i]);
		}
		System.out.println();

		service.performTransaction(0, 2, 50);
		
		System.out.println(service);

		Transaction[] actions2 = service.getTransactionArray(0);
		for (int i = 0; i < actions2.length; i++) {
			System.out.println(actions2[i]);
		}
		System.out.println();

		service.deleteTransactionForUser(0, actions2[0].getId());

		Transaction[] actions3 = service.getTransactionArray(0);
		for (int i = 0; i < actions3.length; i++) {
			System.out.println(actions3[i]);
		}
		System.out.println();

		Transaction notValid = new Transaction(user3, user2, Transaction.DEBIT, 5464);

		user3.getList().addTransaction(notValid);

		Transaction[] notPaired = service.checkValidity();
		for (int i = 0; i < notPaired.length; i++) {
			System.out.println(notPaired[i]);
		}
	}
}

