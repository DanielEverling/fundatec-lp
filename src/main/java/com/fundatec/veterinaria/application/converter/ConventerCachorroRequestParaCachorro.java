package com.fundatec.veterinaria.application.converter;

import com.fundatec.veterinaria.application.request.CachorroRequest;
import com.fundatec.veterinaria.domain.Cachorro;
import com.fundatec.veterinaria.domain.Veterinario;
import com.fundatec.veterinaria.infra.repository.VeterinarioRepository;
import org.springframework.stereotype.Component;

@Component
public class ConventerCachorroRequestParaCachorro {

    private VeterinarioRepository veterinarioRepository;

    public ConventerCachorroRequestParaCachorro(VeterinarioRepository veterinarioRepository) {
        this.veterinarioRepository = veterinarioRepository;
    }

    public Cachorro converter (CachorroRequest cachorroRequest) {
        return converter(null, cachorroRequest);
    }

    public Cachorro converter (Long id, CachorroRequest cachorroRequest) {
        Veterinario veterinario = veterinarioRepository.getOne(cachorroRequest.getVeterinario());

        return new Cachorro(
                id,
                cachorroRequest.getNome(),
                cachorroRequest.getIdade(),
                cachorroRequest.getRaca(),
                cachorroRequest.getPeso(),
                veterinario
        );
    }
}
