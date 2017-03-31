package mush.com.br.blife;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import mush.com.br.blife.model.Doador;


public class AdicionarDoadorActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etIdade;
    private Spinner spSexos;
    private EditText etTelefone;
    private Spinner spTiposSangue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_doador);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.add_doador_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        etNome = (EditText) findViewById(R.id.et_add_doador_nome);
        etIdade = (EditText) findViewById(R.id.et_add_doador_idade);
        spSexos = (Spinner) findViewById(R.id.sp_sexos);
        etTelefone = (EditText) findViewById(R.id.et_add_doador_contato);
        spTiposSangue = (Spinner) findViewById(R.id.sp_tipo_sangue);
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
                String nome = etNome.getText().toString();
                String idadeString = etIdade.getText().toString();
                String sexo = spSexos.getSelectedItem().toString();
                String tipoSangue = spTiposSangue.getSelectedItem().toString();
                String telefone = etTelefone.getText().toString();

                if(nome.equals("") || idadeString.equals("") || telefone.equals("")){
                    Toast.makeText(this, "Todas as informações são obrigatórias", Toast.LENGTH_LONG).show();
                }else{
                    int idade = Integer.parseInt(idadeString);
                    Doador doador = new Doador(nome, sexo, idade , tipoSangue, telefone);
                    long what = doador.save();
                    Toast.makeText(this, "Doador Salvo" + what, Toast.LENGTH_LONG).show();
                    finish();

                }
                break;
            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
