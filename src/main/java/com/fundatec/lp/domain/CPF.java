package com.fundatec.lp.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
public class CPF {

    @NotEmpty
    @NotNull
    @org.hibernate.validator.constraints.br.CPF
    @Column(name = "cpf")
    private String value;

    /**
     * Construtor usado somente para o hibernate
     */
    public CPF() { }

    public CPF(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
