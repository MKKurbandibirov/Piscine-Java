import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Program {
	private static final String DICT_FILE = "dictionary.txt";
	private static Set<String> dictionary;
	private static String[] dictArray;
	private static ArrayList<String> fileAEntrance;
	private static ArrayList<String> fileBEntrance;
	private static ArrayList<Integer> vectorA;
	private static ArrayList<Integer> vectorB;

	public static void readFilesToDict(String fileName, ArrayList<String> fileAOrB) {
		try (FileReader fr = new FileReader(fileName)) {
			Scanner scan = new Scanner(fr);
			while (scan.hasNext()) {
				String tmp = scan.next();
				dictionary.add(tmp);
				fileAOrB.add(tmp);
			}
			scan.close();
		} catch (IOException e) {
			System.err.println("Couldn't read a file!" + fileName);
		}
	}

	public static void fillVector(ArrayList<Integer> vectorAOrB, ArrayList<String> fileEntrance) {
		for (int i = 0; i < dictArray.length; i++) {
			Integer count = 0;
			for (String string : fileEntrance) {
				if (dictArray[i].equals(string)) {
					count++;
				}
			}
			vectorAOrB.add(count);
		}
	}

	public static Double computeSimilarity() {
		Double squareSumA;
		Double squareSumB;
		Double multiSum;
		Double sum;

		sum = 0.0;
		for (Integer val : vectorA) {
			sum += val * val;
		}
		squareSumA = Math.sqrt(sum);
		sum = 0.0;
		for (Integer val : vectorB) {
			sum += val * val;
		}
		squareSumB = Math.sqrt(sum);
		multiSum = 0.0;
		for (int i = 0; i < vectorA.size(); i++) {
			multiSum += vectorA.get(i) * vectorB.get(i);
		}
		if (squareSumA == 0.0 || squareSumB == 0.0) {
			return 0.0;
		}
		return multiSum / (squareSumA * squareSumB);
	}

	public static void writeDictToFile() {
		try (FileWriter fw = new FileWriter(DICT_FILE)) {
			for (int i = 0; i < dictArray.length; i++) {
				fw.write(dictArray[i]);
				fw.write(" ");
			}
		} catch (IOException e) {
			System.err.println("Couldn't read a file!" + DICT_FILE);
		}
	}

	public static void main(String[] args) {
		if (args.length == 2) {
			dictionary = new HashSet<>();
			fileAEntrance = new ArrayList<>();
			fileBEntrance = new ArrayList<>();
			vectorA = new ArrayList<>();
			vectorB = new ArrayList<>();
			
			readFilesToDict(args[0], fileAEntrance);
			readFilesToDict(args[1], fileBEntrance);
			dictArray = dictionary.toString().replaceAll("^\\[|\\]$", "").split(", ");
			Arrays.sort(dictArray, Comparator.naturalOrder());
			fillVector(vectorA, fileAEntrance);
			fillVector(vectorB, fileBEntrance);
			System.out.printf("Similarity = %.2f\n", computeSimilarity());
			writeDictToFile();
		} else {
			System.err.println("Illegal Argument!");
			System.exit(-1);
		}
	}
}
