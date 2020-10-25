package com.fundatec.lp.api;

import com.fundatec.lp.application.PessoaService;
import com.fundatec.lp.domain.Pessoa;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    private PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public void salvar(@Valid @RequestBody Pessoa pessoa) {
        this.pessoaService.salvar(pessoa);
    }

}
