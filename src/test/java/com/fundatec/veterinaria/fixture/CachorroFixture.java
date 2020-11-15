package com.fundatec.veterinaria.fixture;

import com.fundatec.veterinaria.domain.Cachorro;

public class CachorroFixture {

    private CachorroFixture() {}

    public static Cachorro construirCachorro() {
        return new Cachorro(1l,
                "Bilu",
                1,
                "vira lata",
                10,
                    VeterinarioFixture.construirVeterinario()
                );
    }

}
