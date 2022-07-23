import java.util.concurrent.ThreadLocalRandom;

public class Program {
	private static int[] array;
	private static int arraySize;
	private static int threadsCount;
	private static int result;
	private static int MAX_NUM = 1000;
	private static int MIN_NUM = -1000;
	private static SubThread[] threads;

	
	public static class SubThread extends Thread {
		private int start;
		private int end;
		private int id;
		
		public SubThread (int id, int start, int end) {
			this.id = id;
			this.start = start;
			this.end = end;
		}

		@Override
		public void run() {
			int sum = 0;
			for (int i = start; i <= end; i++) {
				sum += array[i];
			}
			System.out.printf("Thread %d: from %d to %d sum is %d\n", this.id, this.start, this.end, sum);
			result += sum;
		}
	}

	public static void generateRandomArray() {
		array = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			array[i] = ThreadLocalRandom.current().nextInt(MIN_NUM, MAX_NUM + 1);
		}
	}

	public static int countSumStandard() {
		int sum = 0;
		for (int i = 0; i < arraySize; i++) {
			sum += array[i];
		}
		return sum;
	}

	public static void subThreadsCreate() {
		threads = new SubThread[threadsCount];
		int range = 0;
		if (threadsCount < arraySize / 2) {
			int tmp = arraySize;
			while (tmp % threadsCount != 0) {
				tmp++;
			}
			range = tmp / threadsCount;
			for (int i = 0, j = 0; i < arraySize; j++) {
				if (i + range > arraySize) {
					range = arraySize - i;
				}
				threads[j] = new SubThread(j + 1, i, i + range - 1);
				i = i + range;
			}
		} else {
			 int j = 0;
			 for (; j < threadsCount - 1; j++) {
				threads[j] = new SubThread(j + 1, j, j);
			}
			threads[j] = new SubThread(j + 1, j, arraySize - 1);
		}
	}

	public static void main(String[] args) {
		if(args.length == 2){
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--arraySize")) {
				arraySize = Integer.parseInt(tmp[1]);
			} else {
				System.err.println("Invalid argument");
				System.exit(-1);
			}
			tmp = args[1].split("=");
			if (tmp[0].equals("--threadsCount")) {
				threadsCount = Integer.parseInt(tmp[1]);
			} else {
				System.err.println("Invalid argument");
				System.exit(-1);
			}
			generateRandomArray();
			System.out.printf("Sum: %d\n", countSumStandard());
			subThreadsCreate();
			for (int i = 0; i < threadsCount; i++) {
				threads[i].start();
			}
			for (int i = 0; i < threadsCount; i++) {
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.getStackTrace();
				}
			}
			System.out.printf("Sum by threads %d\n", result);
		} else {
			System.err.println("Invalid argument");
			System.exit(-1);
		}
	}
}
