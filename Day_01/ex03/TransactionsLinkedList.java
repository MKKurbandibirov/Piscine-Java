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

	public int getLength() {
		return this.length;
	}
	
	public void addTransaction(Transaction action) {
		TransactionsLinkedList node = new TransactionsLinkedList(action);
		TransactionsLinkedList tmp = this;

		if (this.value == null) {
			this.value = action;
			this.length++;
			return;
		}
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = node;
		this.length++;
	}

	public void removeById(String id) throws TransactionNotFoundException {
		TransactionsLinkedList curr;
		curr = this;
		while (curr.next != null && !id.equals(curr.next.value.getId().toString())) {
			curr = curr.next;
		}
		if (curr.next != null && id.equals(curr.next.value.getId().toString())) {
			curr.next = curr.next.next;
			this.length--;
		} else if (curr.next == null && id.equals(curr.value.getId().toString())) {
			curr.value = null;
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
