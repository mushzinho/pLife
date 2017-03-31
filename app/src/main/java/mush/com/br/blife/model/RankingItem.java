package mush.com.br.blife.model;


public class RankingItem {
    private String nome;
    private int Doacoes;

    public RankingItem(String nome, int doacoes) {
        this.nome = nome;
        Doacoes = doacoes;
    }

    public String getNome() {
        return nome;
    }

    public int getDoacoes() {
        return Doacoes;
    }
}
