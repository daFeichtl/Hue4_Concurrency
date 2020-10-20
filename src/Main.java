import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in,"Windows-1252");
        System.out.println("chunks>");
        int teiler = Integer.parseInt(scanner.nextLine());
        System.out.println("divider>");
        int chunk = Integer.parseInt(scanner.nextLine());
        FileDivider divider = new FileDivider(teiler,chunk);
        divider.calc();
        Thread.sleep(100);
        System.out.println("BSP2.:");
        System.out.println("numbers>");
        int numbs = Integer.parseInt(scanner.nextLine());
        new Gausser(numbs).calc();
        JavaStreamsTester.test();
    }
}
