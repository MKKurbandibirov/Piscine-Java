package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;

public class NumberWorkerTest {

	@ParameterizedTest
	@ValueSource(ints = {2, 199, Integer.MAX_VALUE})
	public void isPrimeForPrimes(int number) throws IllegalNumberException{
		assertTrue(NumberWorker.isPrime(number));
	}

	@ParameterizedTest
	@ValueSource(ints = {14, 10023, 10000002})
	public void isPrimeForNotPrime(int number) throws IllegalNumberException {
		assertFalse(NumberWorker.isPrime(number));
	}

	@ParameterizedTest
	@ValueSource(ints = {-11, 0, 1})
	public void isPrimeForIncorrectNumbers(int number) {
		Throwable thrown = assertThrows(IllegalNumberException.class, () -> {
			NumberWorker.isPrime(number);
		});
		assertNotNull(thrown);
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/data.csv")
	public void checkDigitsSum(int number, int sum) {
		assertTrue(NumberWorker.digitSum(number) == sum);
	}
}