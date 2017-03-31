package mush.com.br.blife.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.iconics.IconicsDrawable;


import java.util.List;

import mush.com.br.blife.AdicionarDoadorActivity;
import mush.com.br.blife.MainActivity;
import mush.com.br.blife.R;
import mush.com.br.blife.adapter.DoadoresAdapter;
import mush.com.br.blife.custom.Fab;
import mush.com.br.blife.model.Doador;


public class DoadorFragment extends Fragment {

    private RecyclerView mDoadoresRecyclerView;
    private FloatingActionButton mFabDoador;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.donator_menu_name));


        mDoadoresRecyclerView = (RecyclerView) getActivity().findViewById(R.id.rv_doadores);
        mFabDoador = (FloatingActionButton) getActivity().findViewById(R.id.fab_doador);
        mFabDoador.setImageDrawable( new IconicsDrawable(getActivity(), FontAwesome.Icon.faw_plus).sizeDp(20));
        mFabDoador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdicionarDoadorActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Doador> doadores = Doador.listAll(Doador.class);
        //Log.d("PAPA", "" + doadores.get(0).getId());
        RecyclerView.Adapter doadoresAdapter = new DoadoresAdapter(getActivity(), doadores, mDoadoresRecyclerView);
        mDoadoresRecyclerView.setAdapter(doadoresAdapter);
        mDoadoresRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDoadoresRecyclerView.setHasFixedSize(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return  inflater.inflate(R.layout.fragment_doador, container, false);

    }

}
