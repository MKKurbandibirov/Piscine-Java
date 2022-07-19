package ex00;

public class User {
	private Integer id;
	private String 	name;
	private Integer	balance;

	public User(String name, Integer id) {
		this.setName(name);
		this.setId(id);
		this.setBalance(0);
	}

	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
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
