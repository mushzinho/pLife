package mush.com.br.blife.model;


public class BolsasEmEstoque {
    private int quantidade = 0;


    public BolsasEmEstoque(){
    }
    public BolsasEmEstoque(int quantidade) {
        this.quantidade += quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
