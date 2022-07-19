package ex03;

public class User {
	private int id;
	private String 	name;
	private Integer	balance;

	public User(String name) {
		this.setName(name);
		this.setId();
		this.setBalance(0);
	}

	public Integer getId() {
		return this.id;
	}
	public void setId() {
		this.id = UserIdsGenerator.getInstance().generateId();
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Integer getBalance() {
		return this.balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		String str = "UserId - " + this.getId() + "; Username - " + this.getName() + "; Balance - " + this.getBalance() + ";";
		return str;
	}
}

