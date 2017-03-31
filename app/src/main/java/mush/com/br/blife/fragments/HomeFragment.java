package mush.com.br.blife.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mush.com.br.blife.R;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {

    private String mNomeHemocentro;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences mypreferences = getActivity().getSharedPreferences("confs", MODE_PRIVATE);
        mNomeHemocentro = mypreferences.getString("nome_hemocentro", "default");
        TextView nomeHemocentro = (TextView) getActivity().findViewById(R.id.nome_hemocentro);
        nomeHemocentro.setText(mNomeHemocentro);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
