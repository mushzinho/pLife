package mush.com.br.blife.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import mush.com.br.blife.fragments.DoadorFragment;
import mush.com.br.blife.model.Doador;


public class DoadoresAdapter extends RecyclerView.Adapter<DoadoresAdapter.myViewHolder> {

    private Context context;
    private List<Doador> doadores;
    private boolean selectionModeOn = false;
    private Set<Integer> selecionados = new HashSet<>();
    private ActionBar mActivityBar;

    public DoadoresAdapter(Context ctx, List<Doador> doadores ) {
        this.context = ctx;
        this.doadores = doadores;
        mActivityBar = ((MainActivity)context).getSupportActionBar();
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View myView =  LayoutInflater.from(context).inflate(R.layout.doadores_linha, parent, false);
        return new myViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {

        String sexo = doadores.get(position).getSexo();
        if(sexo.equals("Masculino")){
            holder.ivSexo.setImageDrawable(new IconicsDrawable(context, FontAwesome.Icon.faw_male).color(Color.BLUE).sizeDp(50));
        }else{
            holder.ivSexo.setImageDrawable(new IconicsDrawable(context, FontAwesome.Icon.faw_female).color(Color.BLUE).sizeDp(50));
        }

        holder.tvNome.setText( context.getString(R.string.add_doador_nome_hint)+ ": " + doadores.get(position).getNome());
        holder.tvIdade.setText(context.getString(R.string.add_doador_idade_hint) + ": " +  doadores.get(position).getIdade());
        holder.tvContato.setText(context.getString(R.string.add_doador_telefone) + ": " + doadores.get(position).getContato());
        holder.tvTipoSangue.setText(doadores.get(position).getTipoDeSangue());
        final int adapterPosition = holder.getAdapterPosition();

        holder.cvDoadorLinha.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                selectionModeOn = true;
                holder.cvDoadorLinha.setBackgroundColor(Color.GRAY);

                if(!selecionados.contains(adapterPosition)){
                    selecionados.add(adapterPosition);

                }else{
                    selecionados.remove(adapterPosition);
                    holder.cvDoadorLinha.setBackgroundColor(Color.WHITE);
                }
                if(mActivityBar != null ) mActivityBar.setTitle(selecionados.size() + " Selecionado(s)");

                ((MainActivity) context).startActionMode(new ActionMode.Callback() {
                    @Override
                    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
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
                                for (int id : selecionados) {
                                    Log.d("LOGA", ""+id);
                                    //doadores.get(id).delete();
                                    //doadores.remove( id   );
                                    //notifyItemRemoved(id);

                                }
                                //((DoadorFragment)((MainActivity) context).getmSelectedFrament() ).getmDoadoresRecyclerView().setAdapter(DoadoresAdapter.this);
                               // notifyDataSetChanged();
                                Toast.makeText(context, "Deletados com sucesso", Toast.LENGTH_SHORT).show();
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
                        ((DoadorFragment)((MainActivity) context).getmSelectedFrament() ).getmDoadoresRecyclerView().setAdapter(DoadoresAdapter.this);
                        selecionados.clear();

                    }
                });

                return true;
            }
        });

        holder.cvDoadorLinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectionModeOn){


                    holder.cvDoadorLinha.setBackgroundColor(Color.GRAY);

                    if(!selecionados.contains(adapterPosition)){
                        selecionados.add(adapterPosition);
                    }else{
                        selecionados.remove(adapterPosition);
                        holder.cvDoadorLinha.setBackgroundColor(Color.WHITE);
                    }

                    if(mActivityBar != null ) mActivityBar.setTitle(selecionados.size() + " Selecionado(s)");

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return doadores.size();
    }

    class myViewHolder extends  RecyclerView.ViewHolder{

        private TextView tvNome;
        private TextView tvIdade;
        private TextView tvContato;
        private ImageView ivSexo;
        private TextView tvTipoSangue;
        private CardView cvDoadorLinha;

        myViewHolder(View itemView) {
            super(itemView);
            tvNome = (TextView) itemView.findViewById(R.id.tv_doadores_linha_nome);
            tvIdade = (TextView) itemView.findViewById(R.id.tv_doadores_linha_idade);
            tvContato = (TextView) itemView.findViewById(R.id.tv_doadores_linha_contato);
            ivSexo = (ImageView) itemView.findViewById(R.id.iv_doador_linha_sexo);
            tvTipoSangue = (TextView) itemView.findViewById(R.id.tv_tipo_sangue);
            cvDoadorLinha = (CardView) itemView.findViewById(R.id.cv_doador_linha);

        }
    }
}
