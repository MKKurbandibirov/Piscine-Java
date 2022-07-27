package edu.school21.numbers;

public class NumberWorker {

    class IllegalNumberException extends Exception {
        public IllegalNumberException(String message) {
            super(message);
        }
    }

    public boolean isPrime(int number) throws IllegalNumberException {
        long i = 2;

        if (number < 0 || number == 0 || number == 1) {
            throw new IllegalNumberException("Illegal number - " + number);
        }
        for (; i * i < (long) number; i++) {
            if ((long)number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static int digitSum(int number) {
        int result = 0;

        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}