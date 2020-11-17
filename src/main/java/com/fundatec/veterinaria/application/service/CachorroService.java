package com.fundatec.veterinaria.application.service;

import com.fundatec.veterinaria.application.converter.ConventerCachorroRequestParaCachorro;
import com.fundatec.veterinaria.application.converter.ConverterCachorroParaCachorroProjection;
import com.fundatec.veterinaria.application.projection.CachorroProjection;
import com.fundatec.veterinaria.application.request.CachorroRequest;
import com.fundatec.veterinaria.domain.Cachorro;
import com.fundatec.veterinaria.infra.repository.CachorroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CachorroService {

    private ConventerCachorroRequestParaCachorro cachorroConverterRequestToDomain;

    private ConverterCachorroParaCachorroProjection converterCachorroParaCachorroProjection;

    private CachorroRepository cachorroRepository;

    public CachorroService(ConventerCachorroRequestParaCachorro cachorroConverterRequestToDomain,
                           CachorroRepository cachorroRepository,
                           ConverterCachorroParaCachorroProjection converterCachorroParaCachorroProjection) {
        this.cachorroConverterRequestToDomain = cachorroConverterRequestToDomain;
        this.cachorroRepository = cachorroRepository;
        this.converterCachorroParaCachorroProjection = converterCachorroParaCachorroProjection;
    }

    @Transactional
    public void salvar(CachorroRequest cachorroRequest) {
        Cachorro cachorro = cachorroConverterRequestToDomain.converter(cachorroRequest);
        cachorroRepository.save(cachorro);
    }

    @Transactional
    public void atualizar(Long id, CachorroRequest cachorroRequest) {
        Optional<Cachorro> cachorroExiste = cachorroRepository.findById(id);

        if (cachorroExiste.isEmpty()) {
            throw new RuntimeException("Cachorro com id " + id + " não encontrato para atualização");
        }

        Cachorro cachorro = cachorroConverterRequestToDomain.converter(id, cachorroRequest);
        cachorroRepository.save(cachorro);
    }

    @Transactional
    public void deleteById(long id) {
        cachorroRepository.deleteById(id);
    }

    public Optional<CachorroProjection> findById(Long id) {
        Optional<Cachorro> existeCachorro = cachorroRepository.findById(id);

        if (existeCachorro.isEmpty()) {
            return Optional.empty();
        }
        CachorroProjection cachorroProjection = converterCachorroParaCachorroProjection.converter(existeCachorro.get());
        return Optional.of(cachorroProjection);
    }

    public List<CachorroProjection> findAllByName(String nome) {
        List<CachorroProjection> resultado = new ArrayList<>();
        if (nome == null) {
            List<Cachorro> dogs = cachorroRepository.findAll();
            dogs.stream().forEach( record -> {
                resultado.add(converterCachorroParaCachorroProjection.converter(record));
            });
            return resultado;
        }
        Optional<Cachorro> cachorro = cachorroRepository.findByNome(nome);
        if(cachorro.isPresent()) {
            resultado.add(new CachorroProjection(cachorro.get().getNome(), cachorro.get().getVeterinario().getNome()));
        }
        return resultado;
    }
}
