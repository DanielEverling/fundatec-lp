package com.fundatec.veterinaria.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cachorro")
public class Cachorro implements  Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private int idade;

    @Column(name = "raça")
    private String raca;

    @Column(name = "peso")
    private double peso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinario_id")
    private Veterinario veterinario;

    /*
     * Construtor usado apenas pelo hibernate
     */
    @Deprecated
    protected Cachorro() {}

    public Cachorro(Long id, String nome, int idade, String raca, double peso, Veterinario veterinario) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.peso = peso;
        this.veterinario = veterinario;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() { return idade; }

    public Long getId() { return id; }

    public String getRaca() { return raca; }

    public double getPeso() { return peso; }

    public Veterinario getVeterinario() { return veterinario; }

}
