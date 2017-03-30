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
import mush.com.br.blife.model.Campanha;
import mush.com.br.blife.model.Doacao;


public class CampanhasAdapter extends RecyclerView.Adapter<CampanhasAdapter.myViewHolder> {

    private Context context;
    private List<Campanha> campanhas;
    private boolean selectionModeOn = false;
    private Set<Integer> selecionadas = new HashSet<>();
    private ActionBar mActivityBar;
    private RecyclerView mDoacoesRecyclerView;

    public CampanhasAdapter(Context ctx, List<Campanha> campanhas, RecyclerView myReciclerView ) {
        this.context = ctx;
        this.campanhas = campanhas;
        mActivityBar = ((MainActivity)context).getSupportActionBar();
        mDoacoesRecyclerView = myReciclerView;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View myView =  LayoutInflater.from(context).inflate(R.layout.campanhas_linha, parent, false);
        return new myViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {



        holder.ivCampanhaImage.setImageDrawable(new IconicsDrawable(context, FontAwesome.Icon.faw_caret_square_o_up).color(Color.BLUE).sizeDp(50));

        holder.tvCampanhaNome.setText("Campanha : " + campanhas.get(position).getNome() );
        holder.tvCampanhaTipoSangue.setText("Tipo de Sangue: " +  campanhas.get(position).getTipoSangue() );
        holder.tvCampanhaDataFinal.setText("Data Final : " + campanhas.get(position).getFim().toString() );
        holder.tvDescricaoCampanha.setText( "Descrição: " +  campanhas.get(position).getDescricaoDaCapanha());

        final int adapterPosition = holder.getAdapterPosition();

        holder.cvCampanhaLinha.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                selectionModeOn = true;
                holder.cvCampanhaLinha.setBackgroundColor(Color.GRAY);

                if(!selecionadas.contains(adapterPosition)){
                    selecionadas.add(adapterPosition);

                }else{
                    selecionadas.remove(adapterPosition);
                    holder.cvCampanhaLinha.setBackgroundColor(Color.WHITE);
                }
                if(mActivityBar != null ) mActivityBar.setTitle(selecionadas.size() + " Selecionado(s)");

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

                                                ArrayList<Campanha> doacaoTemp = new ArrayList<>();
                                                for (int id : selecionadas) {
                                                    doacaoTemp.add( campanhas.get(id) );
                                                }
                                                for (Campanha doacao : doacaoTemp){
                                                    campanhas.remove(doacao);
                                                    doacao.delete();
                                                }
                                                doacaoTemp.clear();
                                                selecionadas.clear();
                                                notifyDataSetChanged();

                                                Toast.makeText(context, "Deletado(s) com sucesso", Toast.LENGTH_SHORT).show();
                                                dialogInterface.dismiss();
                                                if(mActivityBar != null ) mActivityBar.setTitle(selecionadas.size() + " Selecionado(s)");
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
                        mActivityBar.setTitle( context.getString(R.string.donator_menu_name) );
                        mDoacoesRecyclerView.setAdapter(CampanhasAdapter.this);
                        selecionadas.clear();

                    }
                });

                return true;
            }
        });

        holder.cvCampanhaLinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectionModeOn){


                    holder.cvCampanhaLinha.setBackgroundColor(Color.GRAY);

                    if(!selecionadas.contains(adapterPosition)){
                        selecionadas.add(adapterPosition);
                    }else{
                        selecionadas.remove(adapterPosition);
                        holder.cvCampanhaLinha.setBackgroundColor(Color.WHITE);
                    }

                    if(mActivityBar != null ) mActivityBar.setTitle(selecionadas.size() + " Selecionado(s)");

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return campanhas.size();
    }

    class myViewHolder extends  RecyclerView.ViewHolder{

        private TextView tvCampanhaNome;
        private TextView tvCampanhaTipoSangue;
        private TextView tvCampanhaDataFinal;
        private TextView tvDescricaoCampanha;

        private ImageView ivCampanhaImage;
        private CardView cvCampanhaLinha;

        myViewHolder(View itemView) {
            super(itemView);
            tvCampanhaNome = (TextView) itemView.findViewById(R.id.tv_campanha_nome);
            tvCampanhaTipoSangue = (TextView) itemView.findViewById(R.id.tv_campanha_tipo_sangue);
            tvCampanhaDataFinal = (TextView) itemView.findViewById(R.id.tv_campanha_data_final);
            tvDescricaoCampanha = (TextView) itemView.findViewById(R.id.tv_campanha_descricao);

            ivCampanhaImage = (ImageView) itemView.findViewById(R.id.iv_campanha_imagem);
            cvCampanhaLinha = (CardView) itemView.findViewById(R.id.cv_campanha_linha);

        }
    }
}
