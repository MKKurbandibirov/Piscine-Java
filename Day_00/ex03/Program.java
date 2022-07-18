import java.util.Scanner;
import java.lang.String;

public class Program {

	public static int GetMinOfSequence(Scanner input) {
		int min = 9;
		int tmp;

		for (int i = 0; i < 5; i++) {
			tmp = input.nextInt();
			if (min > tmp) {
				min = tmp;
			}
		}
		return min;
	}

	public static void PrintResult(long result, int weekCount) {
		if (result == 0) {
			return ;
		} else {
			int tmp;
			PrintResult(result / 10, weekCount - 1);
			tmp = (int)result % 10;
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
			tmpStr = input.next();
			if (tmpStr.equals("42") == true || weekCount > 18) {
				break;
			} else {
				tmpInt = input.nextInt();
				if (tmpInt == weekCount) {
					result = result * 10 + GetMinOfSequence(input);
				} else {
					System.err.println("IllegalArgument");
					System.exit(-1);
				}
				weekCount++;
			}
		}
		PrintResult(result, weekCount);
		input.close();
	}
}