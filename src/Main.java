import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in,"Windows-1252");
        System.out.println("divider>");
        int teiler = Integer.parseInt(scanner.nextLine());
        System.out.println("chunks>");
        int chunk = Integer.parseInt(scanner.nextLine());
        FileDivider divider = new FileDivider(teiler,chunk);

    }
}
