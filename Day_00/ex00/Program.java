public class Program {

    public static void main(String[] args) {
        int value = 479598;
        int result = 0;

        while (value > 0) {
            result += value % 10;
            value /= 10;
        }
        System.out.println(result);
    }
}