import java.util.Scanner;
import java.lang.String;
import java.rmi.server.UnicastRemoteObject;

public class Program {

	public static final int UNICODE_LEN = 65536;
	public static final int RES_LEN = 10;

	public static void generateArrayOfMax(String line) {
		int[] map = new int[UNICODE_LEN];
		char[] tmp = line.toCharArray();
		
		for (int i = 0; i < line.length(); i++) {
			map[(int)tmp[i]]++;
		}
		int uniqLen = 0;
		for (int i = 0; i < UNICODE_LEN; i++) {
			if (map[i] != 0) {
				uniqLen++;
			}
		}
		int[][] uniq = new int[uniqLen][2];
		for (int i = 0, j = 0; i < UNICODE_LEN && j < uniqLen; i++) {
			if (map[i] != 0) {
				uniq[j][0] = i;
				uniq[j][1] = map[i];
				j++;
			}
		}

		int[][] maxs = new int[RES_LEN][2];
		for (int i = 0; i < RES_LEN; i++) {
			int max = uniq[0][1];
			int j = 0, tmpInd = 0;
			for (;j < uniqLen; j++) {
				if (max < uniq[j][1]) {
					max = uniq[j][1];
					tmpInd = j;
				}
			}
			uniq[tmpInd][1] = -1;
			maxs[i][0] = uniq[tmpInd][0];
			maxs[i][1] = max;
		}
		if (uniqLen < 10) {
			printResult(maxs, uniqLen);
		} else {
			printResult(maxs, RES_LEN);
		}
	}

	public static void printResult(int[][] maxs, int len) {
		char[][] result = new char[RES_LEN + 1][len];

		for (int j = 0; j < len; j++) {
			double tmp = 1.0 * maxs[j][1] / maxs[0][1] * 10;
			int i;
			for (i = RES_LEN; i > 0 && tmp >= RES_LEN - i + 1; i--) {
				result[i][j] = '#';
			}
			result[i][j] = 'M';
		}
		for (int i = 0; i < RES_LEN + 1; i++) {
			for (int j = 0; j < len; j++) {
				if (result[i][j] == 'M') {
					System.out.printf(" %2d ", maxs[j][1]);
				} else {
					System.out.printf(" %2c ", result[i][j]);
				}
			}
			System.out.println();
		}
		for (int i = 0; i < len; i++) {
			System.out.printf(" %2c ", maxs[i][0]);
		}

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String line;

		if (!input.hasNextLine()) {
			line = input.nextLine();
			if (line != null && line.length() > 0) {
				generateArrayOfMax(line);
			}
		}
		input.close();
	}
}