package mush.com.br.blife.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import mush.com.br.blife.R;
import mush.com.br.blife.model.Doador;


public class DoadoresAdapter extends RecyclerView.Adapter<DoadoresAdapter.myViewHolder> {

    private Context context;
    private List<Doador> doadores;

    public DoadoresAdapter(Context ctx, List<Doador> doadores ) {
        this.context = ctx;
        this.doadores = doadores;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View myView =  LayoutInflater.from(context).inflate(R.layout.doadores_linha,parent, false);
        return new myViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.tvNome.setText( doadores.get(position).getNome());

    }

    @Override
    public int getItemCount() {
        return doadores.size();
    }

    class myViewHolder extends  RecyclerView.ViewHolder{

        private TextView tvNome;

        myViewHolder(View itemView) {
            super(itemView);
            tvNome = (TextView) itemView.findViewById(R.id.tv_doadores_linha_nome);

        }
    }
}
