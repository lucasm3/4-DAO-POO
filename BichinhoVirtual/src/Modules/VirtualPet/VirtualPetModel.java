package Modules.VirtualPet;

import Enums.EspecieEnum;

public class VirtualPetModel {
    private String nome;
    private EspecieEnum especie;
    private boolean fome;
    private boolean sujo;

    public VirtualPetModel(String nomePet, String especie){
        this.fome = true;
        this.sujo = true;
    }

    public VirtualPetModel(String nome, EspecieEnum especie){
        this(nomePet, especie);
        this.nome = nome;
        this.especie = especie;

    }

    public String getNome(){
        return nome;
    }

    public EspecieEnum getEspecie(){
        return especie;
    }

    public boolean isFome(){
        return fome;
    }

    public boolean isSujo(){
        return sujo;
    }

    protected void alimentar(){
       this.fome = false;
    }

    protected void limpar(){
       this.sujo = false;
    }

    protected void brincar(){
        this.fome = true;
        this.sujo = true;
    }
    @Override
    public String toString(){
        return "VirtualPetModel{" +
                "nome='" + nome + '\'' +
                ", especie=" + especie +
                ", fome=" + fome +
                ", sujo=" + sujo +
                '}';
    }



}

