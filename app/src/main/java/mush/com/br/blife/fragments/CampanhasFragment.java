package mush.com.br.blife.fragments;

import android.content.Intent;
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
import com.mikepenz.iconics.IconicsDrawable;

import java.util.List;

import mush.com.br.blife.AdicionarCampanhaActivity;
import mush.com.br.blife.AdicionarSaidaActivity;
import mush.com.br.blife.MainActivity;
import mush.com.br.blife.R;
import mush.com.br.blife.adapter.CampanhasAdapter;
import mush.com.br.blife.model.Campanha;


public class CampanhasFragment extends Fragment {
    private RecyclerView mRvListarCampanhas;
    private FloatingActionButton mFabCampanhas;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ActionBar actionBar =((MainActivity) getActivity()).getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("Campanhas");
        }

        mRvListarCampanhas = (RecyclerView) getActivity().findViewById(R.id.rv_listar_campanhas);

        mFabCampanhas = (FloatingActionButton) getActivity().findViewById(R.id.fab_campanhas);
        mFabCampanhas.setImageDrawable( new IconicsDrawable(getActivity(), FontAwesome.Icon.faw_plus).sizeDp(20));
        mFabCampanhas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdicionarCampanhaActivity.class);
                getActivity().startActivity(intent);
            }
        });

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
