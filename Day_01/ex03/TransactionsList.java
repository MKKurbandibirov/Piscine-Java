package ex03;

public interface TransactionsList {
	public void addTransaction(Transaction action);
	public void removeById(String id) throws TransactionNotFoundException;
	public Transaction[] toArray();
}
