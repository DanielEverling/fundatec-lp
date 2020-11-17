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
    public ResponseEntity<CachorroProjection> retornaCachorro(@PathVariable("id") Long id) {
        Optional<CachorroProjection> resultado = cachorroService.findById(id);

        if (resultado.isPresent()) {
            return new ResponseEntity<CachorroProjection>(resultado.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<CachorroProjection>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping
    public ResponseEntity<List<CachorroProjection>> findAllByNome(@RequestParam(value = "nome", required = false) String nome) {
        List<CachorroProjection> resultado = cachorroService.findAllByName(nome);
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
