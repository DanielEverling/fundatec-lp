package com.fundatec.lp.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Embeddable
public class Email {

    @NotNull
    @NotEmpty
    @javax.validation.constraints.Email
    @Column(name = "email")
    private String value;

    /**
     * Construtor usado somente para o hibernate
     */
    public Email() { }

    public Email(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
