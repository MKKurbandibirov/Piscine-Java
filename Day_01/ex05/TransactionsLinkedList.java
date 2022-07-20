package ex05;

public class TransactionsLinkedList implements TransactionsList{
	private TransactionsLinkedListNode start;
	private TransactionsLinkedListNode end;
	private int length = 0;

	public TransactionsLinkedList() {
		this.start = new TransactionsLinkedListNode();
		this.end = new TransactionsLinkedListNode();
		this.length = 0;
	}
	public TransactionsLinkedList(Transaction action) {
		this.start = new TransactionsLinkedListNode(action);
		this.end = this.start;
		this.length = 1;
	}

	public int getLength() {
		return this.length;
	}
	
	public void addTransaction(Transaction action) {
		TransactionsLinkedListNode node = new TransactionsLinkedListNode(action);

		node.setNextNode(this.start);
		this.start = node;
		this.length++;
	}

	public void removeById(String id) throws TransactionNotFoundException {
		TransactionsLinkedListNode curr;
		curr = this.start;

		if (id.equals(curr.getValue().getId().toString())) {
			this.start = this.start.getNextNode();
			this.length--;
			return;
		}
		while (curr.getNextNode() != null && !id.equals(curr.getNextNode().getValue().getId().toString())) {
			curr = curr.getNextNode();
		}
		if (curr.getNextNode() != null && id.equals(curr.getNextNode().getValue().getId().toString())) {
			curr.setNextNode(curr.getNextNode().getNextNode());
			this.length--;
		} else if (curr.getNextNode() == null && id.equals(curr.getValue().getId().toString())) {
			curr = null;
			this.length--;
		} else {
			throw new TransactionNotFoundException("Transaction " + id + " not found!");
		} 
	}

	public Transaction[] toArray() {
		Transaction[] array = new Transaction[this.length];
		TransactionsLinkedListNode curr = this.start;

		for (int i = 0; curr != null && i < this.length; i++) {
			array[i] = curr.getValue();
			curr = curr.getNextNode();
		}
		return array;
	}
}