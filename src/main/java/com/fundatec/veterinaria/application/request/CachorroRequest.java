package com.fundatec.veterinaria.application.request;

public class CachorroRequest {

    private String nome;

    private int idade;

    private String raca;

    private double peso;

    private long veterinario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public long getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(long veterinario) {
        this.veterinario = veterinario;
    }

}
