import java.util.Scanner;

public class Program {
	public static final int SIZE = 10;
	public static final String[][] SEPTEMBER = new String[][] {
		{"SU", "MO", "TU", "WE", "TH", "FR", "SA"},
		{"", "", "1", "2", "3", "4", "5"},
		{"6", "7", "8", "9", "10", "11", "12"},
		{"13", "14", "15", "16", "17", "18", "19"},
		{"20", "21", "22", "23", "24", "25", "26"},
		{"27", "28", "29", "30", "", "", ""}
	};

	public static String[] readStudentsList(Scanner input) {
		String[] tmp = new String[SIZE];

		int i;
		for (i = 0; i < SIZE; i++) {
			if (!input.hasNext()) {
				input.close();
				System.err.println("Illegal arguments");
				System.exit(1);
			}
			tmp[i] = input.next();
			if (tmp[i].equals(".")) {
				break;
			}
			if (i > SIZE) {
				input.close();
				System.err.println("Too many arguments");
				System.exit(1);
			}
		}

		String[] students = new String[i];
		for (int j = 0; j < i; j++) {
			students[j] = tmp[j];
		}
		return students;
	}

	public static String[][] readTimeAndWeekday(Scanner input) {
		String[][] tmp = new String[SIZE][2];

		int i;
		for (i = 0; i < SIZE; i++) {
			if (!input.hasNext()) {
				input.close();
				System.err.println("Illegal arguments");
				System.exit(1);
			}
			tmp[i][0] = input.next();
			if (tmp[i][0].equals(".")) {
				break;
			}
			if (!input.hasNext()) {
				input.close();
				System.err.println("Illegal arguments");
				System.exit(1);
			}
			tmp[i][1] = input.next();
			if (i > SIZE) {
				input.close();
				System.err.println("Too many arguments");
				System.exit(1);
			}
		}

		String[][] times = new String[i][2];
		for (int j = 0; j < i; j++) {
			times[j][0] = tmp[j][0];
			times[j][1] = tmp[j][1];
		}
		return times;
	}

	public static int getWeekDay(String str) {
		for (int i = 0; i < SEPTEMBER[0].length; i++) {
			if (str.equals(SEPTEMBER[0][i])) {
				return i + 1;
			}
		}
		return -1;
	}

	public static void sortByWeekday(String[][] times) {
		for (int i = 0; i < times.length - 1; i++) {
			for (int j = 0; j < times.length - i - 1; j++) {
				if (getWeekDay(times[j][1]) > getWeekDay(times[j + 1][1])) {
					String tmp1 = times[j][0], tmp2 = times[j][1];
					times[j][0] = times[j + 1][0];
					times[j][1] = times[j + 1][1];
					times[j + 1][0] = tmp1;
					times[j + 1][1] = tmp2;
				}
			}
		}
	}

	public static int getDayTime(String str) {
		char[] tmp = str.toCharArray();

		return '0' - tmp[0];
	}

	public static void sortByDayTime(String[][] times) {
		for (int i = 0; i < times.length - 1; i++) {
			for (int j = 0; j < times.length - i - 1; j++) {
				if ((getDayTime(times[j][0]) < getDayTime(times[j + 1][0]))
					&& (getWeekDay(times[j][1]) == getWeekDay(times[j + 1][1]))) {
					String tmp1 = times[j][0], tmp2 = times[j][1];
					times[j][0] = times[j + 1][0];
					times[j][1] = times[j + 1][1];
					times[j + 1][0] = tmp1;
					times[j + 1][1] = tmp2;
				}
			}
		}
	}

	public static int getDate(String str) {
		char[] tmp = str.toCharArray();
		int date = tmp[0] - '0';
		if (tmp.length == 2) {
			date = date * 10 + (tmp[1] - '0');
		}
		return date;
	}

