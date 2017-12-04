package com.example.hirob.rpgtrabalho;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hirob.rpgtrabalho.db.DataBaseHelper;

public class MainActivity extends AppCompatActivity {
    private Button botao;
    private Button btnPersonagem1;
    private Button btnPersonagem2;
    private Button btnPersonagem3;
    private Button resetar;
    private DataBaseHelper mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = (Button) findViewById(R.id.buttonTeste);
        btnPersonagem1 = (Button) findViewById(R.id.buttonPerso1);
        btnPersonagem2 = (Button) findViewById(R.id.buttonPerso2);
        btnPersonagem3 = (Button) findViewById(R.id.buttonPerso3);
        resetar = (Button) findViewById(R.id.resetar);


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
                jogoExistente();
            }
        });

        resetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetJogo();
            }
        });

    }

    public void jogoExistente(){

        Intent it = new Intent(MainActivity.this, TelaAmbiente.class);
        it.putExtra("banco", "1");
        startActivity(it);

    }

    public void resetJogo(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Você tem certeza que quer resetar o jogo?");
        mDataBase = new DataBaseHelper(this);
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mDataBase.resetar();

            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }


}
