package mush.com.br.blife;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Date;

import mush.com.br.blife.model.Campanha;
import mush.com.br.blife.model.Doador;

public class AdicionarCampanhaActivity extends AppCompatActivity {
    private EditText mNomeCampanha;
    private EditText mDescricaoCampanha;
    private Spinner mTipoSangueCampanha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_campanha);


        Toolbar toolbar  = (Toolbar) findViewById(R.id.add_campanha_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mNomeCampanha = (EditText) findViewById(R.id.et_nome_campanha);
        mDescricaoCampanha = (EditText) findViewById(R.id.et_descricao_campanha);
        mTipoSangueCampanha = (Spinner) findViewById(R.id.sp_tipo_sangue_campanha);

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

                String nomeCampanha = mNomeCampanha.getText().toString();
                String descricaoCampanha = mDescricaoCampanha.getText().toString();
                String tipoSangueCampanha = mTipoSangueCampanha.getSelectedItem().toString();

                if(nomeCampanha.equals("") || descricaoCampanha.equals("") ){
                    Toast.makeText(this, "Todos os campos são obrigatórios", Toast.LENGTH_SHORT).show();
                }else{
                    new Campanha(nomeCampanha, tipoSangueCampanha, new Date(), new Date(), descricaoCampanha).save();
                    Toast.makeText(this, "Campanha Salva", Toast.LENGTH_SHORT).show();
                }
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
