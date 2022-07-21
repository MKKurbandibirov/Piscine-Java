public class Program {
	public static void main(String[] args) {
		if (args.length == 1) {
			String[] argv = args[0].split("=");
		} else {
			System.err.println("Illegal Argument!");
			System.exit(-1);
		}
	}
}
