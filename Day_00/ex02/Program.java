import java.util.Scanner;

public class Program {

	public static int countSum(int value) {
		int result = 0;

        while (value > 0) {
            result += value % 10;
            value /= 10;
        }
		return result;
	}

	public static boolean isPrime(int value) {
        int i = 2;

        for (; i * i < value; i++) {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
	}
	public static void main(String[] args) {
		int count = 0;
		Scanner input = new Scanner(System.in);
		int tmp;

		while (true) {
			if (!input.hasNextInt()) {
				System.exit(1);
			}
			tmp = input.nextInt();
			if (tmp == 42) {
				break;
			}
			if (isPrime(countSum(tmp))) {
				count++;
			}
		}
		System.out.printf("Count of coffee-request - %d\n", count);
		input.close();
	}
}