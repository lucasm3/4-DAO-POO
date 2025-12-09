
import Utils.MenuUtil;
import Modules.User.UsuarioPetController;

public class Menu {

    private AppView app;
    private UsuarioPetController userController;

    public Menu(AppView app) {
        this.app = app;
        this.userController = app.getUserController();
    }

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Bichinho Virtual ===");
            System.out.println("1 - Criar usuário");
            System.out.println("2 - Entrar");
            System.out.println("3 - Adotar pet (usuário logado)");
            System.out.println("4 - Alimentar pet");
            System.out.println("5 - Limpar pet");
            System.out.println("6 - Brincar com pet");
            System.out.println("7 - Sair");
            System.out.print("Escolha: ");
            int op = MenuUtil.lerOpcao();
            switch (op) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = MenuUtil.lerTexto();
                    System.out.print("Email: ");
                    String email = MenuUtil.lerTexto();
                    System.out.print("Senha: ");
                    String senha = MenuUtil.lerTexto();
                    boolean ok = userController.inserirUsuario(nome, email, senha);
                    System.out.println(ok ? "Usuário criado." : "Falha ao criar usuário.");
                    break;
                case 2:
                    System.out.print("Email: ");
                    String e = MenuUtil.lerTexto();
                    System.out.print("Senha: ");
                    String s = MenuUtil.lerTexto();
                    userController.entrar(e, s);
                    break;
                case 3:
                    System.out.print("Nome do pet: ");
                    String np = MenuUtil.lerTexto();
                    System.out.print("Espécie: ");
                    String es = MenuUtil.lerTexto();
                    userController.adotarPet(np, es);
                    break;
                case 4:
                    userController.alimentarPet();
                    break;
                case 5:
                    userController.limparPet();
                    break;
                case 6:
                    userController.brincarComPet();
                    break;
                case 7:
                    running = false;
                    System.out.println("Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
