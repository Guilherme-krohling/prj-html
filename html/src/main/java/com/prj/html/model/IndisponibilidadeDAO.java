package com.prj.html.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class IndisponibilidadeDAO {

    @Autowired
    DataSource dataSource;

    JdbcTemplate jdbc;

    @PostConstruct
    private void initialize() {
        jdbc = new JdbcTemplate(dataSource);
    }

    public void inserir(Indisponibilidade ind) {
        String sql = "INSERT INTO indisponibilidade (nome, motivo, responsavel, setor, observacoes, data_indisponibilidade) VALUES (?, ?, ?, ?, ?, ?)";
        Object[] parametros = {
            ind.getNome(),
            ind.getMotivo(),
            ind.getResponsavel(),
            ind.getSetor(),
            ind.getObservacoes(),
            ind.getData_indisponibilidade()
        };
        jdbc.update(sql, parametros);
    }

    public List<Map<String, Object>> listarTodas() {
        String sql = "SELECT * FROM indisponibilidade";
        return jdbc.queryForList(sql);
    }

    public Map<String, Object> buscarPorId(int id) {
        String sql = "SELECT * FROM indisponibilidade WHERE id = ?";
        return jdbc.queryForMap(sql, id);
    }

    public void atualizar(int id, Indisponibilidade nova) {
        String sql = "UPDATE indisponibilidade SET nome = ?, motivo = ?, responsavel = ?, setor = ?, observacoes = ?, data_indisponibilidade = ? WHERE id = ?";
        Object[] parametros = {
            nova.getNome(),
            nova.getMotivo(),
            nova.getResponsavel(),
            nova.getSetor(),
            nova.getObservacoes(),
            nova.getData_indisponibilidade(),
            id
        };
        jdbc.update(sql, parametros);
    }

    public void deletar(int id) {
        String sql = "DELETE FROM indisponibilidade WHERE id = ?";
        jdbc.update(sql, id);
    }
}
