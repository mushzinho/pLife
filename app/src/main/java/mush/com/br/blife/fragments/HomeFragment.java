package mush.com.br.blife.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.orm.SugarRecord;

import mush.com.br.blife.MainActivity;
import mush.com.br.blife.R;
import mush.com.br.blife.model.Doacao;

import static android.content.Context.MODE_PRIVATE;


public class HomeFragment extends Fragment {
    private TextView tvBolsasTotalEstoque;

    private TextView tvaPosHome;
    private TextView tvaNegHome;
    private TextView tvbPosHome;
    private TextView tvbNegHome;
    private TextView tvabPosHome;
    private TextView tvabNegHome;
    private TextView tvoPosHome;
    private TextView tvoNegHome;

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


        tvaPosHome = (TextView) getActivity().findViewById(R.id.tv_a_pos_home);
        tvaNegHome = (TextView) getActivity().findViewById(R.id.tv_a_neg_home);
        tvbPosHome = (TextView) getActivity().findViewById(R.id.tv_b_pos_home);
        tvbNegHome = (TextView) getActivity().findViewById(R.id.tv_b_neg_home);

        tvabPosHome = (TextView) getActivity().findViewById(R.id.tv_ab_pos_home);
        tvabNegHome = (TextView) getActivity().findViewById(R.id.tv_ab_neg_home);
        tvoPosHome = (TextView) getActivity().findViewById(R.id.tv_o_pos_home);
        tvoNegHome = (TextView) getActivity().findViewById(R.id.tv_o_neg_home);

        tvBolsasTotalEstoque = (TextView) getActivity().findViewById(R.id.tv_bolsas_total_estoque);



    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        String[] a = {"A+"};
        String[] aNeg = {"B-"};
        String[] b = {"B+"};
        String[] bNeg = {"B-"};

        String[] ab = {"AB+"};
        String[] abNeg = {"AB-"};
        String[] o = {"O+"};
        String[] oNeg = {"O-"};

        long aRes =  SugarRecord.count(Doacao.class, "tipo_sangue_doacao = ?", a );
        long aNegRes =  SugarRecord.count(Doacao.class, "tipo_sangue_doacao = ?", aNeg );
        long bRes =  SugarRecord.count(Doacao.class, "tipo_sangue_doacao = ?", b );
        long bNegRes =  SugarRecord.count(Doacao.class, "tipo_sangue_doacao = ?", bNeg );

        long abRes =  SugarRecord.count(Doacao.class, "tipo_sangue_doacao = ?", ab );
        long abNegRes =  SugarRecord.count(Doacao.class, "tipo_sangue_doacao = ?", abNeg );
        long oRes =  SugarRecord.count(Doacao.class, "tipo_sangue_doacao = ?", o );
        long oNegRes =  SugarRecord.count(Doacao.class, "tipo_sangue_doacao = ?", oNeg );

        long totalBolsas = SugarRecord.count(Doacao.class);

        tvaPosHome.setText("A+: " + aRes);
        tvaNegHome.setText("A-: " + aNegRes);
        tvbPosHome.setText("B+: " + bRes);
        tvbNegHome.setText("B-: " + bNegRes);

        tvabPosHome.setText("AB+: " + abRes);
        tvabNegHome.setText("AB-: " + abNegRes);
        tvoPosHome.setText("O+: " + oRes);
        tvoNegHome.setText("O-: " + oNegRes);

        tvBolsasTotalEstoque.setText("Bolsas Totais em Estoque: " + totalBolsas);

    }
}
