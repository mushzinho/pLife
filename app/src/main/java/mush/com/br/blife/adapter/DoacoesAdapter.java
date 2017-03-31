package mush.com.br.blife.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mush.com.br.blife.MainActivity;
import mush.com.br.blife.R;
import mush.com.br.blife.model.Doacao;


public class DoacoesAdapter extends RecyclerView.Adapter<DoacoesAdapter.myViewHolder> {

    private Context context;
    private List<Doacao> doacoes;
    private boolean selectionModeOn = false;
    private Set<Integer> selecionadas = new HashSet<>();
    private ActionBar mActivityBar;
    private RecyclerView mDoacoesRecyclerView;

    public DoacoesAdapter(Context ctx, List<Doacao> doacoes, RecyclerView myReciclerView ) {
        this.context = ctx;
        this.doacoes = doacoes;
        mActivityBar = ((MainActivity)context).getSupportActionBar();
        mDoacoesRecyclerView = myReciclerView;
      //  Doacao.deleteAll(Doacao.class);
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View myView =  LayoutInflater.from(context).inflate(R.layout.doacoes_linha, parent, false);
        return new myViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {



        holder.ivDoacaoImage.setImageDrawable(new IconicsDrawable(context, FontAwesome.Icon.faw_ambulance).color(Color.BLUE).sizeDp(50));

        holder.tvDoacaoDoadorNome.setText("Doador : " + doacoes.get(position).getDoador().getNome() );
        holder.tvPacienteDestino.setText("Destino: " +  doacoes.get(position).getPacienteDestino() );
        holder.tvNomeCampanha.setText("Campanha : " + doacoes.get(position).getCampanhaDestino() );
        holder.tvDataDoacao.setText( doacoes.get(position).getData().toString() );


        final int adapterPosition = holder.getAdapterPosition();

        holder.cvDoacaoLinha.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                selectionModeOn = true;
                holder.cvDoacaoLinha.setBackgroundColor(Color.BLACK);

                if(!selecionadas.contains(adapterPosition)){
                    selecionadas.add(adapterPosition);

                }else{
                    selecionadas.remove(adapterPosition);
                    holder.cvDoacaoLinha.setBackgroundColor(Color.DKGRAY);
                }

                ((MainActivity) context).startActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                        selectionModeOn = true;
                        actionMode.getMenuInflater().inflate(R.menu.deletar_doador, menu);
                        return true;
                    }

                    @Override
                    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                        return false;
                    }

                    @Override
                    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_doador_deletar:

                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Confirma deleção?")
                                        .setMessage("Os itens serão deletados permanentementes.")
                                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                                ArrayList<Doacao> doacaoTemp = new ArrayList<>();
                                                for (int id : selecionadas) {
                                                    doacaoTemp.add( doacoes.get(id) );
                                                }
                                                for (Doacao doacao : doacaoTemp){
                                                    doacoes.remove(doacao);
                                                    doacao.delete();
                                                }
                                                doacaoTemp.clear();
                                                selecionadas.clear();
                                                notifyDataSetChanged();

                                                Toast.makeText(context, "Deletado(s) com sucesso", Toast.LENGTH_SHORT).show();
                                                dialogInterface.dismiss();
                                            }
                                        }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).create().show();

                                return true;
                            default:
                                return false;

                        }
                    }

                    @Override
                    public void onDestroyActionMode(ActionMode actionMode) {
                        actionMode = null;
                        selectionModeOn = false;
                        mDoacoesRecyclerView.setAdapter(DoacoesAdapter.this);
                        selecionadas.clear();

                    }
                });

                return true;
            }
        });

        holder.cvDoacaoLinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectionModeOn){


                    holder.cvDoacaoLinha.setBackgroundColor(Color.BLACK);

                    if(!selecionadas.contains(adapterPosition)){
                        selecionadas.add(adapterPosition);
                    }else{
                        selecionadas.remove(adapterPosition);
                        holder.cvDoacaoLinha.setBackgroundColor(Color.DKGRAY);
                    }


                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return doacoes.size();
    }

    class myViewHolder extends  RecyclerView.ViewHolder{

        private TextView tvDoacaoDoadorNome;
        private TextView tvPacienteDestino;
        private TextView tvNomeCampanha;
        private TextView tvDataDoacao;
        private ImageView ivDoacaoImage;
        private CardView cvDoacaoLinha;

        myViewHolder(View itemView) {
            super(itemView);
            tvDoacaoDoadorNome = (TextView) itemView.findViewById(R.id.tv_doacao_doador_nome);
            tvPacienteDestino = (TextView) itemView.findViewById(R.id.tv_doacao_paciente_destino);
            tvNomeCampanha = (TextView) itemView.findViewById(R.id.tv_nome_campanha);
            tvDataDoacao = (TextView) itemView.findViewById(R.id.tv_data_doacao);
            ivDoacaoImage = (ImageView) itemView.findViewById(R.id.iv_doacao_linha);
            cvDoacaoLinha = (CardView) itemView.findViewById(R.id.cv_doacao_linha);

        }
    }
}
