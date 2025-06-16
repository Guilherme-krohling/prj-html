package com.prj.html.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndisponibilidadeService {

    @Autowired
    IndisponibilidadeDAO dao;

    public void inserir(Indisponibilidade ind) {
        dao.inserir(ind);
    }

    public List<Indisponibilidade> listarTodas() {
        return Indisponibilidade.converterVarios(dao.listarTodas());
    }

    public Indisponibilidade buscarPorId(int id) {
        return Indisponibilidade.converter(dao.buscarPorId(id));
    }

    public void atualizar(int id, Indisponibilidade nova) {
        dao.atualizar(id, nova);
    }

    public void deletar(int id) {
        dao.deletar(id);
    }
}
