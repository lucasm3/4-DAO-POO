package Utils;


import java.util.Scanner;

public class MenuUtil {

    private static Scanner scanner = new Scanner(System.in);

    public static int lerOpcao() {
        try {
            String line = scanner.nextLine();
            return Integer.parseInt(line.trim());
        } catch (Exception e) {
            return -1;
        }
    }

    public static String lerTexto() {
        return scanner.nextLine().trim();
    }
}
