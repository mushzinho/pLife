package mush.com.br.blife;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import mush.com.br.blife.model.Campanha;
import mush.com.br.blife.model.Doacao;
import mush.com.br.blife.model.Doador;

public class AdicionarDoacaoActivity extends AppCompatActivity {

    private AutoCompleteTextView mSelecionarDoador;
    private AutoCompleteTextView mSelecionarCampanha;
    private boolean doadorSelecionado = false;
    private boolean campanhaSelecionada = false;
    private int mSelectedDestinoDoacao;
    private EditText mQuantidadeDeBolsas;
    private EditText mPessoaDestino;
    private Doador doadorSelecionadoObjeto;
    private Campanha campanhaSelecionadaObjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_doacao);

        Toolbar toolbar  = (Toolbar) findViewById(R.id.add_doacao_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

       // new Doador("Pablo", "Masculino",10,"A+","9999999" ).save();
       // new Campanha("Campanha", "A+", new Date(), new Date(), "Campanha Legal" ).save();

        mSelecionarDoador = (AutoCompleteTextView) findViewById(R.id.actv_selecionar_doador);
        mSelecionarCampanha = (AutoCompleteTextView) findViewById(R.id.actv_selecionar_campanha);
        RadioGroup mDestinoDoacaoRadio = (RadioGroup) findViewById(R.id.rg_tipo_campanha);
        mQuantidadeDeBolsas = (EditText) findViewById(R.id.et_quantidade_bolsas);
        mPessoaDestino = (EditText) findViewById(R.id.et_pessoa_destino);





        mDestinoDoacaoRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int radioSelectedId = radioGroup.getCheckedRadioButtonId();
                View radionButtonSelected = radioGroup.findViewById(radioSelectedId);
                mSelectedDestinoDoacao = radioGroup.indexOfChild(radionButtonSelected);


                View doarParaPessoaLinearLayout = findViewById(R.id.doar_para_pessoa);
                View doarParaCampanhaLinearLayout = findViewById(R.id.doar_para_campanha);
                if(mSelectedDestinoDoacao == 0){
                    doarParaPessoaLinearLayout.setVisibility(View.VISIBLE);
                    doarParaCampanhaLinearLayout.setVisibility(View.GONE);
                }else if(mSelectedDestinoDoacao == 1){
                    doarParaCampanhaLinearLayout.setVisibility(View.VISIBLE);
                    doarParaPessoaLinearLayout.setVisibility(View.GONE);
                }else if (mSelectedDestinoDoacao == 2){
                    doarParaCampanhaLinearLayout.setVisibility(View.GONE);
                    doarParaPessoaLinearLayout.setVisibility(View.GONE);
                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();


        List<Doador> doadores = Doador.listAll(Doador.class);
        final ArrayAdapter<Doador> adapter = new ArrayAdapter<Doador>(this,
                android.R.layout.simple_dropdown_item_1line, doadores);

        mSelecionarDoador.setAdapter(adapter);
        mSelecionarDoador.setThreshold(1);
        mSelecionarDoador.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                doadorSelecionado = true;
                doadorSelecionadoObjeto = (Doador) adapterView.getItemAtPosition(i);
            }
        });
        mSelecionarDoador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
               if(doadorSelecionado){
                   doadorSelecionado = false;
                   mSelecionarDoador.setText("");

               }
            }
        });

        List<Campanha> campanhas = Campanha.listAll(Campanha.class);
        final ArrayAdapter<Campanha> adapterCampanha = new ArrayAdapter<Campanha>(this,
                android.R.layout.simple_dropdown_item_1line, campanhas);
        mSelecionarCampanha.setAdapter(adapterCampanha);
        mSelecionarCampanha.setThreshold(1);
        mSelecionarCampanha.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                campanhaSelecionada = true;
                campanhaSelecionadaObjeto = (Campanha) adapterView.getItemAtPosition(i);
            }
        });
        mSelecionarCampanha.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if(campanhaSelecionada){
                    campanhaSelecionada = false;
                    mSelecionarCampanha.setText("");
                }
            }
        });

    }

    public boolean SalvarDocao(){

        int quantidadeDeBolsas =  Integer.parseInt( mQuantidadeDeBolsas.getText().toString() );
        if(mSelectedDestinoDoacao == 0){
            String pacienteDestino = mPessoaDestino.getText().toString();
            long res = new Doacao(quantidadeDeBolsas, new Date(), doadorSelecionadoObjeto, pacienteDestino, null).save();
            if(res > 0) return true;
        }else  if(mSelectedDestinoDoacao == 1){
            long res = new Doacao(quantidadeDeBolsas, new Date(), doadorSelecionadoObjeto, Doacao.LIVRE, campanhaSelecionadaObjeto).save();
            if(res > 0) return true;
        }else {
            long res = new Doacao(quantidadeDeBolsas, new Date(), doadorSelecionadoObjeto, Doacao.CAMPANHA, null).save();
            if(res > 0) return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_doador_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_doador_salvar:
                if(SalvarDocao() ){
                    Toast.makeText(this, "Doação Realizada", Toast.LENGTH_SHORT).show();
                }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
