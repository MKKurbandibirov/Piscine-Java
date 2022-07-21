import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;;

public class FileManager {
	private String currentFolder;

	public FileManager(String folder) {
		if (!Files.exists(Paths.get(folder)) || !Files.isDirectory(Paths.get(folder))) {
			System.err.println(folder + "is not exists!");
			System.exit(-1);
		}
		System.out.println(folder);
		setCurrentFolder(folder);
	}

	public String getCurrenFolder() {
		return this.currentFolder;
	}
	public void setCurrentFolder(String folder) {
		this.currentFolder = folder;
	}

	public long getFolderSize(File folder) {
		long size = 0;
		for (File file : folder.listFiles()) {
			if (file.isFile()) {
				size += file.length();
			} else if (file.isDirectory()) {
				size += getFolderSize(file);
			}
		}
		return size;
	}

	public void listDirectory() throws IOException {
		File folder = new File(this.currentFolder);
		for (File file : folder.listFiles()) {
			long fileSize = 0;
			Path path = file.toPath();
			if (Files.isRegularFile(path)) {
				fileSize = Files.size(path);
			} else if (Files.isDirectory(path)) {
				fileSize = getFolderSize(file);
			}
			if (fileSize < 1024) {
				System.out.printf("%s %d KB\n", file.getName(), fileSize / 1024);
			} else {
				System.out.printf("%s %d KB\n", file.getName(), fileSize / 1024);
			}
		}
	}

	public void changeDirectory(String folder) {
		String newCurrDir = this.currentFolder + "/" + folder;
		Path tmp = Paths.get(newCurrDir);
		if (Files.exists(tmp) && Files.isDirectory(tmp)) {
			this.currentFolder = newCurrDir;
			System.out.println(this.getCurrenFolder());
		} else {
			System.err.println("Invalid folder - " + folder);
		}
	}

	public void move(String what, String where) throws IOException {
		Path pathWhat = Paths.get(this.currentFolder + "/" + what);
		Path pathWhere = Paths.get(this.currentFolder + "/" + where);

		if (Files.isRegularFile(pathWhat)) {
			if (Files.isDirectory(pathWhere)) {
				pathWhere = Paths.get(pathWhere + "/" + pathWhat);
			}
			Files.move(pathWhat, pathWhere, REPLACE_EXISTING);
		}
	}
}
