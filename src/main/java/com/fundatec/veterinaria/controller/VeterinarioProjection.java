package com.fundatec.veterinaria.controller;

import java.util.List;

public class VeterinarioProjection {

    private String nome;

    private String cpf;

    private List<CachorroVeterinarioProjection> cachorros;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<CachorroVeterinarioProjection> getCachorros() {
        return cachorros;
    }

    public void setCachorros(List<CachorroVeterinarioProjection> cachorros) {
        this.cachorros = cachorros;
    }
}
