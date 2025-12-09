package Modules.User;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

public class UsuarioModel {

    private int id;
    private String nome;
    private String email;
    private String senhaCodificada;
    private boolean logado = false;
    protected Modules.VirtualPet.VirtualPetModel petVirtual;

    public UsuarioModel(int id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaCodificada = Base64.getEncoder().encodeToString(senha.getBytes(StandardCharsets.UTF_8));
        this.logado = false;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenhaCodificada() { return senhaCodificada; }

    public boolean entrar() {
        this.logado = true;
        return true;
    }

    public void sair() {
        this.logado = false;
    }

    public boolean isLogado() { return logado; }

    public void adotarPet(Modules.VirtualPet.VirtualPetModel pet) {
        this.petVirtual = pet;
    }

    public Modules.VirtualPet.VirtualPetModel getPet() {
        return petVirtual;
    }

    @Override
    public String toString() {
        return id + " - " + nome + " (" + email + ")";
    }
}
