package ex03;

public class TransactionsLinkedList implements TransactionsList{
	public	Transaction value;
	public	TransactionsLinkedList next;
	private int length = 0;

	public TransactionsLinkedList(Transaction action) {
		this.value = action;
		this.next = null;
		this.length = 1;
	}
	
	public void addTransaction(Transaction action) {
		TransactionsLinkedList node = new TransactionsLinkedList(action);
		TransactionsLinkedList tmp = this;

		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = node;
		this.length++;
	}

	public void removeById(String id) throws TransactionNotFoundException {
		TransactionsLinkedList curr;
		curr = this;
		while (curr.next != null && !id.equals(curr.next.value.getId())) {
			curr = curr.next;
		}
		if (curr.next != null && id.equals(curr.next.value.getId())) {
			curr.next = curr.next.next;
			this.length--;
		} else {
			throw new TransactionNotFoundException("Transaction " + id + " not found!");
		} 
	}

	public Transaction[] toArray() {
		Transaction[] array = new Transaction[this.length];
		TransactionsLinkedList curr = this;

		for (int i = 0; curr != null && i < this.length; i++) {
			array[i] = curr.value;
			curr = curr.next;
		}
		return array;
	}
}
