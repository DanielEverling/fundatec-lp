package com.fundatec.veterinaria.application.converter;

import com.fundatec.veterinaria.application.projection.CachorroProjection;
import com.fundatec.veterinaria.domain.Cachorro;
import com.fundatec.veterinaria.fixture.CachorroFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterCachorroParaCachorroProjectionTest {

    private ConverterCachorroParaCachorroProjection converterCachorroParaCachorroProjection;

    @BeforeEach
    void setUp() {
        converterCachorroParaCachorroProjection = new ConverterCachorroParaCachorroProjection();
    }

    @Test
    public void deveConverterCachorroParaProjection() {
        Cachorro cachorro = CachorroFixture.construirCachorro();

        CachorroProjection cachorroProjection = converterCachorroParaCachorroProjection.converter(cachorro);

        assertEquals("Bilu", cachorroProjection.getNome());
        assertEquals("Jose Ribeiro", cachorroProjection.getVeterinario());
    }
}