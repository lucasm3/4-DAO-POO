import Utils.MenuUtil;

public class Main {
    public static void main(String[] args) {
        AppView app = new AppView();
        Menu menu = new Menu(app);
        menu.run();
    }
}
