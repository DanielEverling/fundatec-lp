package com.fundatec.veterinaria.application.projection;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CachorroProjection that = (CachorroProjection) o;
        return Objects.equals(nome, that.nome) &&
                Objects.equals(veterinario, that.veterinario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, veterinario);
    }
}
