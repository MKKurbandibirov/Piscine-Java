import java.util.Scanner;
import java.lang.String;
import java.rmi.server.UnicastRemoteObject;

public class Program {

	public static final int UNICODE_LEN = 65536;
	public static final int RES_LEN = 10;

	public static int[][] generateArrayOfMax(String line) {
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
			// System.out.printf("%c - %d\n", maxs[i][0], maxs[i][1]);
		}
		return maxs;
	}

	public static void printResult(int[][] maxs) {
		char[][] result = new char[RES_LEN + 1][RES_LEN];
		double norm = 1.0 * maxs[0][1] / RES_LEN;

		for (int j = 0; j < RES_LEN; j++) {
			double tmp = 1.0 * maxs[j][1];
			int i;
			for (i = RES_LEN; i > 0 && (tmp - norm) >= 0; i--) {
				result[i][j] = '#';
				tmp -= norm;
			}
			result[i][j] = 'M';
		}
		for (int i = 0; i < RES_LEN + 1; i++) {
			for (int j = 0; j < RES_LEN; j++) {
				if (result[i][j] == 'M') {
					System.out.printf(" %2d ", maxs[j][1]);
				} else {
					System.out.printf(" %2c ", result[i][j]);
				}
			}
			System.out.println();
		}
		for (int i = 0; i < RES_LEN; i++) {
			System.out.printf(" %2c ", maxs[i][0]);
		}

	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String line = input.nextLine();
		int[][] maxs = generateArrayOfMax(line);
		printResult(maxs);
		input.close();
	}
}