package com.example.hirob.rpgtrabalho;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button botao;
    private Button btnPersonagem1;
    private Button btnPersonagem2;
    private Button btnPersonagem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.buttonTeste);
        btnPersonagem1 = (Button) findViewById(R.id.buttonPerso1);
        btnPersonagem2 = (Button) findViewById(R.id.buttonPerso2);
        btnPersonagem3 = (Button) findViewById(R.id.buttonPerso3);


        btnPersonagem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, TelaJogo.class);
                it.putExtra("personagem", "1");
                startActivity(it);
            }
        });
        btnPersonagem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, TelaJogo.class);
                it.putExtra("personagem", "2");
                startActivity(it);
            }
        });
        btnPersonagem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, TelaJogo.class);
                it.putExtra("personagem", "3");
                startActivity(it);
            }
        });

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarMensagem();
            }
        });

    }

    public void mostrarMensagem(){
        Personagem personagem = new Personagem();
        personagem.setAtkMax(10);
        personagem.setAtkMin(5);
        personagem.setDefense(0);
        personagem.setGold(0);
        personagem.setJogavel(true);
        personagem.setNmPersonagem("Hiroshi");



        int ataque = CalculosRpg.ataque(personagem.getAtkMin(),personagem.getAtkMax(),personagem.getDefense());

        Context contexto = getApplicationContext();
        int duracao = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(contexto, ataque+"",duracao);
        toast.show();

    }


}
