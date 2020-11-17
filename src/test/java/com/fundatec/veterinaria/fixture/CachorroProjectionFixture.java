package com.fundatec.veterinaria.fixture;

import com.fundatec.veterinaria.application.projection.CachorroProjection;

public class CachorroProjectionFixture {

    private CachorroProjectionFixture() {}

    public static CachorroProjection construirCachorroProjection() {
        return new CachorroProjection(
                "Bilu",
                "Jose Ribeiro"
        );
    }
}
