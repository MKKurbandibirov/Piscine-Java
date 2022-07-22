public class Program {
	private static int count;
	public static class Printer {
		public static boolean isPrinted = true;
		public synchronized void print(String message) {
			if (!isPrinted) {
				try {
					isPrinted = true;
					wait();
				} catch (InterruptedException e) {
					e.getStackTrace();
				}
			}
			notify();
			isPrinted = false;
		}
	}

	public static class Egg extends Thread {
		private String message;
		private Printer printer;

		public Egg(String message, Printer printer) {
			this.message = message;
			this.printer = printer;
		}

		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				printer.print(message);
			}
		}
	}

	public static class Hen extends Thread {
		private String message;
		private Printer printer;

		public Hen(String message, Printer printer) {
			this.message = message;
			this.printer = printer;
		}

		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				printer.print(message);
			}

		}
	}

	public static void main(String[] args) {
		if(args.length == 1){
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--count")) {
				count = Integer.parseInt(tmp[1]);
				Printer printer1 = new Printer();
				Thread hen = new Hen("Hen", printer1);
				Thread egg = new Egg("Egg", printer1);
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
}
