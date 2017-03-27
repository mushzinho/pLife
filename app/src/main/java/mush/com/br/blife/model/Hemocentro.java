package mush.com.br.blife.model;

import com.orm.SugarRecord;


public class Hemocentro extends SugarRecord {

    private String nome;
    private String endereço;
    private String telefone;

    public Hemocentro() {
    }

    public Hemocentro(String nome, String endereço, String telefone) {
        this.nome = nome;
        this.endereço = endereço;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereço() {
        return endereço;
    }

    public String getTelefone() {
        return telefone;
    }
}
