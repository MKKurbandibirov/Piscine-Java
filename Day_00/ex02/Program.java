import java.util.Scanner;

public class Program {

	public static int CountSum(int value) {
		int result = 0;

        while (value > 0) {
            result += value % 10;
            value /= 10;
        }
		return result;
	}

	public static boolean IsPrime(int value) {
        int sqrt = 1;
        int i = 2;

        while (sqrt * sqrt < value) {
            sqrt++;
        }
        for (; i < sqrt; i++) {
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
			tmp = input.nextInt();
			if (tmp == 42) {
				break;
			}
			if (IsPrime(CountSum(tmp))) {
				count++;
			}
		}
		System.out.printf("Count of coffee-request - %d\n", count);
	}
}