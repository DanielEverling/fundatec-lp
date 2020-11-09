package com.fundatec.veterinaria.controller;

import com.fundatec.veterinaria.domain.Cachorro;
import com.fundatec.veterinaria.domain.Veterinario;
import com.fundatec.veterinaria.repository.CachorroRepository;
import com.fundatec.veterinaria.repository.VeterinarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/cachorros")
public class CachorroController {

    @Autowired
    private CachorroRepository cachorroRepository;

    @Autowired
    private VeterinarioRepository veterinarioRepository;

    @GetMapping("{id}")
    public ResponseEntity<Cachorro> retornaCachorro(@PathVariable("id") Long id) {
        Optional<Cachorro> resultado = cachorroRepository.findById(id);

        if(resultado.isPresent()) {
            return new ResponseEntity<Cachorro>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Cachorro>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<CachorroVeterinarioProjection>> findAllByNome(@RequestParam(value = "nome", required = false) String nome) {
        List<CachorroProjection> resultado = new ArrayList<>();

        //validar nome
        if (nome != null) {
            Optional<Cachorro> cachorro = cachorroRepository.findByNome(nome);

            if(cachorro.isPresent()) {
                resultado.add(new CachorroProjection(cachorro.get().getNome(), cachorro.get().getVeterinario().getNome()));
            } else {
                resultado = Collections.emptyList();
            }
        } else {
            List<Cachorro> dogs = cachorroRepository.findAll();
            List<CachorroProjection> finalResultado = resultado;
            dogs.stream().map(record -> {
                 return finalResultado.add(new CachorroProjection(record.getNome(), record.getVeterinario().getNome()));
            });
        }

        return new ResponseEntity(resultado, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity salvaCachorro(@RequestBody CachorroRequest cachorroRequest) {

        Veterinario veterinario = veterinarioRepository.getOne(cachorroRequest.getVeterinario());

        Cachorro cachorro = new Cachorro();
        cachorro.setIdade(cachorroRequest.getIdade());
        cachorro.setNome(cachorroRequest.getNome());
        cachorro.setPeso(cachorroRequest.getPeso());
        cachorro.setRaca(cachorroRequest.getRaca());
        cachorro.setVeterinario(veterinario);

        cachorroRepository.save(cachorro);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Cachorro cachorro) {
        return cachorroRepository.findById(id)
                .map(record -> {
                    record.setNome(cachorro.getNome());
                    record.setIdade(cachorro.getIdade());
                    record.setPeso(cachorro.getPeso());
                    record.setRaca(cachorro.getRaca());
                    Cachorro updated = cachorroRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") long id) {
        cachorroRepository.deleteById(id);
        return "deleted";
    }

}
