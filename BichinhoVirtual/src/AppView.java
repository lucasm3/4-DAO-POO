
import Modules.User.UsuarioPetController;
import Modules.VirtualPet.VirtualPetController;
import DAO.DAO;
import Modules.User.UsuarioModel;

public class AppView {

    private UsuarioPetController userController;
    private VirtualPetController petController;
    private DAO<UsuarioModel> usuarioDAO;

    public AppView() {
        this.usuarioDAO = new DAO<>(UsuarioModel::getId);
        this.petController = new VirtualPetController();
        this.userController = new UsuarioPetController(usuarioDAO, petController);
    }

    public UsuarioPetController getUserController() { return userController; }
    public VirtualPetController getPetController() { return petController; }
}
