package mush.com.br.blife.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

import java.util.List;

import mush.com.br.blife.AdicionarDoacaoActivity;
import mush.com.br.blife.MainActivity;
import mush.com.br.blife.R;
import mush.com.br.blife.adapter.DoacoesAdapter;
import mush.com.br.blife.model.Doacao;


public class DoacaoFragment extends Fragment {
    String TAG = "DEBUG";

    private RecyclerView mRvDoacoes;
    private FloatingActionButton mFabDoacao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_doacao, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar actionBar =((MainActivity) getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Doação");
        }

        mRvDoacoes = (RecyclerView) getActivity().findViewById(R.id.rv_listar_doacoes);
        mFabDoacao = (FloatingActionButton) getActivity().findViewById(R.id.fab_doacao);
        mFabDoacao.setImageDrawable( new IconicsDrawable(getActivity(), FontAwesome.Icon.faw_plus).sizeDp(20));
        mFabDoacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdicionarDoacaoActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();

        List<Doacao> doacoes = Doacao.listAll(Doacao.class);
        DoacoesAdapter doacoesAdapter = new DoacoesAdapter(getActivity(), doacoes, mRvDoacoes );
        mRvDoacoes.setAdapter(doacoesAdapter);
        mRvDoacoes.setLayoutManager( new LinearLayoutManager(getActivity()));
        mRvDoacoes.setHasFixedSize(true);
    }
}
