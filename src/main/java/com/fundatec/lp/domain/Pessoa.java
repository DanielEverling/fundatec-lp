package com.fundatec.lp.domain;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @Valid
    @Embedded
    private Email email;

    @Valid
    @Embedded
    private CPF cpf;

    private boolean status;

    /**
     * Construtor usado somente para o hibernate
     */
    public Pessoa() {}

    public Pessoa(Long id, String nome, Email email, CPF cpf, boolean status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Email getEmail() {
        return email;
    }

    public CPF getCpf() {
        return cpf;
    }

    public boolean isStatus() {
        return status;
    }

}
