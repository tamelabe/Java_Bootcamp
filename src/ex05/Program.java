import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();

        int symbols[][] = new int[65536][2];
        for (char symbol : line.toCharArray()) {
            if (symbol == ' ') continue;
            symbols[symbol][0] = symbol;
            symbols[symbol][1]++;
        }
        symbols = createsymbolsArray(symbols);
        createGraph(symbols);
    }

    private static void createGraph(int[][] symbols) {
        int max_val = symbols[0][1];
        boolean flag = false, is_printed = true;
        float step = (float)max_val / 10;
        step = (int)step > 1 ? step : 1;
        int length = findLength(symbols);
        if (length == 10) {
            System.out.printf(" " + "%3" + "d\n", max_val);
        }
        for (float i = length; i > 0; --i) {
            for (int j = 0; j < 10; ++j) {
                if (symbols[j][1] == 0) break;
                if (symbols[j][1] / step >= i) {
                    System.out.print("   #");
                    flag = true;
                } else if (symbols[j][1] / step >= i - 1) {
                    System.out.printf(" " + "%3" + "d", symbols[j][1]);
                    flag = true;
                }
            }
            if (flag == true) {
                System.out.println();
                flag = false;
            }
        }
        for (int i = 0; i < 10; i++) {
            if (symbols[i][0] == 0) break;
            System.out.print("   " + (char)symbols[i][0]);
        }
        System.out.println();
    }

    private static int findLength(int[][] symbols) {
        int len = 0;
        for (int i = 0; i < 10; ++i) {
            if (symbols[i][1] != 0)
                len++;
        }
        return len;
    }

    private static int[][] createsymbolsArray(int[][] symbols) {
        int rows = symbols.length;
        int cols = symbols[0].length;
        for (int i = 0; i < rows - 1; i++) {
            for (int j = 0; j < rows - i - 1; j++) {
                if (symbols[j][1] < symbols[j + 1][1]) {
                    int[] temp = symbols[j];
                    symbols[j] = symbols[j + 1];
                    symbols[j + 1] = temp;
                }
            }
        }
        return symbols;
    }
}