import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int value = scanner.nextInt(), i = 2;
        if (value < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }
        while (i <= Math.sqrt(value)) {
            if (value % i == 0) {
                System.out.println("false " + (i - 1));;
                System.exit(0);
            }
            ++i;
        }
        scanner.close();
        System.out.println("true " + (i - 1));

    }
}