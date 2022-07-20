package ex05;

public class UserIdsGenerator {
	private static UserIdsGenerator INSTANCE;
	private static int idCounter = 0;
	private UserIdsGenerator() {}

	public static UserIdsGenerator getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UserIdsGenerator();
		}
		return INSTANCE;
	}

	public static int generateId() {
		return idCounter++;
	}
}
