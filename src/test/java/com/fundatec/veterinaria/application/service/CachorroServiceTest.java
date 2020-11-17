package com.fundatec.veterinaria.application.service;

import com.fundatec.veterinaria.application.converter.ConventerCachorroRequestParaCachorro;
import com.fundatec.veterinaria.application.converter.ConverterCachorroParaCachorroProjection;
import com.fundatec.veterinaria.application.projection.CachorroProjection;
import com.fundatec.veterinaria.application.request.CachorroRequest;
import com.fundatec.veterinaria.domain.Cachorro;
import com.fundatec.veterinaria.fixture.CachorroFixture;
import com.fundatec.veterinaria.fixture.CachorroProjectionFixture;
import com.fundatec.veterinaria.fixture.CachorroRequestFixture;
import com.fundatec.veterinaria.infra.repository.CachorroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

class CachorroServiceTest {

    @Mock
    private CachorroRepository cachorroRepository;

    @Mock
    private ConventerCachorroRequestParaCachorro conventerCachorroRequestParaCachorro;

    @Mock
    private ConverterCachorroParaCachorroProjection converterCachorroParaCachorroProjection;

    private CachorroService cachorroService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        cachorroService = new CachorroService(conventerCachorroRequestParaCachorro,
                cachorroRepository,
                converterCachorroParaCachorroProjection
        );
    }

    @Test
    public void deveSalvarUmCachorro() {
        CachorroRequest cachorroRequest = CachorroRequestFixture.construirCachorroRequest();
        Cachorro cachorro = CachorroFixture.construirCachorro();

        when(conventerCachorroRequestParaCachorro.converter(cachorroRequest)).thenReturn(cachorro);
        cachorroService.salvar(cachorroRequest);
        Mockito.verify(conventerCachorroRequestParaCachorro, times(1)).converter(cachorroRequest);
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

        Mockito.verify(conventerCachorroRequestParaCachorro, never()).converter(cachorroRequest);
        Mockito.verify(cachorroRepository, never()).save(cachorro);
    }

    @Test
    public void deveAtualizarUmCachorro() {
        CachorroRequest cachorroRequest = CachorroRequestFixture.construirCachorroRequest();
        Cachorro cachorro = CachorroFixture.construirCachorro();

        when(cachorroRepository.findById(1l)).thenReturn(Optional.of(cachorro));
        when(conventerCachorroRequestParaCachorro.converter(1l, cachorroRequest)).thenReturn(cachorro);

        cachorroService.atualizar(1l, cachorroRequest);

        Mockito.verify(conventerCachorroRequestParaCachorro, times(1)).converter(1l, cachorroRequest);
        Mockito.verify(cachorroRepository, times(1)).save(cachorro);
    }

    @Test
    public void deveRetornarUmCachorroProjectionQuandoEncontrarUmCachorro() {
        Cachorro cachorro = CachorroFixture.construirCachorro();
        CachorroProjection cachorroProjection = CachorroProjectionFixture.construirCachorroProjection();

        when(cachorroRepository.findById(1l)).thenReturn(Optional.of(cachorro));
        when(converterCachorroParaCachorroProjection.converter(cachorro)).thenReturn(cachorroProjection);

        cachorroService.findById(1l);

        Mockito.verify(converterCachorroParaCachorroProjection, times(1)).converter(cachorro);
    }

    @Test
    public void deveRetornarUmVazioQuandoNaoEncontrarUmCachorro() {
        Cachorro cachorro = CachorroFixture.construirCachorro();

        when(cachorroRepository.findById(1l)).thenReturn(Optional.empty());

        cachorroService.findById(1l);

        Mockito.verify(converterCachorroParaCachorroProjection, never()).converter(cachorro);
    }

    @Test
    public void deveBuscarCachorroPorNomeQuandoNomeEhInformado() {
        Cachorro cachorro = CachorroFixture.construirCachorro();
        CachorroProjection cachorroProjection = CachorroProjectionFixture.construirCachorroProjection();

        when(cachorroRepository.findByNome("Bilu")).thenReturn(Optional.of(cachorro));
        when(converterCachorroParaCachorroProjection.converter(cachorro)).thenReturn(cachorroProjection);

        List<CachorroProjection> projections = cachorroService.findAllByName("Bilu");
        assertEquals(1, projections.size());
        assertEquals(cachorroProjection, projections.stream().findFirst().get());
    }

    @Test
    public void deveBuscarCachorroPorNomeQuandoNomeNaoEhInforamado() {
        Cachorro cachorro = CachorroFixture.construirCachorro();
        CachorroProjection cachorroProjection = CachorroProjectionFixture.construirCachorroProjection();

        when(cachorroRepository.findAll()).thenReturn(List.of(cachorro));
        when(converterCachorroParaCachorroProjection.converter(cachorro)).thenReturn(cachorroProjection);

        List<CachorroProjection> projections = cachorroService.findAllByName(null);
        assertEquals(1, projections.size());
        assertEquals(cachorroProjection, projections.stream().findFirst().get());
    }
}