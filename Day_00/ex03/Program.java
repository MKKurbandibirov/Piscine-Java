import java.util.Scanner;

public class Program {

	public static int getMinOfSequence(Scanner input) {
		int min = 9;
		int tmp;

		for (int i = 0; i < 5; i++) {
			if (!input.hasNextInt()) {
				System.exit(1);
			}
			tmp = input.nextInt();
			if (min > tmp) {
				min = tmp;
			}
		}
		return min;
	}

	public static void printResult(long result, int weekCount) {
		if (result == 0) {
			return ;
		} else {
			long tmp;
			printResult(result / 10, weekCount - 1);
			tmp = result % 10;
			System.out.printf("Week %d ", weekCount - 1);
			for (int i = 0; i < tmp; i++) {
				System.out.print("=");
			}
			System.out.println(">");
		}
	}

	public static void main(String[] args) {
		int weekCount = 1;
		String tmpStr;
		int tmpInt;
		Scanner input = new Scanner(System.in);
		long result = 0;

		while (true) {
			if (!input.hasNext()) {
				System.exit(1);
			}
			tmpStr = input.next();
			if (tmpStr.equals("42") == true || weekCount > 18) {
				break;
			} else {
				if (!input.hasNextInt()) {
					System.exit(1);
				}
				tmpInt = input.nextInt();
				if (tmpInt == weekCount) {
					result = result * 10 + getMinOfSequence(input);
				} else {
					System.err.println("IllegalArgument");
					System.exit(-1);
				}
				weekCount++;
			}
		}
		printResult(result, weekCount);
		input.close();
	}
}