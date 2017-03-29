package mush.com.br.blife.model;

import com.orm.SugarRecord;

import java.util.Date;


public class SaidaBolsa extends SugarRecord {

    private String hospital;
    private Date data;
    private int quantidadeSaindo;
    //TODO adicionar o tipo de saida
    public SaidaBolsa(){
    }
    public SaidaBolsa(String hospital, Date data, int quantidadeSaindo) {
        this.hospital = hospital;
        this.data = data;
        this.quantidadeSaindo = quantidadeSaindo;
    }

    public String getHospital() {
        return hospital;
    }

    public Date getData() {
        return data;
    }

    public int getQuantidadeSaindo() {
        return quantidadeSaindo;
    }
}
