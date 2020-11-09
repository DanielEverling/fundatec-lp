package com.fundatec.veterinaria.controller;

public class CachorroProjection {

    private String nome;

    private String veterinario;

    public CachorroProjection(String nome, String veterinario) {
        this.nome = nome;
        this.veterinario = veterinario;
    }

    public String getNome() {
        return nome;
    }

    public String getVeterinario() {
        return veterinario;
    }
}
