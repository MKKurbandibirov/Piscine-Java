package ex05;

public class Program {
	public static void main(String[] args) {
		Menu.Mode mode = null;

		if (args[0].equals("--profile=dev")) {
			mode = Menu.Mode.DEV;
		} else if (args[0].equals("--profile=production")) {
			mode = Menu.Mode.PRO;
		} else {
			System.err.println("Invalide mode");
			System.exit(-1);
		}
		Menu menu = new Menu(mode);
		menu.mainLoop();
	}
}

