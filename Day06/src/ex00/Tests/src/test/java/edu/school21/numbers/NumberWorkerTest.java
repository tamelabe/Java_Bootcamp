package edu.school21.numbers;

import edu.school21.numbers.NumberWorker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.engine.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    NumberWorker nw;

    @BeforeEach
    void beforeEachMethod() {
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {499, 977, 919, 199, 947})
    public void isPrimeForPrimes(int num) {
        Assertions.assertTrue(nw.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 50, 36, 24, 1000})
    public void isPrimeForNotPrimes(int num) {
        Assertions.assertFalse(nw.isPrime(num));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, -433, -11111})
    public void isPrimeForIncorrectNumbers(int num) {
        Assertions.assertThrows(IllegalNumberException.class, () -> nw.isPrime(num));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    public void digitSum(int value, int expected) {
        Assertions.assertEquals(expected, nw.digitsSum(value));
    }

}


/*
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
 */