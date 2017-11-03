package com.example.hirob.rpgtrabalho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaAmbiente extends AppCompatActivity {
    TextView texto;
    Personagem personagem ;
    Personagem inimigo;
    Button btnCombate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_ambiente);

        texto = (TextView) findViewById(R.id.textViewLogs);

        personagem = new Personagem();
        personagem.setAtkMax(10);
        personagem.setAtkMin(5);
        personagem.setDefense(0);
        personagem.setGold(0);
        personagem.setJogavel(true);
        personagem.setNmPersonagem("Hiroshi");
        personagem.setHpTotal(50);
        personagem.setHpAtual(50);

        inimigo = new Personagem();
        inimigo.setAtkMax(5);
        inimigo.setAtkMin(1);
        inimigo.setDefense(0);
        inimigo.setGold(0);
        inimigo.setJogavel(false);
        inimigo.setNmPersonagem("Inimigo");
        inimigo.setHpTotal(30);
        inimigo.setHpAtual(30);

        btnCombate = (Button) findViewById(R.id.buttonCombate);
        btnCombate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                batalha();
            }
        });

    }
    public void batalha(){
        int ataquePersonagem = CalculosRpg.ataque(personagem.getAtkMin(),personagem.getAtkMax(),personagem.getDefense());
        int ataqueInimigo = CalculosRpg.ataque(inimigo.getAtkMin(),inimigo.getAtkMax(),inimigo.getDefense());
        personagem.setHpAtual(personagem.getHpTotal()-ataqueInimigo);
        inimigo.setHpAtual(inimigo.getHpTotal()-ataquePersonagem);
        int hpAtualPersonagem = personagem.getHpAtual();
        int hpAtualInimigo = inimigo.getHpAtual();
        texto.setText("Você sofreu "+ataqueInimigo+" dano e está com "+hpAtualPersonagem+
        "\n Voce deu "+ataquePersonagem + " dano e ele está com "+ hpAtualInimigo);
    }
}
