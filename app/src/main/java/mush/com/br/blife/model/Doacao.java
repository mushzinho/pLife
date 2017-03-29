package mush.com.br.blife.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

public class Doacao extends SugarRecord {

    private int quantidadeBolsas;
    private Date data;
    private Doador doador;
    private String pacienteDestino;
    private Campanha campanhaDestino;
    @Ignore
    public static final String LIVRE = "LIVRE";
    @Ignore
    public static final String CAMPANHA = "CAMPANHA";

    public Doacao() {
    }

    public Doacao(int quantidadeBolsas, Date data, Doador doador, String pacienteDestino, Campanha campanhaDestino) {
        this.quantidadeBolsas = quantidadeBolsas;
        this.data = data;
        this.doador = doador;
        this.pacienteDestino = pacienteDestino;
        this.campanhaDestino = campanhaDestino;
    }

    public int getQuantidadeBolsas() {
        return quantidadeBolsas;
    }

    public Date getData() {
        return data;
    }

    public Doador getDoador() {
        return doador;
    }

    public String getPacienteDestino() {
        return pacienteDestino;
    }

    public Campanha getCampanhaDestino() {
        return campanhaDestino;
    }

    @Override
    public String toString() {
        return "Doacao{" +
                "quantidadeBolsas=" + quantidadeBolsas +
                ", data=" + data +
                ", doador=" + doador +
                ", pacienteDestino='" + pacienteDestino + '\'' +
                '}';
    }
}
