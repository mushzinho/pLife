package mush.com.br.blife.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mush.com.br.blife.MainActivity;
import mush.com.br.blife.R;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Home");

        SharedPreferences mypreferences = getActivity().getSharedPreferences("confs", MODE_PRIVATE);
        String nomeHemocentro = mypreferences.getString("nome_hemocentro", "default");
        TextView tvNomeHemocentro = (TextView) getActivity().findViewById(R.id.nome_hemocentro);
        tvNomeHemocentro.setText(nomeHemocentro);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
