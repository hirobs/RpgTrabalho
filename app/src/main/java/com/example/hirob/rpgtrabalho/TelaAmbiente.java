package com.example.hirob.rpgtrabalho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.hirob.rpgtrabalho.db.DBHelper;

public class TelaAmbiente extends AppCompatActivity {
    TextView texto;
    Personagem personagem;
    Personagem inimigo;
    Button btnCombate;
    Button btnSim;
    Button btnNao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ambiente);

        texto = (TextView) findViewById(R.id.textViewLogs);
        btnCombate = (Button) findViewById(R.id.buttonCombate);
        btnSim = (Button) findViewById(R.id.buttonSim);
        btnNao = (Button) findViewById(R.id.buttonNao);


        personagem = new Personagem();
        personagem.setAtkMax(10);
        personagem.setAtkMin(5);
        personagem.setDefense(0);
        personagem.setGold(0);
        personagem.setJogavel(true);
        personagem.setNmPersonagem("Hiroshi");
        personagem.setHpTotal(50);
        personagem.setHpAtual(50);


//        //TESTEEEEEEEEEEEEEE
       DBHelper dbHelper = new DBHelper(this);
        dbHelper.doesDbExist();




//        inimigo = new Personagem();
//        inimigo.setAtkMax(5);
//        inimigo.setAtkMin(1);
//        inimigo.setDefense(0);
//        inimigo.setGold(5);
//        inimigo.setJogavel(false);
//        inimigo.setNmPersonagem("Inimigo");
//        inimigo.setHpTotal(30);
//        inimigo.setHpAtual(30);


//        btnCombate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                batalha();
//            }
//        });

    }
    public void clickBotao(View v) {
        switch(v.getId()){
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

        inimigo = new Personagem();
        inimigo.setAtkMax(5);
        inimigo.setAtkMin(1);
        inimigo.setDefense(0);
        inimigo.setGold(5);
        inimigo.setJogavel(false);
        inimigo.setNmPersonagem("Inimigo MAL");
        inimigo.setHpTotal(30);
        inimigo.setHpAtual(30);

        texto.setText("Voce encontrou o " + inimigo.getNmPersonagem() + "." +
                " Voce deseja batalhar com ele?");
        btnSim.setVisibility(View.VISIBLE);
        btnNao.setVisibility(View.VISIBLE);
    }

    public void aceitarCombate(){
        btnSim.setVisibility(View.GONE);
        btnNao.setVisibility(View.GONE);
        btnCombate.setVisibility(View.VISIBLE);
        btnCombate.setText("atacar");

        texto.setText("Voce está com "+ personagem.getHpAtual()+" de vida."+
                "\nO que você deseja fazer?");
    }

    public void batalha() {


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
                        "\n\nVocê sofreu " + ataqueInimigo + " dano e está com " + hpAtualPersonagem +
                        "\nVocê recebeu " + dinheiroInimigo + " gold e está com " + personagem.getGold() + " ao total."
                );
                btnCombate.setText("confirmar");

            }
        }
    }

    public void ambientePrincipal(){
        btnCombate.setText("explorar");
    }


}
