package com.fundatec.lp.application;

import com.fundatec.lp.domain.Pessoa;
import com.fundatec.lp.domain.PessoaRepository;
import org.springframework.stereotype.Service;

@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void salvar(Pessoa pessoa) {
        this.pessoaRepository.save(pessoa);
    }

}
