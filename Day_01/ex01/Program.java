package ex01;

public class Program {
	public static void main(String[] args) {
		User user1 = new User("John");
		User user2 = new User("Bob");
		User user3 = new User("Bob");
		User user4 = new User("Bob");
		User user5 = new User("Bob");
		
		System.out.println(user1);
		System.out.println(user2);
		System.out.println(user3);
		System.out.println(user4);
		System.out.println(user5);

	}
}