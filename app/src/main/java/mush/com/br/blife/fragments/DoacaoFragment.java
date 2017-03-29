package mush.com.br.blife.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mush.com.br.blife.R;
import mush.com.br.blife.adapter.DoacoesAdapter;
import mush.com.br.blife.model.Doacao;


public class DoacaoFragment extends Fragment {
    String TAG = "DEBUG";

    private RecyclerView mRvDoacoes;

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

        mRvDoacoes = (RecyclerView) getActivity().findViewById(R.id.rv_listar_doacoes);

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
