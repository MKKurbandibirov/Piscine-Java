import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Program {
	private static String URLS_FILE = "files_urls.txt";
	private static Map<Integer, String> filesURL;
	private static int threadsCount;

	public static void readFileFromURL(Integer number, String url) {
		try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
		FileOutputStream fileOS = new FileOutputStream(number.toString())) {
			byte data[] = new byte[1024];
			int byteContent;
			while ((byteContent = inputStream.read(data, 0, 1024)) != -1) {
				fileOS.write(data, 0, byteContent);
			}
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

	private static void readURLFile() {
		filesURL = new HashMap<>();
		try (FileReader fr = new FileReader(URLS_FILE)) {
			Scanner scan = new Scanner(fr);
			while (scan.hasNext()) {
				if (!scan.hasNextInt()) {
					throw new IOException();
				}
				Integer number = scan.nextInt();
				if (!scan.hasNext()) {
					throw new IOException();
				}
				String url = scan.next();
				filesURL.put(number, url);
			}
			scan.close();
		} catch (IOException e) {
			System.err.println("Couldn't read " + URLS_FILE);
		}
	}

	public static void main(String[] args) {
		if(args.length == 1){
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--threadsCount")) {
				threadsCount = Integer.parseInt(tmp[1]);
			} else {
				System.err.println("Invalid argument");
				System.exit(-1);
			}
			readURLFile();
			ExecutorService service = Executors.newFixedThreadPool(threadsCount);
			for (Map.Entry<Integer, String> entry : filesURL.entrySet()) {
				service.submit(() -> {
					readFileFromURL(entry.getKey(), entry.getValue());
				});
			}
			service.shutdown();
			

			
		} else {
			System.err.println("Invalid argument");
			System.exit(-1);
		}
	}
}
