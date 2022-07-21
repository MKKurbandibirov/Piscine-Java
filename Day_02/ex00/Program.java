import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Program {

	private static Map<String, String[]> signatureMap;
	private static final String SIGNATURE_PATH = "signature.txt";
	private static final String RESULT_PATH = "result.txt";
	private static LinkedList<String> resultList;
	private static int maxLength = 0;

	public static void setSignatureMap() {
		try (FileInputStream fileInput = new FileInputStream(SIGNATURE_PATH)) {
			signatureMap = new HashMap<>();
			int length = (int)new File(SIGNATURE_PATH).length();
			byte[] bytes = new byte[length];
			fileInput.read(bytes);
			String signatureLines = new String(bytes);
			// System.out.println(signatureLines);
			String[] splitedLines = signatureLines.split("\n");
			// for (String string : splitedLines) {
			// 	System.out.println(string);
			// }
			for (String line : splitedLines) {
				String[] keyVal = line.split(",");
				// System.out.printf("%s\t%s", keyVal[0], keyVal[1]);
				// System.out.println();
				String[] values = keyVal[1].trim().split(" ");
				// System.out.printf("%s\t", keyVal[0]);
				// for (String string : values) {
				// 	System.out.printf(" %s |", string);
				// }
				// System.out.println();
				signatureMap.put(keyVal[0], values); 
			}
		} catch (IOException e) {
			System.err.println("File not found!");
		}
	}

	public static void setResultList() {
		resultList = new LinkedList<>();
		Scanner input = new Scanner(System.in);
		String filePath;
		while (true) {
			if (!input.hasNextLine()) {
				input.close();
			}
			filePath = input.nextLine();
			if (filePath.equals("42")) {
				break;
			}
			try (FileInputStream fileInput = new FileInputStream(filePath)) {
				byte[] bytes = new byte[maxLength];
				for (int i = 0; i < maxLength; i++) {
					bytes[i] = (byte)fileInput.read();
				}
				String[] strBytes = new String[maxLength];
				for (int i = 0; i < maxLength; i++) {
					strBytes[i] = Integer.toHexString(bytes[i] & 0xff);
					if (strBytes[i].length() == 1) {
						strBytes[i] = "0" + strBytes[i].toUpperCase();
					} else {
						strBytes[i] = strBytes[i].toUpperCase();
					}
				}
				boolean check = false;
				for (Map.Entry<String, String[]> entry : signatureMap.entrySet()) {
					check = true;
					for (int i = 0; i < entry.getValue().length; i++) {
						if (!entry.getValue()[i].equals(strBytes[i])) {
							check = false;
							break;
						} 
					}
					if (check) {
						System.out.println("PROCESSED");
						resultList.add(entry.getKey());
						break;
					}
				}
				if (!check) {
					System.out.println("UNDEFINED");
				}
			} catch (IOException e) {
				System.err.println("File not found!");
			}
		}
		input.close();
	}

	public static void main(String[] args) {
		setSignatureMap();
		for (String[] values : signatureMap.values()) {
			if (values.length > maxLength) {
				maxLength = values.length;
			}
		}
		setResultList();
		try (FileOutputStream fileInput = new FileOutputStream(RESULT_PATH)) {
			for (String string : resultList) {
				byte[] bytes = string.getBytes();
				fileInput.write(bytes, 0, bytes.length);
				byte[] nl = "\n".getBytes();
				fileInput.write(nl);
			}
		} catch (IOException e) {
			System.err.println("Don't have access");
		}
	}
}
