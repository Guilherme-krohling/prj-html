package com.prj.html.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Indisponibilidade {
    private int id;
    private String nome;
    private String motivo;
    private String responsavel;
    private String setor;
    private String observacoes;
    private Date data_indisponibilidade;

    public Indisponibilidade() {}

    public Indisponibilidade(int id, String nome, String motivo, String responsavel, String setor, String observacoes, Date data_indisponibilidade) {
        this.id = id;
        this.nome = nome;
        this.motivo = motivo;
        this.responsavel = responsavel;
        this.setor = setor;
        this.observacoes = observacoes;
        this.data_indisponibilidade = data_indisponibilidade;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public Date getData_indisponibilidade() { return data_indisponibilidade; }
    public void setData_indisponibilidade(Date data_indisponibilidade) { this.data_indisponibilidade = data_indisponibilidade; }

    public static Indisponibilidade converter(Map<String, Object> registro) {
        Indisponibilidade i = new Indisponibilidade();
        i.setId((Integer) registro.get("id"));
        i.setNome((String) registro.get("nome"));
        i.setMotivo((String) registro.get("motivo"));
        i.setResponsavel((String) registro.get("responsavel"));
        i.setSetor((String) registro.get("setor"));
        i.setObservacoes((String) registro.get("observacoes"));
        i.setData_indisponibilidade((Date) registro.get("data_indisponibilidade")); // precisa estar vindo como java.util.Date

        return i;
    }

    public static List<Indisponibilidade> converterVarios(List<Map<String, Object>> registros) {
        List<Indisponibilidade> lista = new ArrayList<>();
        for (Map<String, Object> reg : registros) {
            lista.add(converter(reg));
        }
        return lista;
    }
}
