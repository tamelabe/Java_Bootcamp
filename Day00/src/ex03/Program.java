import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.next();
        int week = 1, storage = 1, count = 1;
        for (;!line.equals("42") && count < 19; ++count) {
            week = scanner.nextInt();
            if (!line.equals("Week") || week != count) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            storage = pushNumber(storage, findMin(scanner));
            line = scanner.next();
        }
        printStats(storage, count - 1);
    }

    private static void printStats(int storage, int counter) {
        int grade = 0;
        for (int i = 1; i <= counter; ++i) {
            grade = getNumber(storage, i, counter);
            System.out.printf("Week %d ", i);
            while (grade > 0) {
                System.out.printf("=");
                grade--;
            }
            System.out.println(">");
        }
    }

    private static int findMin(Scanner scanner) {
        int min = 9, current = 0;
        for (int i = 0; i < 5; ++i) {
            current = scanner.nextInt();
            if (current < min) {
                min = current;
            }
        }
        return min;
    }

    private static int pushNumber(int storage, int num) {
        storage *= 10;
        storage += num;
        return storage;
    }

    private static int getNumber(int storage, int index, int size) {
        for (int i = size; i > index; --i) {
            storage /= 10;
        }
        return storage % 10;
    }
}