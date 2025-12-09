package Modules.User;


import Enums.EspecieEnum;
import Modules.User.Errors.UsuariosErros;
import Modules.User.Exceptions.UsuarioException;
import Modules.VirtualPet.Errors.VirtualPetErros;
import Modules.VirtualPet.Exceptions.VirtualPetException;
import Modules.VirtualPet.VirtualPetController;
import Modules.VirtualPet.VirtualPetModel;
import DAO.DAO;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

/**
 * Controller de usuários que manipula DAO de UsuarioModel e delega ações para VirtualPetController.
 */
public class UsuarioPetController {

    private DAO<UsuarioModel> dao;
    private VirtualPetController petController;
    private UsuarioModel usuarioLogado;
    private int idAtual = 1;

    public UsuarioPetController(DAO<UsuarioModel> dao, VirtualPetController petController) {
        this.dao = dao;
        this.petController = petController;
    }

    public boolean possuiPet() {
        return petController.existePet();
    }

    public void sair() {
        this.usuarioLogado = null;
    }

    public boolean verificarUsuarioExistente(String email) {
        List<UsuarioModel> todos = dao.readAll();
        for (UsuarioModel u : todos) {
            if (u.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    public boolean inserirUsuario(String nome, String email, String senha) {
        try {
            if (!email.contains("@")) {
                throw new UsuarioException(UsuariosErros.emailInvalido);
            }
            if (senha.length() < 8) {
                throw new UsuarioException(UsuariosErros.senhaFraca);
            }
            if (verificarUsuarioExistente(email)) {
                throw new UsuarioException(UsuariosErros.emailJaCadastrado);
            }
            UsuarioModel novo = new UsuarioModel(idAtual++, nome, email, senha);
            dao.create(novo);
            return true;
        } catch (UsuarioException ex) {
            System.out.println("Erro ao inserir usuário: " + ex.getMessage());
            return false;
        }
    }

    public boolean entrar(String emailInformado, String senhaInformada) {
        try {
            List<UsuarioModel> todos = dao.readAll();
            boolean existe = false;
            for (UsuarioModel u : todos) {
                if (u.getEmail().equalsIgnoreCase(emailInformado)) {
                    existe = true;
                    // compara senha codificada
                    String cod = Base64.getEncoder().encodeToString(senhaInformada.getBytes(StandardCharsets.UTF_8));
                    if (u.getSenhaCodificada().equals(cod)) {
                        u.entrar();
                        this.usuarioLogado = u;
                        System.out.println("Login OK: " + u.getNome());
                        return true;
                    } else {
                        throw new UsuarioException(UsuariosErros.verifiqueSuasCredenciais);
                    }
                }
            }
            if (!existe) throw new UsuarioException(UsuariosErros.verifiqueSuasCredenciais);
        } catch (UsuarioException ex) {
            System.out.println("Erro ao entrar: " + ex.getMessage());
        }
        return false;
    }

    public boolean adotarPet(String nomePet, String especie) throws UsuariosException {
        try {
            if (usuarioLogado == null) {
                System.out.println("Nenhum usuário logado.");
                return false;
            }
            if (possuiPet()) {
                throw new VirtualPetException(VirtualPetErros.jaPossuiPet);
            }
            petController.adotar(nomePet, EspecieEnum.valueOf(especie));
            // vincula ao usuário
            usuarioLogado.adotarPet(new VirtualPetModel(nomePet, especie));
            return true;
        } catch (VirtualPetException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPet() throws UsuarioException, VirtualPetException {
        throw new VirtualPetException(VirtualPetErros.naoPodeAbandonar);
    }

    public void alimentarPet() {
        petController.alimentarPet();
    }

    public void limparPet() {
        petController.limparPet();
    }

    public void brincarComPet() {
        petController.brincarComPet();
    }

    public VirtualPetModel meuPet() {
        return petController.getPet();
    }

    public UsuarioModel getUsuarioLogado() {
        return usuarioLogado;
    }
}