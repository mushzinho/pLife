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
import mush.com.br.blife.adapter.CampanhasAdapter;
import mush.com.br.blife.model.Campanha;


public class CampanhasFragment extends Fragment {
    private RecyclerView mRvListarCampanhas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRvListarCampanhas = (RecyclerView) getActivity().findViewById(R.id.rv_listar_campanhas);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_campanhas, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Campanha> campanhas = Campanha.listAll(Campanha.class);
        CampanhasAdapter campanhasAdapter = new CampanhasAdapter(getActivity(), campanhas, mRvListarCampanhas);
        mRvListarCampanhas.setAdapter(campanhasAdapter);
        mRvListarCampanhas.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvListarCampanhas.setHasFixedSize(true);

    }
}
