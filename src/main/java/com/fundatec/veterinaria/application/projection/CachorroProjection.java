package com.fundatec.veterinaria.application.projection;

public class CachorroProjection {

    private long cachorroId;

    private String nome;

    private long veterinarioId;

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
