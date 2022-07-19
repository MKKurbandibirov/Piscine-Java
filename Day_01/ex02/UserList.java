package ex02;

public interface UserList {
	public void addUser(User newUser);
	public User getById(int id) throws UserNotFoundException;
	public User getByIndex(int ind);
	public int getLength();
}
