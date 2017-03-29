package mush.com.br.blife.model;

import com.orm.SugarRecord;

import java.util.Date;


public class Campanha extends SugarRecord {

    private String nome;
    private String tipoSangue;
    private Date inicio;
    private Date fim;
    private String DescricaoDaCapanha;

    public Campanha(){
    }

    public Campanha(String nome, String tipoSangue, Date inicio, Date fim, String descricaoDaCapanha) {
        this.nome = nome;
        this.tipoSangue = tipoSangue;
        this.inicio = inicio;
        this.fim = fim;
        DescricaoDaCapanha = descricaoDaCapanha;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public Date getInicio() {
        return inicio;
    }

    public Date getFim() {
        return fim;
    }

    public String getDescricaoDaCapanha() {
        return DescricaoDaCapanha;
    }

    @Override
    public String toString() {
        return nome;
    }
}
