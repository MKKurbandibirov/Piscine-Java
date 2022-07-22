public class Program {
	private static int count;
	public static void main(String[] args) {

		if(args.length == 1){
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--count")) {
				count = Integer.parseInt(tmp[1]);
				Store store = new Store();
				Producer producer = new Producer(store);
				Consumer consumer = new Consumer(store);
				Thread egg = new Thread(producer);
				Thread hen = new Thread(consumer);
				egg.start();
				hen.start();
				try {
					egg.join();
					hen.join();
				} catch (InterruptedException e) {
					e.getStackTrace();
				}
			} else {
				System.err.println("Invalid argument");
				System.exit(-1);
			}
		} else {
			System.err.println("Invalid argument");
			System.exit(-1);
		}
	}
	public static class Store {
		private int product=0;
		public synchronized void get() {
			while (product<1) {
				try {
					wait();
				}
				catch (InterruptedException e) {}
			}
			product--;
			System.out.println("Hen");
			notify();
		}
		public synchronized void put() {
			while (product>0) {
			try {
				wait();
			}
			catch (InterruptedException e) {} 
			}
			product++;
			System.out.println("Egg");
			notify();
		}
	}
	
	public static class Producer implements Runnable{
	  
		Store store;
		Producer(Store store){
			this.store=store; 
		}
		public void run(){
			for (int i = 1; i < count; i++) {
				store.put();
			}
		}
	}
	public static class Consumer implements Runnable{
		  
		 Store store;
		Consumer(Store store){
		   this.store=store; 
		}
		public void run(){
			for (int i = 1; i < count; i++) {
				store.get();
			}
		}
	}
}
