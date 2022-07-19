package ex00;

public class Program {
	public static void main(String[] args) {
		User user1 = new User("John", 1);
		User user2 = new User("Bob", 2);
		
		System.out.println(user1);
		System.out.println(user2);

		Transaction action = new Transaction(user1, user2, Transaction.DEBIT, 100);
		System.out.println(action);
	}
}
