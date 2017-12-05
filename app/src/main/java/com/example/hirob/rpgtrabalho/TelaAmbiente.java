package com.example.hirob.rpgtrabalho;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hirob.rpgtrabalho.db.DataBaseHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class TelaAmbiente extends AppCompatActivity {
    TextView texto;
    Personagem personagem;
    Personagem inimigo;
    Button btnCombate;
    Button btnSim;
    Button btnNao;
    DataBaseHelper mBancoDeDados;
    Button btnLoja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ambiente);


        texto = (TextView) findViewById(R.id.textViewLogs);
        btnCombate = (Button) findViewById(R.id.buttonCombate);
        btnSim = (Button) findViewById(R.id.buttonSim);
        btnNao = (Button) findViewById(R.id.buttonNao);
        btnLoja = (Button) findViewById(R.id.buttonLoja);

        //Verificação personagem
        if(getIntent().getExtras()!=null){
            Intent it =getIntent();
            if (it.getStringExtra("banco").equals("1")) {
                mBancoDeDados = new DataBaseHelper(this);
                personagem = mBancoDeDados.pegarPersonagemPronto();
            }
       } else {

            personagem = new Personagem();
            personagem.setAtkMax(10);
            personagem.setAtkMin(5);
            personagem.setDefense(0);
            personagem.setGold(0);
            personagem.setJogavel(true);
            personagem.setNmPersonagem("Hiroshi");
            personagem.setHpTotal(50);
            personagem.setHpAtual(50);

            inicializarBancoDeDados();
            mBancoDeDados.salvarPersonagem(personagem);
        }
        btnLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaAmbiente.this, TelaLoja.class));
            }
        });


    }

    public void clickBotao(View v) {

        switch (v.getId()) {
            case R.id.buttonCombate: // Se for o botão principal, abre o switch dele

                String btnText = (String) btnCombate.getText();
                switch (btnText) {
                    case "explorar": //Se ele explorar, vai procurar um inimigo novo
                        inimigoNovo();
                        break;
                    case "atacar":
                        batalha();
                        break;
                    case "confirmar":
                        btnCombate.setText("explorar");
                        btnLoja.setVisibility(View.VISIBLE);
                        texto.setText("Olá Aventureiro! O que você deseja fazer?");
                        break;
                }
                break;
            case R.id.buttonSim:
                aceitarCombate();
                break;
            case R.id.buttonNao:
                break;

        }
    }

    public void inimigoNovo() {
        btnCombate.setVisibility(View.GONE);
        btnLoja.setVisibility(View.GONE);
        List<Personagem> inimigoLista = mBancoDeDados.allInimigo();
        int random = CalculosRpg.inimigoNovo(inimigoLista.size());
        inimigo = inimigoLista.get(random);


        texto.setText("Voce encontrou o " + inimigo.getNmPersonagem() + "." +
                " Voce deseja batalhar com ele?");
        btnSim.setVisibility(View.VISIBLE);
        btnNao.setVisibility(View.VISIBLE);
    }

    public void aceitarCombate() {
        btnSim.setVisibility(View.GONE);
        btnNao.setVisibility(View.GONE);
        btnCombate.setVisibility(View.VISIBLE);
        btnCombate.setText("atacar");

        personagem = mBancoDeDados.pegarPersonagemPronto();

        texto.setText("Voce está com " + personagem.getHpAtual() + " de vida." +
                "\nO que você deseja fazer?");
    }

    public void batalha() {
        personagem = mBancoDeDados.pegarPersonagemPronto();

        int ataquePersonagem = CalculosRpg.ataque(personagem.getAtkMin(), personagem.getAtkMax(), personagem.getDefense());
        int ataqueInimigo = CalculosRpg.ataque(inimigo.getAtkMin(), inimigo.getAtkMax(), inimigo.getDefense());
        int hpAtualNovo = personagem.getHpAtual() - ataqueInimigo;
        int hpAtualNovoInimigo = inimigo.getHpAtual() - ataquePersonagem;

        if (hpAtualNovo > 0 && hpAtualNovoInimigo > 0) {

            personagem.setHpAtual(hpAtualNovo);
            inimigo.setHpAtual(hpAtualNovoInimigo);

            int hpAtualPersonagem = personagem.getHpAtual();
            int hpAtualInimigo = inimigo.getHpAtual();
            texto.setText("Você sofreu " + ataqueInimigo + " dano e está com " + hpAtualPersonagem +
                    "\n Voce deu " + ataquePersonagem + " dano e ele está com " + hpAtualInimigo);

        } else {
            //Se a sua vida tiver menor que 0 vc morreu
            if (hpAtualNovo <= 0) {
                texto.setText("Você Morreu");
            } else {
                //A hp do inimigo está menor que 0, então acabou a luta
                personagem.setHpAtual(hpAtualNovo);
                int hpAtualPersonagem = personagem.getHpAtual();
                int dinheiroInimigo = inimigo.getGold();
                personagem.setGold(personagem.getGold() + dinheiroInimigo);
                texto.setText("Parabéns, você matou o inimigo." +
                       // "\n\nVocê sofreu " + ataqueInimigo + " dano e está com " + hpAtualPersonagem +
                        "\n\nVocê está com está com " + hpAtualPersonagem +" de vida"+
                        "\nVocê recebeu " + dinheiroInimigo + " gold e está com " + personagem.getGold() + " ao total."
                );
                btnCombate.setText("confirmar");


                mBancoDeDados.salvarPersonagem(personagem);
                mBancoDeDados.pegarPersonagemPronto().getHpAtual();

                Context contexto = getApplicationContext();
                int duracao = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(contexto, mBancoDeDados.pegarPersonagemPronto().getHpAtual() + "", duracao);
                toast.show();


            }
        }
    }

    public void ambientePrincipal() {
        btnCombate.setText("explorar");
    }


    private void inicializarBancoDeDados() {
        mBancoDeDados = new DataBaseHelper(this);


        File database = getApplicationContext().getDatabasePath("Personagem");
        if (database.exists() == false) {
            mBancoDeDados.getReadableDatabase();
            if (copiaBanco(this)) {
                Context contexto = getApplicationContext();
                int duracao = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(contexto, "Copiado com sucesso", duracao);
                toast.show();
            } else {
                Context contexto = getApplicationContext();
                int duracao = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(contexto, "Não copiou", duracao);
                toast.show();
            }
        }

        Context contexto = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto, mBancoDeDados.allPessoa().get(1).getNmPersonagem() + "", duracao);
        toast.show();
    }

    private boolean copiaBanco(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DataBaseHelper.DB_NAME);
            String outFile = DataBaseHelper.DB_PATH + DataBaseHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buff = new byte[1024];
            int legth = 0;
            while ((legth = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, legth);
            }
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


}
