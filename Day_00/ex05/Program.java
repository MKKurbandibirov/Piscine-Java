import java.util.Scanner;
import java.lang.String;

public class Program {

	public static final int SIZE = 10;
	public static final int WEEKS = 5;
	public static final int WEEK_DAYS = 7;
	public static void main(String[] args) {
		String[] students = new String[SIZE];
		String[][] timeAndDays = new String[SIZE][2];
		Scanner input = new Scanner(System.in);

		for (int i = 0; i < SIZE; i++) {
			students[i] = input.next();
			if (students[i].equals(".")) {
				break;
			}
			if (students[i].length() > 10) {
				System.err.println("Illegal Argument");
				i--;
			}
		}
		for (int i = 0; i < SIZE; i++) {
			timeAndDays[i][0] = input.next();
			if (timeAndDays[i][0].equals(".")) {
				break;
			}
			timeAndDays[i][1] = input.next();
		}
		String[][] september = new String[][] {
			{"", "", "TU", "WE", "TH", "FR", "SA"},
			{"SU", "MO", "TU", "WE", "TH", "FR", "SA"},
			{"SU", "MO", "TU", "WE", "TH", "FR", "SA"},
			{"SU", "MO", "TU", "WE", "TH", "FR", "SA"},
			{"SU", "MO", "TU", "WE", "", "", ""}
		};

		// while (true) {

		// }

		input.close();
	}
}
