package mush.com.br.blife.model;

import com.orm.SugarRecord;

import java.util.Date;


public class SaidaBolsa extends SugarRecord {

    private String hospital;
    private Date data;
    private BolsasPorTipo bolsasPorTipo;

    public SaidaBolsa(){
    }

    public SaidaBolsa(String hospital, Date data, BolsasPorTipo bolsasPorTipo) {
        this.hospital = hospital;
        this.data = data;
        this.bolsasPorTipo = bolsasPorTipo;
    }

    public String getHospital() {
        return hospital;
    }

    public Date getData() {
        return data;
    }

    public BolsasPorTipo getBolsasPorTipo() {
        return bolsasPorTipo;
    }
}
