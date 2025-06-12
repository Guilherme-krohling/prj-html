package com.prj.html.model;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class PessoaDAO {

    @Autowired
	DataSource dataSource;
	
	JdbcTemplate jdbc;
	
	@PostConstruct
	private void initialize() {
		jdbc = new JdbcTemplate(dataSource);
	}

    public void inserirPessoa(Pessoa pes){
        String sql = "INSERT INTO pessoa(nome,banda,cantor,musica) VALUES(?,?,?,?)";
        Object[] parametros = new Object[4]; //Um para cada ?
        parametros[0] = pes.getNome();
        parametros[1] = pes.getBanda();
        parametros[1] = pes.getCantor();
        parametros[1] = pes.getMusica();
        jdbc.update(sql,parametros);
    }

    //Map<String,Object> -> registro generico do banco de dados
    //A chave String seria o nome do campo ex: id
    //O valor Object o valor do campo ex: 2
    /* List<Map<String,Object>> :=
        [
        { "id" : 1,
          "nome" : "Teste0",
          "cpf" : 11122233344
        },
        { "id" : 2,
          "nome" : "Teste",
          "cpf" : 12345678900
        }
        ]
     */
    public List<Map<String,Object>> puxarTodasPessoas(){
        String sql = "SELECT * FROM pessoas;";
        return jdbc.queryForList(sql);
    }

    public Map<String,Object> puxarPessoa(int id){
        String sql = "SELECT * from pessoa WHERE id = ?";
        return jdbc.queryForMap(sql, id);
    }

    public void atualizarPessoa(int id, Pessoa novo){
        String sql = "UPDATE pessoa SET nome = ?, banda = ?, cantor = ?, musica = ? WHERE id = ?";
        Object[] parametros = new Object[3];
        parametros[0] = novo.getNome();
        parametros[1] = novo.getBanda();
        parametros[2] = novo.getCantor();
        parametros[3] = novo.getMusica();
        parametros[4] = id;
        jdbc.update(sql,parametros);
    }

    public void deletar(int id){
        String sql = "DELETE FROM pessoa WHERE id = ?";
        jdbc.update(sql,id);
    }}
