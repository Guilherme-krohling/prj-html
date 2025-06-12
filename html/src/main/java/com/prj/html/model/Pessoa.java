package com.prj.html.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Pessoa {
    private int id;
    private String nome, banda, cantor, musica;

    public Pessoa() {
    }

    public Pessoa(String banda, String cantor, String musica, String nome) {
        this.nome = nome;
        this.banda = banda;
        this.cantor = cantor;
        this.musica = musica;      
    }

    public Pessoa(int id, String nome, String banda, String cantor, String musica) {
        this.id = id;
        this.nome = nome;
        this.banda = banda;
        this.cantor = cantor;
        this.musica = musica;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getBanda() {
        return banda;
    }

    public String getCantor() {
        return cantor;
    }

    public String getMusica() {
        return musica;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setBanda(String banda) {
        this.banda = banda;
    }

    public void setCantor(String cantor) {
        this.cantor = cantor;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public static Pessoa converter(Map<String, Object> registro){
        int id = (Integer) registro.get("id");
        String nome = (String) registro.get("nome");
        String banda = (String) registro.get("banda");
        String cantor = (String) registro.get("cantor");
        String musica = (String) registro.get("musica");

        return new Pessoa(id, nome, banda, cantor, musica);
    }

    public static List<Pessoa> converterVarios(List<Map<String, Object>> registros) {
        List<Pessoa> lista = new ArrayList<>();
        for (Map<String, Object> reg : registros) {
            lista.add(converter(reg));
        }
        return lista;
    }
    

}
