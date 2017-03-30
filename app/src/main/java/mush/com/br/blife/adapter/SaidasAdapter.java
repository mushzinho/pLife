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
import mush.com.br.blife.model.SaidaBolsa;


public class SaidasAdapter extends RecyclerView.Adapter<SaidasAdapter.myViewHolder> {

    private Context context;
    private List<SaidaBolsa> saidasBolsas;
    private boolean selectionModeOn = false;
    private Set<Integer> selecionados = new HashSet<>();
    private ActionBar mActivityBar;
    private RecyclerView mSaidaRecyclerView;

    public SaidasAdapter(Context ctx, List<SaidaBolsa> saidasBolsas, RecyclerView myReciclerView ) {
        this.context = ctx;
        this.saidasBolsas = saidasBolsas;
        mActivityBar = ((MainActivity)context).getSupportActionBar();
        mSaidaRecyclerView = myReciclerView;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View myView =  LayoutInflater.from(context).inflate(R.layout.saidas_linha, parent, false);
        return new myViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {


        holder.ivSaidasImagem.setImageDrawable(new IconicsDrawable(context, FontAwesome.Icon.faw_chevron_left).color(Color.BLUE).sizeDp(50));

        holder.tvNomeHospital.setText("Hospital : " + saidasBolsas.get(position).getHospital());
        if(saidasBolsas.get(position).getBolsasPorTipo() != null){

            holder.tvaPos.setText("A+ " +  saidasBolsas.get(position).getBolsasPorTipo().getaPos() );
            holder.tvaNeg.setText("A- : " + saidasBolsas.get(position).getBolsasPorTipo().getaNeg() );
            holder.tvbPos.setText("B+ : " + saidasBolsas.get(position).getBolsasPorTipo().getbPos() );
            holder.tvbNeg.setText("B- : " + saidasBolsas.get(position).getBolsasPorTipo().getbNeg() );
            holder.tvabPos.setText("AB+ : " + saidasBolsas.get(position).getBolsasPorTipo().getAbPos() );
            holder.tvabNeg.setText("AB- : " + saidasBolsas.get(position).getBolsasPorTipo().getAbNeg() );
            holder.tvoPos.setText("O+ : " + saidasBolsas.get(position).getBolsasPorTipo().getoPos() );
            holder.tvoNeg.setText("O- : " + saidasBolsas.get(position).getBolsasPorTipo().getoNeg() );
        }



        final int adapterPosition = holder.getAdapterPosition();

        holder.dvSaidaLinha.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                selectionModeOn = true;
                holder.dvSaidaLinha.setBackgroundColor(Color.GRAY);

                if(!selecionados.contains(adapterPosition)){
                    selecionados.add(adapterPosition);

                }else{
                    selecionados.remove(adapterPosition);
                    holder.dvSaidaLinha.setBackgroundColor(Color.WHITE);
                }
                if(mActivityBar != null ) mActivityBar.setTitle(selecionados.size() + " Selecionado(s)");

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

                                                ArrayList<SaidaBolsa> doacaoTemp = new ArrayList<>();
                                                for (int id : selecionados) {
                                                    doacaoTemp.add( saidasBolsas.get(id) );
                                                }
                                                for (SaidaBolsa saidaBolsa : doacaoTemp){
                                                    saidasBolsas.remove(saidaBolsa);
                                                    saidaBolsa.delete();
                                                }
                                                doacaoTemp.clear();
                                                selecionados.clear();
                                                notifyDataSetChanged();

                                                Toast.makeText(context, "Deletado(s) com sucesso", Toast.LENGTH_SHORT).show();
                                                dialogInterface.dismiss();
                                                if(mActivityBar != null ) mActivityBar.setTitle(selecionados.size() + " Selecionado(s)");
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
                        mSaidaRecyclerView.setAdapter(SaidasAdapter.this);
                        selecionados.clear();

                    }
                });

                return true;
            }
        });

        holder.dvSaidaLinha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectionModeOn){


                    holder.dvSaidaLinha.setBackgroundColor(Color.GRAY);

                    if(!selecionados.contains(adapterPosition)){
                        selecionados.add(adapterPosition);
                    }else{
                        selecionados.remove(adapterPosition);
                        holder.dvSaidaLinha.setBackgroundColor(Color.WHITE);
                    }

                    if(mActivityBar != null ) mActivityBar.setTitle(selecionados.size() + " Selecionado(s)");

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return saidasBolsas.size();
    }

    class myViewHolder extends  RecyclerView.ViewHolder{

        private TextView tvNomeHospital;
        private TextView tvaPos;
        private TextView tvaNeg;
        private TextView tvbPos;
        private TextView tvbNeg;
        private TextView tvabPos;
        private TextView tvabNeg;
        private TextView tvoPos;
        private TextView tvoNeg;

        private ImageView ivSaidasImagem;
        private CardView dvSaidaLinha;

        myViewHolder(View itemView) {
            super(itemView);

            tvNomeHospital = (TextView) itemView.findViewById(R.id.tv_saidas_hospital);
            tvaPos = (TextView) itemView.findViewById(R.id.tv_a_pos);
            tvaNeg = (TextView) itemView.findViewById(R.id.tv_a_neg);
            tvbPos = (TextView) itemView.findViewById(R.id.tv_b_pos);
            tvbNeg = (TextView) itemView.findViewById(R.id.tv_b_neg);

            tvabPos = (TextView) itemView.findViewById(R.id.tv_ab_pos);
            tvabNeg = (TextView) itemView.findViewById(R.id.tv_ab_neg);
            tvoPos = (TextView) itemView.findViewById(R.id.tv_o_pos);
            tvoNeg = (TextView) itemView.findViewById(R.id.tv_o_neg);


            ivSaidasImagem = (ImageView) itemView.findViewById(R.id.iv_saidas);
            dvSaidaLinha = (CardView) itemView.findViewById(R.id.cv_saidas_linha);

        }
    }
}
