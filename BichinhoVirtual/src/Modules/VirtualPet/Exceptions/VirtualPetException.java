package Modules.VirtualPet.Exceptions;

import Modules.User.Exceptions.UsuarioException;

public class VirtualPetException extends Exception{

    public VirtualPetException(String mensagem){
        super(mensagem);

    }
}
