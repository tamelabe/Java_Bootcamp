import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt(), counter = 0;
        while (value != 42) {
            value = sumElements(value);
            if (isPrimeNumber(value)) {
                ++counter;
            }
            value = scanner.nextInt();
        }
        scanner.close();
        System.out.println("Count of coffee-request – " + counter);
    }

    private static int sumElements(int num) {
        int res = 0;
        for (int i = 0; num > 0; ++i) {
            res += num % 10;
            num /= 10;
        }
        return res;
    }

    private static boolean isPrimeNumber(int value) {
        if (value < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        int i = 2;
        while (i <= Math.sqrt(value)) {
            if (value % i == 0) {
                return false;
            }
            ++i;
        }
        return true;
    }
}