package mush.com.br.blife.model;

import com.orm.SugarRecord;

import java.util.Date;


public class Campanhas extends SugarRecord {

    private String nome;
    private Tipos_sangue tipoSangue;
    private Date inicio;
    private Date fim;
    private String DescricaoDaCapanha;

    public Campanhas(){
    }

    public Campanhas(String nome, Tipos_sangue tipoSangue, Date inicio, Date fim, String descricaoDaCapanha) {
        this.nome = nome;
        this.tipoSangue = tipoSangue;
        this.inicio = inicio;
        this.fim = fim;
        DescricaoDaCapanha = descricaoDaCapanha;
    }

    public String getNome() {
        return nome;
    }

    public Tipos_sangue getTipoSangue() {
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

    public static enum Tipos_sangue{

        A_MAIS(0),
        A_MENOS(1),
        B_MAIS(2),
        B_MENOS(3),
        AB_MAIS(4),
        AB_MENOS(5),
        O_MAIS(6),
        O_MENOS(7);

        private final int codigo;

        private Tipos_sangue(int codigo) {
            this.codigo = codigo;
        }

        public int getCodigo() {
            return codigo;
        }
    }
}
