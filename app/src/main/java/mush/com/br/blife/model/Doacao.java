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
    private String tipoSangueDoacao;
    @Ignore
    public static final String LIVRE = "LIVRE";
    @Ignore
    public static final String CAMPANHA = "CAMPANHA";

    public Doacao() {
    }

    public Doacao(int quantidadeBolsas, Date data, Doador doador, String pacienteDestino, Campanha campanhaDestino, String tipoSangueDoacao) {
        this.quantidadeBolsas = quantidadeBolsas;
        this.data = data;
        this.doador = doador;
        this.pacienteDestino = pacienteDestino;
        this.campanhaDestino = campanhaDestino;
        this.tipoSangueDoacao = tipoSangueDoacao;
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

    public String getTipoSangueDoacao() {
        return tipoSangueDoacao;
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
