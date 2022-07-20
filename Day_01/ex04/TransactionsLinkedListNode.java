package ex04;

public class TransactionsLinkedListNode {
	private	Transaction value;
	private	TransactionsLinkedListNode next;

	public TransactionsLinkedListNode() {
		this.value = null;
		this.next = null;
	}
	public TransactionsLinkedListNode(Transaction value) {
		this.value = value;
		this.next = null;
	}

	public Transaction getValue() {
		return this.value;
	}
	public void setValue(Transaction value) {
		this.value = value;
	}

	public TransactionsLinkedListNode getNextNode() {
		return this.next;
	}
	public void setNextNode(TransactionsLinkedListNode node) {
		this.next = node;
	}
}
