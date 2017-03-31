package mush.com.br.blife.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.List;

import mush.com.br.blife.AdicionarDoacaoActivity;
import mush.com.br.blife.AdicionarSaidaActivity;
import mush.com.br.blife.MainActivity;
import mush.com.br.blife.R;
import mush.com.br.blife.adapter.SaidasAdapter;
import mush.com.br.blife.model.SaidaBolsa;


public class SaidasFragment extends Fragment {
    private RecyclerView mRvSaidas;
    private FloatingActionButton mFabSaidas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar actionBar =((MainActivity) getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Saidas de Doações");
        }
        mRvSaidas = (RecyclerView) getActivity().findViewById(R.id.rv_listar_saidas);
        mFabSaidas = (FloatingActionButton) getActivity().findViewById(R.id.fab_saidas);
        mFabSaidas.setImageDrawable( new IconicsDrawable(getActivity(), FontAwesome.Icon.faw_plus).sizeDp(20));
        mFabSaidas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdicionarSaidaActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_saidas, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        List<SaidaBolsa> saidaBolsas = SaidaBolsa.listAll(SaidaBolsa.class);
       // Log.d("DEBUG", saidaBolsas.get(0).getBolsasPorTipo().toString() );
        SaidasAdapter saidasAdapter = new SaidasAdapter(getActivity(), saidaBolsas, mRvSaidas);
        mRvSaidas.setAdapter(saidasAdapter);
        mRvSaidas.setLayoutManager( new LinearLayoutManager(getActivity()));
        mRvSaidas.setHasFixedSize(true);
    }
}
