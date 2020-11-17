package com.fundatec.veterinaria.application.converter;

import com.fundatec.veterinaria.application.request.CachorroRequest;
import com.fundatec.veterinaria.domain.Cachorro;
import com.fundatec.veterinaria.domain.Veterinario;
import com.fundatec.veterinaria.fixture.VeterinarioFixture;
import com.fundatec.veterinaria.infra.repository.VeterinarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConventerCachorroRequestParaCachorroTest {

    @Mock
    private VeterinarioRepository veterinarioRepository;

    private ConventerCachorroRequestParaCachorro cachorroConverterRequestToDomain;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        cachorroConverterRequestToDomain = new ConventerCachorroRequestParaCachorro(veterinarioRepository);
    }

    @Test
    public void deveConverterCachorroRequestParaCachorroDominioQuandoIncluir() {
        Veterinario veterinario = VeterinarioFixture.construirVeterinario();
        when(veterinarioRepository.getOne(1l)).thenReturn(veterinario);

        CachorroRequest cachorroRequest = new CachorroRequest();
        cachorroRequest.setIdade(1);
        cachorroRequest.setNome("Bilu");
        cachorroRequest.setPeso(10);
        cachorroRequest.setRaca("vira lata");
        cachorroRequest.setVeterinario(1l);

        Cachorro cachorro = cachorroConverterRequestToDomain.converter(cachorroRequest);

        assertNull(cachorro.getId());
        assertEquals(1, cachorro.getIdade());
        assertEquals("Bilu", cachorro.getNome());
        assertEquals(10, cachorro.getPeso());
        assertEquals("vira lata", cachorro.getRaca());
        assertEquals(veterinario, cachorro.getVeterinario());
    }

    @Test
    public void deveConverterCachorroRequestParaCachorroDominioQuandoEditar() {
        Veterinario veterinario = VeterinarioFixture.construirVeterinario();
        when(veterinarioRepository.getOne(1l)).thenReturn(veterinario);

        CachorroRequest cachorroRequest = new CachorroRequest();
        cachorroRequest.setIdade(1);
        cachorroRequest.setNome("Bilu");
        cachorroRequest.setPeso(10);
        cachorroRequest.setRaca("vira lata");
        cachorroRequest.setVeterinario(1l);

        Cachorro cachorro = cachorroConverterRequestToDomain.converter(1l, cachorroRequest);

        assertEquals(1l, cachorro.getId());
        assertEquals(1, cachorro.getIdade());
        assertEquals("Bilu", cachorro.getNome());
        assertEquals(10, cachorro.getPeso());
        assertEquals("vira lata", cachorro.getRaca());
        assertEquals(veterinario, cachorro.getVeterinario());
    }
}