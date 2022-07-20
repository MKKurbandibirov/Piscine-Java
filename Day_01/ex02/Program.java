package ex02;

public class Program {
	public static void main(String[] args) {
		UsersArrayList users = new UsersArrayList();

		User user1 = new User("Bob1");
		User user2 = new User("Bob2");
		User user3 = new User("Bob3");
		User user4 = new User("Bob4");
		User user5 = new User("Bob5");
		User user6 = new User("Bob6");
		User user7 = new User("Bob7");
		User user8 = new User("Bob8");
		User user9 = new User("Bob9");
		User user10 = new User("Bob10");
		User user11 = new User("Bob11");
		User user12 = new User("Bob12");


		users.addUser(user1);
		users.addUser(user8);
		users.addUser(user3);
		users.addUser(user10);
		users.addUser(user4);
		users.addUser(user6);
		users.addUser(user2);
		users.addUser(user7);
		users.addUser(user5);
		users.addUser(user9);

		System.out.println(users.getLength());

		try {
			System.out.println(users.getById(5));
		} catch (UserNotFoundException e) {
			System.err.println(e.getMessage());
		}

		System.out.println(users.getByIndex(8));

		users.addUser(user11);
		users.addUser(user12);

		System.out.println(users.getLength());
	}
}
