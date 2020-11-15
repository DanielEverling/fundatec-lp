package com.fundatec.veterinaria.application.service;

import com.fundatec.veterinaria.application.converter.CachorroConverterRequestToDomain;
import com.fundatec.veterinaria.application.request.CachorroRequest;
import com.fundatec.veterinaria.domain.Cachorro;
import com.fundatec.veterinaria.fixture.CachorroFixture;
import com.fundatec.veterinaria.fixture.CachorroRequestFixture;
import com.fundatec.veterinaria.infra.repository.CachorroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

class CachorroServiceTest {

    @Mock
    private CachorroRepository cachorroRepository;

    @Mock
    private CachorroConverterRequestToDomain cachorroConverterRequestToDomain;

    private CachorroService cachorroService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        cachorroService = new CachorroService(cachorroConverterRequestToDomain, cachorroRepository);
    }

    @Test
    public void deveSalvarUmCachorro() {
        CachorroRequest cachorroRequest = CachorroRequestFixture.construirCachorroRequest();
        Cachorro cachorro = CachorroFixture.construirCachorro();

        when(cachorroConverterRequestToDomain.converter(cachorroRequest)).thenReturn(cachorro);
        cachorroService.salvar(cachorroRequest);
        Mockito.verify(cachorroConverterRequestToDomain, times(1)).converter(cachorroRequest);
        Mockito.verify(cachorroRepository, times(1)).save(cachorro);
    }

    @Test
    public void deveValidarAtualizacaoDeCachorroQuandoNaoExistir() {
        CachorroRequest cachorroRequest = CachorroRequestFixture.construirCachorroRequest();
        Cachorro cachorro = CachorroFixture.construirCachorro();

        when(cachorroRepository.findById(1l)).thenReturn(Optional.empty());

        try {
            cachorroService.atualizar(1l, cachorroRequest);
        } catch (RuntimeException runtimeException) {
            String mensagemEsperada = "Cachorro com id " + 1l + " não encontrato para atualização";
            assertEquals(mensagemEsperada, runtimeException.getMessage());
        }

        Mockito.verify(cachorroConverterRequestToDomain, never()).converter(cachorroRequest);
        Mockito.verify(cachorroRepository, never()).save(cachorro);
    }

    @Test
    public void deveAtualizarUmCachorro() {
        CachorroRequest cachorroRequest = CachorroRequestFixture.construirCachorroRequest();
        Cachorro cachorro = CachorroFixture.construirCachorro();

        when(cachorroRepository.findById(1l)).thenReturn(Optional.of(cachorro));
        when(cachorroConverterRequestToDomain.converter(1l, cachorroRequest)).thenReturn(cachorro);

        cachorroService.atualizar(1l, cachorroRequest);

        Mockito.verify(cachorroConverterRequestToDomain, times(1)).converter(1l, cachorroRequest);
        Mockito.verify(cachorroRepository, times(1)).save(cachorro);
    }

}