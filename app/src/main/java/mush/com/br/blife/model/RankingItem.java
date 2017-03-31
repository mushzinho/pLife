package mush.com.br.blife.model;


import java.util.Comparator;

public class RankingItem implements Comparable<RankingItem> {
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


    @Override
    public int compareTo(RankingItem rankingItem) {
        return Integer.compare(this.getDoacoes(), rankingItem.getDoacoes());
    }
}