	public static void sortByDates(String[][] firstRow) {
		for (int i = 0; i < firstRow.length - 1; i++) {
			for (int j = 0; j < firstRow.length - i - 1; j++) {
				if (getDate(firstRow[j][2]) > getDate(firstRow[j + 1][2])) {
					String t1 = firstRow[j][0], t2 = firstRow[j][1], t3 = firstRow[j][2];
					firstRow[j][0] = firstRow[j + 1][0];
					firstRow[j][1] = firstRow[j + 1][1];
					firstRow[j][2] = firstRow[j + 1][2];
					firstRow[j + 1][0] = t1;
					firstRow[j + 1][1] = t2;
					firstRow[j + 1][2] = t3;
				}
			}
		}
	}

	public static String[][] getFirstRow(String[][] times) {
		int columns = 0;
		for (int i = 0; i < times.length; i++) {
			int j;
			for (j = 0; j < SEPTEMBER[0].length; j++) {
				if (SEPTEMBER[0][j].equals(times[i][1])) {
					break;
				}
			}
			for (int k = 1; k < SEPTEMBER.length; k++) {
				if (SEPTEMBER[k][j] != "") {
					columns++;
				}
			}
		}
		String[][] firstRow = new String[columns][3];
		for (int i = 0, l = 0; i < times.length; i++) {
			int j = 0;
			for (; j < 7; j++) {
				if (times[i][1].equals(SEPTEMBER[0][j])) {
					break;
				}
			}
			for (int k = 1; k < 6; k++) {
				if (!SEPTEMBER[k][j].equals("")) {
					firstRow[l][0] = times[i][0] + ":00";
					firstRow[l][1] = times[i][1];
					firstRow[l][2] = SEPTEMBER[k][j];
					l++;
				}
			}
		}
		sortByDates(firstRow);
		return firstRow;
	}

	public static String[][] createResultTable(String[] students, String[][] firstRow, Scanner input) {
		String [][] resultTable = new String[students.length + 1][firstRow.length + 1];
		resultTable[0][0] = "";
		for (int i = 0; i < students.length; i++) {
			resultTable[i + 1][0] = students[i];
		}
		String name, time, date;
		for (int i = 0; i < firstRow.length; i++) {
			resultTable[0][i + 1] = firstRow[i][0] + " " + firstRow[i][1] + " " + firstRow[i][2];
		}
		while (true) {
			if (!input.hasNext()) {
				input.close();
				System.err.println("Illegal arguments");
				System.exit(1);
			}
			name = input.next();
			if (name.equals(".")) {
				break;
			}
			if (!input.hasNext()) {
				input.close();
				System.err.println("Illegal arguments");
				System.exit(1);
			}
			time = input.next();
			if (!input.hasNext()) {
				input.close();
				System.err.println("Illegal arguments");
				System.exit(1);
			}
			date = input.next();
			int i, j;
			for (i = 1; i < resultTable.length; i++) {
				if (name.equals(resultTable[i][0])) {
					break;
				}
			}
			String tmp = time + ":00";
			for (j = 1; j < firstRow.length; j++) {
				if (firstRow[j - 1][0].equals(tmp) && firstRow[j - 1][2].equals(date)) {
					break;
				}
			}
			String here;
			if (!input.hasNext()) {
				input.close();
				System.err.println("Illegal arguments");
				System.exit(1);
			}
			here = input.next();
			if (i != resultTable.length || j != firstRow.length)
				resultTable[i][j] = here.equals("HERE") ? "1" : "-1";
		}
		return resultTable;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String[] students = readStudentsList(input);
		String[][] times = readTimeAndWeekday(input);

		sortByWeekday(times);
		sortByDayTime(times);		
		String[][] firstRow = getFirstRow(times);
		String[][] resultTable = createResultTable(students, firstRow, input);
		for (int i = 0; i < resultTable.length; i++) {
			for (int j = 0; j < resultTable[i].length; j++) {
				if (i == 0 && j > 0) {
					System.out.printf("%4s%3s%3s|", firstRow[j - 1][0], firstRow[j - 1][1], firstRow[j - 1][2]);
				} else if (i == 0 && j == 0) {
					System.out.printf("%10s", "");
				} else if (j == 0 && i > 0) {
					System.out.printf("%10s", resultTable[i][j]);
				} else if (i > 0 && j > 0) {
					System.out.printf("%10s|", (resultTable[i][j] != null) ? resultTable[i][j] : "");
				}
			}
			System.out.println();
		}
		input.close();
	}
}
