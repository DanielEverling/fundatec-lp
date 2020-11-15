package com.fundatec.veterinaria.fixture;

import com.fundatec.veterinaria.domain.Veterinario;

public class VeterinarioFixture {

    private VeterinarioFixture() {}

    public static Veterinario construirVeterinario() {
        return new Veterinario(1l,
                "Jose Ribeiro",
                "01592281810"
                );
    }
}
