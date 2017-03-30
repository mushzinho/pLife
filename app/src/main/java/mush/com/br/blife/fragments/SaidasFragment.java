package mush.com.br.blife.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mush.com.br.blife.R;
import mush.com.br.blife.adapter.SaidasAdapter;
import mush.com.br.blife.model.SaidaBolsa;


public class SaidasFragment extends Fragment {
    private RecyclerView mRvSaidas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRvSaidas = (RecyclerView) getActivity().findViewById(R.id.rv_listar_saidas);
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
