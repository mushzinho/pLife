package mush.com.br.blife.model;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.Date;

public class Doacao extends SugarRecord {

    private int quantidadeBolsas;
    private Date data;
    private Doador doador;
    private String pacienteDestino;
    @Ignore
    public static final String LIVRE = "LIVRE";

    public Doacao() {
    }

    public Doacao(int quantidadeBolsas, Date data, Doador doador, String pacienteDestino) {
        this.quantidadeBolsas = quantidadeBolsas;
        this.data = data;
        this.doador = doador;
        this.pacienteDestino = pacienteDestino;
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
