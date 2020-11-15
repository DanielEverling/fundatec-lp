package com.fundatec.veterinaria.api;

import com.fundatec.veterinaria.application.projection.CachorroProjection;
import com.fundatec.veterinaria.application.request.CachorroRequest;
import com.fundatec.veterinaria.application.service.CachorroService;
import com.fundatec.veterinaria.domain.Cachorro;
import com.fundatec.veterinaria.domain.Veterinario;
import com.fundatec.veterinaria.infra.repository.CachorroRepository;
import com.fundatec.veterinaria.infra.repository.VeterinarioRepository;
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

    private CachorroService cachorroService;

    public CachorroController(CachorroService cachorroService) {
        this.cachorroService = cachorroService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Cachorro> retornaCachorro(@PathVariable("id") Long id) {
        Optional<Cachorro> resultado = cachorroService.findById(id);

        if(resultado.isPresent()) {
            return new ResponseEntity<Cachorro>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Cachorro>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<CachorroProjection>> findAllByNome(@RequestParam(value = "nome", required = false) String nome) {
        List<CachorroProjection> resultado = new ArrayList<>();

        //validar nome
        if (nome != null) {
            Optional<Cachorro> cachorro = cachorroService.findByNome(nome);

            if(cachorro.isPresent()) {
                resultado.add(new CachorroProjection(cachorro.get().getNome(), cachorro.get().getVeterinario().getNome()));
            } else {
                resultado = Collections.emptyList();
            }
        } else {
            List<Cachorro> dogs = cachorroService.findAll();
            List<CachorroProjection> finalResultado = resultado;
            dogs.stream().map(record -> {
                 return finalResultado.add(new CachorroProjection(record.getNome(), record.getVeterinario().getNome()));
            });
        }

        return new ResponseEntity(resultado, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity salvaCachorro(@RequestBody CachorroRequest cachorroRequest) {
        cachorroService.salvar(cachorroRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody CachorroRequest cachorroRequest) {
        cachorroService.atualizar(id, cachorroRequest);
        return ResponseEntity.ok().body(cachorroRequest);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") long id) {
        cachorroService.deleteById(id);
        return "deleted";
    }

}
