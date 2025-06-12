package com.prj.html.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PessoaService {
    @Autowired
    PessoaDAO cdao;

    public void inserirPessoa(Pessoa pes){
        cdao.inserirPessoa(pes);
    }

    public List<Pessoa> puxarTodasPessoas(){
        return Pessoa.converterVarios(cdao.puxarTodasPessoas());
    }

    public void atualizarPessoa(int id, Pessoa novo){
        cdao.atualizarPessoa(id, novo);
    }

    public Pessoa puxarPessoa(int id){
        return Pessoa.converter(cdao.puxarPessoa(id));
    }

    public void deletar(int id){
        cdao.deletar(id);
    }}
