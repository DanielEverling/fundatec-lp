package com.fundatec.veterinaria.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "veterinario")
public class Veterinario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;


    @Column(name = "CPF", unique = true)
    private String cpf;

    @OneToMany(mappedBy = "veterinario")
    private List<Cachorro> cachorros;

    public Veterinario() {
    }

    public Veterinario(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Cachorro> getCachorros() {
        return cachorros;
    }

    public void setCachorros(List<Cachorro> cachorros) {
        this.cachorros = cachorros;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Veterinario that = (Veterinario) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(cachorros, that.cachorros);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, cpf, cachorros);
    }
}
