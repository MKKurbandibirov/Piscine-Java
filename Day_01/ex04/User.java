package ex04;

public class User {
	private int id;
	private String 	name;
	private Integer	balance;
	private TransactionsLinkedList list;

	public User(String name) {
		this.setName(name);
		this.setId();
		this.setBalance(0);
		this.setList(null);
	}

	public User(String name, TransactionsLinkedList list) {
		this.setName(name);
		this.setId();
		this.setBalance(0);
		this.setList(list);
	}

	public TransactionsLinkedList getList() {
		return this.list;
	}
	public void setList(TransactionsLinkedList list) {
		if (list == null) {
			this.list = new TransactionsLinkedList();
		} else {
			this.list = list;
		}
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
		if (balance < 0) {
			this.balance = 0;
		}
		this.balance = balance;
	}

	@Override
	public String toString() {
		String str = "UserId - " + this.getId() + "; Username - " + this.getName() + "; Balance - " + this.getBalance() + ";";
		return str;
	}
}

