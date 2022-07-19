package ex04;

public class UsersArrayList implements UserList {
	private int capacity = 10;
	private int length = 0;
	private User[] users;

	public UsersArrayList() {
		this.users = new User[this.capacity];
	}

	public void addUser(User newUser) {
		if (this.length != this.capacity) {
			this.users[length] = newUser;
			this.length++;
		} else {
			this.capacity += this.capacity / 2;
			User[] tmp = new User[this.capacity];
			for (int i = 0; i < this.length; i++) {
				tmp[i] = this.users[i];
			}
			tmp[length] = newUser;
			this.length++;
			this.users = tmp;
		}
	}

	public User getById(int id) throws UserNotFoundException {
		for (int i = 0 ; i < this.length; i++) {
			if (this.users[i].getId() == id) {
				return users[i];
			}
		}
		throw new UserNotFoundException("User with ID " + id + " not found!");
	}

	public User getByIndex(int ind) {
		if (ind < this.length && ind > 0) {
			return this.users[ind];
		}
		return null;
	}

	public int getLength() {
		return this.length;
	}
}
