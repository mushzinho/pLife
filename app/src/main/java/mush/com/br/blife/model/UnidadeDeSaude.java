package mush.com.br.blife.model;

import com.orm.SugarRecord;


public class UnidadeDeSaude extends SugarRecord {

    private String nome;
    private String endereço;

    public UnidadeDeSaude() {
    }
    public UnidadeDeSaude(String nome, String endereço) {
        this.nome = nome;
        this.endereço = endereço;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereço() {
        return endereço;
    }
}
