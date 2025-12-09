package Modules.VirtualPet;

import Enums.EspecieEnum;
import Modules.VirtualPet.Errors.VirtualPetErros;
import Modules.VirtualPet.Exceptions.VirtualPetException;

public class VirtualPetController {
    private VirtualPetModel pet;

    public VirtualPetController(){
        this.pet = null;
    }

    public boolean existePet(){
        return pet != null && pet.getNome() != null && pet.getEspecie() != null;
    }

    public void adotar(String nome, EspecieEnum especie){
        this.pet = new VirtualPetModel(nome, especie);
    }

    public boolean statusFome() throws IllegalAccessException {
        if (!existePet()){
            throw new IllegalAccessException("Nenhum pet adotado ainda");
        }
        return pet.isFome();
    }

    public void alimentarPet() throws IllegalAccessException {
       try{
           if (!existePet()){
               throw new IllegalAccessException("Nenhum pet adotado ainda");
           }

           if(statusFome()){
               pet.alimentar();
               System.out.println(pet.getNome() + "foi alimentado e está sem fome!");
           }else {
                throw new VirtualPetException(VirtualPetErros.jaComeu);
           }
       } catch (VirtualPetException e) {
           System.out.println("Erro ao alimentar o pet: " + e.getMessage());
       }

    }
    public boolean statusLimpeza() throws IllegalAccessException{
        if (!existePet()){
            throw new IllegalAccessException("Nenhum pet adotado");
        }
        return pet.isSujo();

    }

    public void limparPet() throws IllegalAccessException {
        try{
            if (!existePet()){
                throw new IllegalAccessException("Nenhum pet adotado ainda");
            }

            if(statusLimpeza()){
                pet.limpar();
                System.out.println(pet.getNome() + "foi limpo!");
            }else {
                throw new VirtualPetException(VirtualPetErros.jaEstaLimpo);
            }
        } catch (VirtualPetException e) {
            System.out.println("Erro ao limpar o pet: " + e.getMessage());
        }

    }

    public void brincarComPet() throws IllegalAccessException {
        if (!existePet()) {
            System.out.println("Nenhum pet adotado ainda! Adote um pet para brincar.");
            return;
        }

        System.out.println("\n=== Hora da Brincadeira com " + pet.getNome() + " ===");

        if (!statusFome()) {
            pet.brincar();
            System.out.println(pet.getNome() + " está brincando!");
            System.out.println(pet.getNome() + " está com fome!");
            System.out.println(pet.getNome() + " ficou sujo!");
        } else {
            System.out.println(pet.getNome() + " está com fome e não tem energia para brincar!");
            System.out.println(pet.getNome() + " precisa comer para brincar!");
        }

        System.out.println("====================================\n");
    }

    public VirtualPetModel getPet() {
        return pet;
    }

}
