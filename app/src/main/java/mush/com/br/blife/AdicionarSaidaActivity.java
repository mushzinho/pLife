package mush.com.br.blife;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import mush.com.br.blife.model.BolsasPorTipo;
import mush.com.br.blife.model.Doador;
import mush.com.br.blife.model.SaidaBolsa;

public class AdicionarSaidaActivity extends AppCompatActivity {

    private TextView mHospitalDestino;
    private EditText maPos;
    private EditText maNeg;
    private EditText mbPos;
    private EditText mbNeg;
    private EditText mabPos;
    private EditText mabNeg;
    private EditText moPos;
    private EditText moNeg;
    private boolean saidaValida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_saida);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.add_saida_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mHospitalDestino = (TextView) findViewById(R.id.et_hospital_destino);
        maPos = (EditText) findViewById(R.id.et_a_pos);
        maNeg = (EditText) findViewById(R.id.et_a_neg);
        mbPos = (EditText) findViewById(R.id.et_b_pos);
        mbNeg = (EditText) findViewById(R.id.et_b_neg);
        mabPos = (EditText) findViewById(R.id.et_ab_pos);
        mabNeg = (EditText) findViewById(R.id.et_ab_neg);
        moPos = (EditText) findViewById(R.id.et_o_pos);
        moNeg = (EditText) findViewById(R.id.et_o_neg);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.add_doador_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_doador_salvar:

                saidaValida = false;
                BolsasPorTipo bolsasPorTipo = new BolsasPorTipo();

                String hopitalDestino = mHospitalDestino.getText().toString();
                String aPos = maPos.getText().toString();
                String aNeg = maNeg.getText().toString();
                String bPos = mbPos.getText().toString();
                String bNeg = mbNeg.getText().toString();
                String abPos = mabPos.getText().toString();
                String abNeg = mabNeg.getText().toString();
                String oPos = moPos.getText().toString();
                String oNeg = moNeg.getText().toString();

                if(!aPos.equals("")){ bolsasPorTipo.setaPos(Integer.parseInt(aPos)); saidaValida = true;}
                if(!aNeg.equals("")){ bolsasPorTipo.setaNeg(Integer.parseInt(aNeg)); saidaValida = true;}
                if(!bPos.equals("")){ bolsasPorTipo.setbPos(Integer.parseInt(bPos)); saidaValida = true;}
                if(!bNeg.equals("")){ bolsasPorTipo.setbNeg(Integer.parseInt(bNeg)); saidaValida = true;}
                if(!abPos.equals("")){ bolsasPorTipo.setAbPos(Integer.parseInt(abPos)); saidaValida = true;}
                if(!abNeg.equals("")){ bolsasPorTipo.setAbNeg(Integer.parseInt(abNeg)); saidaValida = true;}
                if(!oPos.equals("")){ bolsasPorTipo.setoPos(Integer.parseInt(oPos)); saidaValida = true;}
                if(!oNeg.equals("")){ bolsasPorTipo.setoNeg(Integer.parseInt(oNeg)); saidaValida = true;}

                if(hopitalDestino.equals("") || !saidaValida){

                    Toast.makeText(this, "Hospital ou Quantidades em branco !", Toast.LENGTH_LONG).show();

                }else{
                    Log.d("DEBUG", "AQUI TA INDO" + bolsasPorTipo.getaPos() );
                    new SaidaBolsa(hopitalDestino, new Date(), bolsasPorTipo ).save();

                    Toast.makeText(this, "Saida Cadastrada !", Toast.LENGTH_LONG).show();
                }



                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
