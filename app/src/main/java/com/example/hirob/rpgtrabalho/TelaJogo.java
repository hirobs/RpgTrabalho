package com.example.hirob.rpgtrabalho;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TelaJogo extends AppCompatActivity {

    Button btnVoltar;
    Button btncAceitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_jogo);

        //Verificação personagem
        Intent it = getIntent();
        if(it.getStringExtra("personagem").equals("1")) {
            mostrarTexto(1);
        }else{
            if(it.getStringExtra("personagem").equals("2")) {
                mostrarTexto(2);
            }else{
                mostrarTexto(3);
            }
        }

        //Voltar e aceitar
        btnVoltar = (Button) findViewById(R.id.buttonVoltar);
        btncAceitar = (Button) findViewById(R.id.buttonAceitar);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btncAceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TelaJogo.this, TelaAmbiente.class));
            }
        });
    }

    public void mostrarTexto(int perso){
        TextView texto = (TextView) findViewById(R.id.textViewPersonagem);

        if(perso == 1){
            texto.setText("Esse personagem é bom no ataque");
        }
        if(perso ==2){
            texto.setText("Esse personagem é bom na defesa");
        }
        if(perso ==3){
            texto.setText("Esse personagem é equilibrado");
        }

    }
}
