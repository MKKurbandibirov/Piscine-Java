import java.io.IOException;
import java.util.Scanner;

public class Program {

	private static String currentFolder;
	public static void main(String[] args) {
		if (args.length == 1) {
			String[] tmp = args[0].split("=");
			if (tmp[0].equals("--current-folder")) {
				currentFolder = tmp[1];
				FileManager fileManager = new FileManager(currentFolder);
				try (Scanner scan = new Scanner(System.in)) {
					while (true) {
						if (!scan.hasNext()) {
							System.err.println("Illegal Argument!");
						}
						String cmd = scan.next();
						if (cmd.equals("ls")) {
							fileManager.listDirectory();
						} else if (cmd.equals("cd")) {
							if (!scan.hasNext()) {
								System.err.println("Illegal Argument!");
							}
							String folder = scan.next();
							fileManager.changeDirectory(folder);
						} else if (cmd.equals("exit")) {
							System.exit(0);
						} else if (cmd.equals("mv")) {
							if (!scan.hasNext()) {
								System.err.println("Illegal Argument!");
							}
							String what = scan.next();
							if (!scan.hasNext()) {
								System.err.println("Illegal Argument!");
							}
							String where = scan.next();
							fileManager.move(what, where);
						}
					}
				} catch (RuntimeException | IOException e) {
					System.err.println("Couldn't read");
					System.exit(-1);
				}
			} else {
				System.err.println("Illegal Argument!");
				System.exit(-1);
			}
		} else {
			System.err.println("Illegal Argument!");
			System.exit(-1);
		}
	}
}
