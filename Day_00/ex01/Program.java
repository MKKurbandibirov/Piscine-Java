import java.util.Scanner;

public class Program {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int value = input.nextInt();
        int i = 2;
        if (value < 0 || value == 0 || value == 1) {
            System.err.println("IllegalArgument");
            System.exit(1);
        }
        for (; i * i <= value; i++) {
            if (value % i == 0) {
                System.out.printf("%b %d\n", false, i - 1);
                input.close();
                System.exit(0);
            }
        }
        System.out.printf("%b %d\n", true, i - 1);
        input.close();
    }
}