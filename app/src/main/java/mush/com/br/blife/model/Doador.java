package mush.com.br.blife.model;

import com.orm.SugarRecord;

import java.util.List;

public class Doador extends SugarRecord {

    private String nome;
    private String sexo;
    private int idade;
    private List<String> contatos;

    public Doador() {
    }

    public Doador(String nome, String sexo, int idade, List<String> contatos) {
        this.nome = nome;
        this.sexo = sexo;
        this.idade = idade;
        this.contatos = contatos;
    }

    public String getNome() {
        return nome;
    }

    public String getSexo() {
        return sexo;
    }

    public int getIdade() {
        return idade;
    }

    public List<String> getContatos() {
        return contatos;
    }
}
