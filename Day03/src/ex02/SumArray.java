package ex02;

import java.util.Arrays;
import java.util.Random;
public class SumArray {
    private int pos;
    private int[] array;
    private int step;
    private int stepReserve;
    private int count;
    private int total;

    SumArray(int sizeArr, int steps) {
        pos = total = 0;
        count = 1;
        array = new int[sizeArr];
        step = array.length / steps;
        stepReserve = array.length - steps * step;
    }
    public int getPos() { return pos; }

    public synchronized void printSum() {
        int prevPos = pos, sum = 0, currentStep = step;
        if (stepReserve > 0) {
            currentStep++;
            stepReserve--;
        }
        int finalPos = Math.min(pos + currentStep, array.length);
        while (pos < finalPos) {
            sum += array[pos];
            ++pos;
        }
        total += sum;
        System.out.println("Thread " + count + ": from " + prevPos + " to " + (pos - 1) + " sum is " + sum);
        ++count;
    }

    public void printUnthreadSum() {
        int sum = 0;
        for (int i = 0; i < array.length; ++i) {
            sum += array[i];
        }
        System.out.println("Sum: " + sum);
    }

    public void printTotalSum() {
        System.out.println("Sum by threads: " + total);
    }

    public void fillRandom() {
        Random random = new Random();
        for (int i = 0; i < array.length; ++i)
            array[i] = random.nextInt();
    }

    public void fillOne() {
        Arrays.fill(array, 1);
    }
}