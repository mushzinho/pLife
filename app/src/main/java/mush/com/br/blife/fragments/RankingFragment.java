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

import com.orm.util.Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mush.com.br.blife.MainActivity;
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
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Ranking de Doadores");
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


        List<Doador> doadores = Doador.listAll(Doador.class);
        for (Doador doador: doadores) {

            RankingItem rankingItem = new RankingItem(doador.getNome(), doador.getDoacoes().size());
            rankingItens.add(rankingItem);

        }
        Collections.sort(rankingItens, Collections.reverseOrder());

        RankingAdapter rankingAdapter = new RankingAdapter(getActivity(), rankingItens);

        mRvRanking.setAdapter(rankingAdapter);
        mRvRanking.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvRanking.setHasFixedSize(true);
    }
}
