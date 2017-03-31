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

import java.util.ArrayList;
import java.util.List;

import mush.com.br.blife.R;
import mush.com.br.blife.adapter.RankingAdapter;
import mush.com.br.blife.model.Doacao;
import mush.com.br.blife.model.Doador;
import mush.com.br.blife.model.RankingItem;


public class RankingFragment extends Fragment {
    private RecyclerView mRvRanking;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRvRanking = (RecyclerView) getActivity().findViewById(R.id.rv_lista_ranking);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ranking, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<RankingItem> rankingItens = new ArrayList<>();
        rankingItens.add(new RankingItem("Pablo", 20));
        rankingItens.add(new RankingItem("Fernando", 28));
        RankingAdapter rankingAdapter = new RankingAdapter(getActivity(), rankingItens);

       // List<Doador> doador = Doador.findWithQuery(Doador.class, "Select * from Doador");
        List<Doador> doadores = Doador.listAll(Doador.class);
        for (Doador doador: doadores) {

            //todo

        }


        mRvRanking.setAdapter(rankingAdapter);
        mRvRanking.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRanking.setHasFixedSize(true);
    }
}
