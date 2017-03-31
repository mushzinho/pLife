package mush.com.br.blife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mush.com.br.blife.R;
import mush.com.br.blife.model.RankingItem;


public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.myViewHolder> {
    private Context context;
    private List<RankingItem> rankingItems;

    public RankingAdapter(Context context, List<RankingItem> rankingItems) {
        this.context = context;
        this.rankingItems = rankingItems;
    }

    @Override
    public RankingAdapter.myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(context).inflate(R.layout.ranking_linha, parent, false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RankingAdapter.myViewHolder holder, int position) {

        holder.tvNomeDoador.setText("Doador: " + rankingItems.get(position).getNome());
        holder.tvQuantidadeDoacoes.setText("Doações: " + rankingItems.get(position).getDoacoes());
    }

    @Override
    public int getItemCount() {
        return rankingItems.size();
    }
    class myViewHolder extends RecyclerView.ViewHolder {
        TextView tvNomeDoador;
        TextView tvQuantidadeDoacoes;

        myViewHolder(View itemView) {
            super(itemView);
            tvNomeDoador = (TextView) itemView.findViewById(R.id.tv_ranking_doador_name);
            tvQuantidadeDoacoes = (TextView) itemView.findViewById(R.id.tv_ranking_quantidade_doacoes);
        }
    }
}
