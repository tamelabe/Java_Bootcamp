package edu.school21.numbers;
public class NumberWorker {
    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException("Numbers is not correct");
        }
        for (int i = 2; i <= Math.sqrt(number); ++i) {
            if (number % i == 0) { return false; }
        }
        return true;
    }

    public int digitsSum(int number) {
        number = Math.abs(number);
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}

class IllegalNumberException extends RuntimeException {
    public IllegalNumberException(String message) {
        super(message);
    }
}
