public class Program {
	private static int count;

	public static class Egg extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < count; i++) {
				System.out.println("Egg");
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					System.err.println("Egg interrupted");
				}
			}
		}
	}

	public static class Hen extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < count; i++ ) {
				System.out.println("Hen");
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					System.err.println("Hen interrupted");
				}
			}
		}
	}

	public static void main(String[] args) {
		if(args.length == 1){
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--count")) {
				count = Integer.parseInt(tmp[1]);
				Thread egg = new Egg();
				Thread hen = new Hen();
				egg.start();
				hen.start();
				try {
					egg.join();
					hen.join();
				} catch (InterruptedException e) {
					e.getStackTrace();
				}
				for (int i = 0; i < count; i++) {
					System.out.println("Human");
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