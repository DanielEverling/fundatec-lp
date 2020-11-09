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


    public Cachorro() {}

    public Cachorro(String nome, int idade, String raca, double peso) {
        this.nome = nome;
        this.idade = idade;
        this.raca = raca;
        this.peso = peso;
    }

    public String getNome()
    {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public void setVeterinario(Veterinario veterinario) {
        this.veterinario = veterinario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cachorro cachorro = (Cachorro) o;
        return idade == cachorro.idade &&
                Double.compare(cachorro.peso, peso) == 0 &&
                Objects.equals(id, cachorro.id) &&
                Objects.equals(nome, cachorro.nome) &&
                Objects.equals(raca, cachorro.raca) &&
                Objects.equals(veterinario, cachorro.veterinario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, idade, raca, peso, veterinario);
    }
}
